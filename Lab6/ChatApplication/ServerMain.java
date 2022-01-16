public class ServerMain {
    public static void main(String[] args) {
        System.out.println("Server is  running......");
        ServerHandler serverHandler = new ServerHandler();
        serverHandler.startServer();
    }
}
