package com.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

class Scraper {

    private String path;

    Scraper(String username, String path) throws IOException {
        this.path = path;
        getLinks(username);
    }

    private void callWriter(String fileName, String instructions, String solution) {
        Writer writer = new Writer(this.path);   // Create an instance of the Writer class
        writer.createFiles(fileName, instructions, solution);  // call createFiles
    }

    private Document getInstructionDocument(String link) {
        Document solutionPage = null;
        try {
            solutionPage = Jsoup.connect(link).get();   // Get the solution page
        } catch (IOException e) {
            System.out.println(String.format("Couldn't open: %s\nGiving empty instruction.", link));
        }
        return solutionPage;
    }

    private String getInstructions(Document solutionPage) {
        Elements instructions = solutionPage.select("div.challenge-instructions p");  // Extract problem instruction from solution page
        StringBuilder stringBuilder = new StringBuilder();
        // iterate through all elements of the instructions and append them to the string builder except last repeating instruction
        for(int i = 0; i < instructions.size() - 1; i++) {
            stringBuilder.append(instructions.get(i).text()).append("\n");
        }
        return stringBuilder.toString();
    }

    private void linkTraversal(Elements links) throws IOException {
        // Iterate through links
        for(Element link: links) {
            String absoluteLink = link.attr("abs:href");
            String solution = java.net.URLDecoder.decode(absoluteLink, "UTF-8");   // Convert link to absolute link and decode it with url decoder
            String fileName = solution.substring(solution.indexOf("challenges/") + "challanges/".length(), solution.indexOf("?"));   // Extract file name from url
            solution = solution.substring(solution.indexOf("\n"));    // after the first new line, rest of the link is the solution
            Document solutionPage = getInstructionDocument(absoluteLink);
            if(solutionPage == null) {  // check if the document is null
                callWriter(fileName, "", solution); // give empty instruction to the writer if we got HttpStatusException and continue
                continue;
            }
            String instructionString = getInstructions(solutionPage);  // get the instructions as a string
            callWriter(fileName, instructionString, solution);
        }
    }

    // TODO: make this code more functional
    private void getLinks(String username) throws IOException {
        Document doc = Jsoup.connect("https://www.freecodecamp.org/" + username).get();     // Get the base profile page
        Element algorithmTable = doc.select("table").get(1);    // Get the algorithm table
        Elements links = algorithmTable.select(".col-xs-2 a[href]");    // Get the solution links
        linkTraversal(links);
    }
}
