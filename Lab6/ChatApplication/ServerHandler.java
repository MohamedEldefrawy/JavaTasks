import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;


public class ServerHandler {

    // Sockets
    private ServerSocket serverSocket;
//    Socket socket;

    public ServerHandler() {
        try {
            serverSocket = new ServerSocket(2021);
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class ClientHandler extends Thread {

    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();


    // IO streams
    DataInputStream dStream;
    PrintStream pStream;
    private Socket socket;


    public ClientHandler(Socket socket) {
        this.socket = socket;

        try {

            dStream = new DataInputStream(socket.getInputStream());
            pStream = new PrintStream(socket.getOutputStream());
            clientHandlers.add(this);

            start();

        } catch (IOException e) {
            e.printStackTrace();
            closeStreams();
        }
    }

    private void closeStreams() {
        try {
            if (dStream != null)
                dStream.close();
            if (pStream != null)
                pStream.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        removeClientHandler();
    }

    public void broadcast(String message) throws IOException {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.pStream.println(message);
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        System.out.println("Chat client has been closed");
    }

    @Override
    public void run() {

        while (socket.isConnected()) {
            try {
                String messageFromClient = dStream.readLine();
                if (messageFromClient != null) {
                    broadcast(messageFromClient);
                    System.out.println(messageFromClient);
                } else
                    socket.close();
            } catch (SocketException e) {
                System.out.println("Client has been closed");
            } catch (IOException e) {
                e.printStackTrace();
                closeStreams();
            }
        }
    }
}
