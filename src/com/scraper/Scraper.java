package com.scraper;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

class Scraper {

    Scraper(String username, String path) throws IOException {
        getLinks(username, path);
    }

    // TODO: make this code more functional
    private void getLinks(String username, String path) throws IOException {
        Writer writer = new Writer(path);   // Create an instance of Writer class
        Document doc = Jsoup.connect("https://www.freecodecamp.org/" + username).get();     // Get the base profile page
        Element algorithmTable = doc.select("table").get(1);    // Get the algorithm table
        Elements links = algorithmTable.select(".col-xs-2 a[href]");    // Get the solution links
        // Iterate through links
        for(Element link: links) {
            String solution = java.net.URLDecoder.decode(link.attr("abs:href"), "UTF-8");   // Convert link to absolute link and decode it with url decoder
            String fileName = solution.substring(solution.indexOf("challenges/") + "challanges/".length(), solution.indexOf("?"));   // Extract file name from url
            solution = solution.substring(solution.indexOf("\n"));    // after the first new line, rest of the link is the solution
            Document solutionPage;
            try {   // it gives HttpStatusException for one connection
                solutionPage = Jsoup.connect(link.attr("abs:href")).get();  // Get the solution page
            } catch (HttpStatusException e) {
                writer.createFiles(fileName, "", solution);     // give empty instruction to the writer if we got HttpStatusException and continue
                continue;
            }
            Elements instructions = solutionPage.select("div.challenge-instructions p");  // Extract problem instruction from solution page
            StringBuilder instructionBuilder = new StringBuilder();
            // iterate through all elements of the instructions and append them to the string builder except last repeating instruction
            for(int i = 0; i < instructions.size() - 1; i++) {
                instructionBuilder.append(instructions.get(i).text()).append("\n");
            }
            String instructionString = instructionBuilder.toString();   // convert string builder to string
            writer.createFiles(fileName, instructionString, solution);  // call createFiles from Writer class with all parameters
        }
    }
}
