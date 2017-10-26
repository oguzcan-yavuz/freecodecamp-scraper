package com.company;


import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String username = "yavuzovski";
        Scraper scraper = new Scraper();
        try {
            scraper.getLinks(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
