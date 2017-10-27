package com.scraper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Writer {

    private String path;

    Writer(String path) {
        this.path = path;
    }

    // convert example: Reverse a String => reverse_a_string.js
    private String convertFileName(String fileName) {
        return fileName.toLowerCase().replace(" ", "_") + ".js";
    }

    private String convertPath(String fileName) {
        this.path = this.path.replace("~", System.getProperty("user.home"));    // replace the "~" symbol with home path
        return this.path + File.separator + fileName;   // concatenate path with file name
    }

    private void makeFiles(String fileName, String instructions, String solution, String fullPath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fullPath))) {
            bw.write("/*\n" + instructions + "*/\n\n" + solution + "\n"); // write instructions at the top in comment blocks, then solution
            System.out.println(String.format("File is successfully created: %s", fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(String.format("Error occurred while trying to create: %s", fileName));
        }

    }

    void createFiles(String fileName, String instructions, String solution) {
        String convertedFileName = convertFileName(fileName);
        String fullPath = convertPath(convertedFileName);
        makeFiles(convertedFileName, instructions, solution, fullPath);
    }
}
