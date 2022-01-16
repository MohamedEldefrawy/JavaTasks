import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFIle {
    FileInputStream inputStream;

    ReadFIle() {
        try {
            inputStream = new FileInputStream("test.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printTextFile() {
        byte[] fileBytes;
        try {
            fileBytes = new byte[inputStream.available()];
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        try {
            while ((i = inputStream.read()) != -1) {
                System.out.print((char) i);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
