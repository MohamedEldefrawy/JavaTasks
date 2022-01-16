import java.io.*;

public class WriteFile {
    FileWriter fileWriter;
    PrintWriter printWriter;
    String[] data;

    public WriteFile(String[] data) {
        try {
            fileWriter = new FileWriter("Write.txt", true);
            printWriter = new PrintWriter(fileWriter);
            this.data = data;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile() {
        for (String text : data) {
            printWriter.println(text);
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.close();
    }
}
