import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {
        ReadFIle f = new ReadFIle();
        f.printTextFile();

        WriteFile outputStream = new WriteFile(args);
        outputStream.createFile();

    }
}
