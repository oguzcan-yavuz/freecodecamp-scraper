package com.scraper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        // concatenate path with file name
        // TODO: fix the ~ problem for getting home path, try System.getProperty("user.home");
//        Path fullPath = Paths.get(this.path + File.separator + convertFileName(fileName));
        String fullPath = this.path + File.separator + convertFileName(fileName);
        // create new file
        File file = new File(fullPath);
        // check if the new file is created
        if(file.createNewFile())
            System.out.println("File created: " + fileName);
    }
}
