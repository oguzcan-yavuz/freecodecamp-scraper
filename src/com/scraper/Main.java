package com.scraper;

import java.io.IOException;

public class Main {

    private static void printHelp() {
        // prints parameters and basic usage example of the program
        System.out.println(
                "Missing parameters!\n" +
                "Parameters:\n" +
                "-h: help\n" +
                "-u: freecodecamp username(*)\n" +
                "-o: output directory(*)\n" +
                "Example usage:\n" +
                "  fcc-scraper -u yavuzovksi -o ~/playground/javascript"
        );
        System.exit(0);
    }

    private static String[] argsHandler(String[] args) {
        // arguments[0] = username, arguments[1] = output directory
        String[] arguments = new String[2];
        // print help if any argument is not given
        if(args.length == 0) {
            printHelp();
        }
        // iterate through args, find the username and output directory
        for(int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-h":
                    printHelp();
                case "-u":
                    arguments[0] = args[i + 1];
                    break;
                case "-o":
                    arguments[1] = args[i + 1];
                    break;
            }
        }
        // if username or output is not given, call the printHelp function.
        if(arguments[0] == null || arguments[1] == null)
            printHelp();
        return arguments;   // return arguments with username as 0. element and output dir as 1. element
    }

    public static void main(String[] args) throws IOException {
        String[] arguments = argsHandler(args);
        Scraper scraper = new Scraper(arguments[0], arguments[1]);
    }
}
