package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

class Scraper {

    // TODO: decide to return type of this function
    void getLinks(String username) throws IOException {
        Document doc = Jsoup.connect("https://www.freecodecamp.org/" + username).get();
        // TODO: extract algorithms table only
        Elements table = doc.select("table");
        System.out.println(table);
    }
}
