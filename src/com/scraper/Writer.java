package com.scraper;

import java.io.File;
import java.io.IOException;

class Writer {

    private String path;

    Writer(String path) {
        this.path = path;
    }

    private String convertFileName(String fileName) {
        return fileName.toLowerCase().replace(" ", "_") + ".js";
    }

    // TODO: Create a class for creating files in the given output directory
    void createFiles(String fileName) throws IOException {
        // replace the "~" symbol with home path
        this.path = this.path.replace("~", System.getProperty("user.home"));
        // concatenate path with file name
        String fullPath = this.path + File.separator + convertFileName(fileName);
        // create new file
        File file = new File(fullPath);
        // check if the new file is created
        if(file.createNewFile())
            System.out.println("File created: " + fileName);
    }
}
