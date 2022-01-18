package com.notepad.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileWriter {
    private BufferedWriter writer;
    private File file;
    FileWriter fileWriter;
    private String content;

    public BufferedWriter getWriter() {
        return writer;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    public TextFileWriter(File file) {
        this.file = file;
        String fileName = file.getAbsolutePath();

        // Create file if not exists
        if (!file.exists()) {
            try {
                boolean result = file.createNewFile();
                if (result)
                    System.out.println("Created ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Initialize file streams
        try {
            fileWriter = new FileWriter(fileName);
            System.out.println(fileName);
            this.writer = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void saveFile() {
        try {
            writer.write(content);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
