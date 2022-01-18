package com.notepad.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {
    private FileReader fileReader;
    private BufferedReader reader;
    private File file;
    private String fileName;
    private String content;

    public TextFileReader(File file) {
        this.file = file;
        this.fileName = file.getAbsolutePath();


        // Initialize file streams
        try {
            fileReader = new FileReader(fileName);
            System.out.println(fileName);

            this.reader = new BufferedReader(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String fileContent() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
