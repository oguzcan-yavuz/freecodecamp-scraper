package com.scraper;

import java.io.File;
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

    void createFiles(String fileName, String instructions, String solution) throws IOException {
        // TODO: write the instructions in comment at the top of the created file and write the solution after that
        this.path = this.path.replace("~", System.getProperty("user.home"));    // replace the "~" symbol with home path
        String fullPath = this.path + File.separator + convertFileName(fileName);   // concatenate path with file name
        File file = new File(fullPath);     // create new file
        if(file.createNewFile())    // check if the new file is created
            System.out.println("File created: " + fileName);    // print out created file's name
    }
}
