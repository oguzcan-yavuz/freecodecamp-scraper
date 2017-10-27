package com.scraper;

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
        // Get the document
        Document doc = Jsoup.connect("https://www.freecodecamp.org/" + this.username).get();
        // Get the tables
        Elements table = doc.select("table");

        for(Element t: table) {
            for(Element algorithms: t.getElementsContainingText("Algorithms")) {
                for(Element algorithm: algorithms.select("td")) {
                    // TODO: get the document of the links and send their content to the createFiles func.
//                    Elements solutionLink = algorithm.select("a[href]");
//                    System.out.println(solutionLink);
                    String fileName = algorithm.select(".col-xs-5").text();
                    writer.createFiles(fileName);
                }
            }
        }
    }
}
