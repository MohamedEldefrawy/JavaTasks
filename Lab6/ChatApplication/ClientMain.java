

public class ClientMain {
    public static void main(String[] args) {
        ChatClient ui = new ChatClient();
//        ui.establishConnection();
        System.out.println("Client running");
        ui.frame.setSize(600, 400);
        ui.frame.setResizable(false);
        ui.frame.setVisible(true);
    }
}
