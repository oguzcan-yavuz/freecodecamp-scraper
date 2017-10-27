package com.scraper;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

class Scraper {

    private String username;
    private String path;

    Scraper(String username, String path) {
        this.username = username;
        this.path = path;
    }

    void getLinks() throws IOException {
        // Create an instance of Writer class
        Writer writer = new Writer(this.path);
        // Get the base profile page
        Document doc = Jsoup.connect("https://www.freecodecamp.org/" + this.username).get();
        // Get the algorithm table
        Element algorithmTable = doc.select("table").get(1);
        // Get the solution links
        Elements links = algorithmTable.select(".col-xs-2 a[href]");
        // Iterate through links
        for(Element link: links) {
            // Convert link to absolute link and decode it with url decoder
            String solution = java.net.URLDecoder.decode(link.attr("abs:href"), "UTF-8");
            String fileName = solution.substring(solution.indexOf("challenges/") + "challanges/".length(), solution.indexOf("?"));   // Extract file name from url
            solution = solution.substring(solution.indexOf("\n"));    // after the first new line, rest of the link is the solution
            Document solutionPage;
            try {   // it gives HttpStatusException for one connection
                solutionPage = Jsoup.connect(link.attr("abs:href")).get();  // Get the solution page
            } catch (HttpStatusException e) {   // give empty instruction if we got HttpStatusException and continue
//                writer.createFiles(fileName, "", solution);
                continue;
            }
            // TODO: iterate through instruction elements and concatenate them as a string
            Elements instructions = solutionPage.select("div.challenge-instructions");  // Extract problem instruction from solution page
            System.out.println(instructions);

//            writer.createFiles(fileName, instructions, solution);
        }
    }
}
