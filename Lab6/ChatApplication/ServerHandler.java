import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerHandler {

    // Sockets
    private ServerSocket serverSocket;


    private static final ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    public ServerHandler() {
        try {
            serverSocket = new ServerSocket(2021);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has been connected");


                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ClientHandler implements Runnable {

        private final Socket socket;

        // IO streams
        public BufferedReader inputStream;
        public BufferedWriter outputStream;


        public ClientHandler(Socket socket) {
            this.socket = socket;

            try {
                outputStream = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
                inputStream = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                System.out.println(inputStream.readLine());

                clientHandlers.add(this);

            } catch (IOException e) {
                e.printStackTrace();
                closeStreams();
            }
        }

        private void closeStreams() {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            removeClientHandler();
        }

        public void broadcast(String message) throws IOException {
            for (ClientHandler clientHandler : clientHandlers) {
                try {
                    clientHandler.outputStream.write(message);
                    clientHandler.outputStream.newLine();
                    clientHandler.outputStream.flush();
                } catch (IOException e) {

                    closeStreams();
                    e.printStackTrace();
                }
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
                    String messageFromClient = inputStream.readLine();
                    if (messageFromClient != null)
                        broadcast(messageFromClient);
                } catch (IOException e) {
                    e.printStackTrace();
                    closeStreams();
                }
            }
        }
    }
}