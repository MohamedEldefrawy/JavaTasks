import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class ChatClient {

    public static class ClientHandler {
        Socket socket;
        DataInputStream inputStream;
        PrintStream outputStream;

        public ClientHandler() {
            try {
                socket = new Socket(InetAddress.getLocalHost(), 2021);
                outputStream = new PrintStream(socket.getOutputStream());
                inputStream = new DataInputStream(socket.getInputStream());
                outputStream.println("Hello from client");
                System.out.println(inputStream.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
