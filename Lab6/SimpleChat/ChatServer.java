import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public static class serverHandler {

        // Server
        ServerSocket serverSocket;

        // Client
        Socket socket;

        DataInputStream inputStream;
        PrintStream outputStream;

        public serverHandler() {
            try {
                serverSocket = new ServerSocket(2021);
                socket = serverSocket.accept();
                outputStream = new PrintStream(socket.getOutputStream());
                outputStream.println("Done..");
                inputStream = new DataInputStream(socket.getInputStream());

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                inputStream.close();
                outputStream.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
