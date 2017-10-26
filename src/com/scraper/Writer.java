package com.scraper;

import java.io.File;
import java.io.IOException;

class Writer {
    // TODO: Create a class for creating files in the given output directory
    public void createFiles(String[] fileNames, String path) throws IOException {
        String fullPath;
        for(String name: fileNames) {
            // concatenate path with file name
            fullPath = path + File.separator + name;
            // create new file
            File file = new File(fullPath);
            // check if the new file is created
            if(file.createNewFile())
                System.out.println("File created: " + name);
        }
    }
}
