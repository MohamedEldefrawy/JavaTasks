import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class ChatClient {

    private Socket socket;
    DataInputStream dStream;
    PrintStream pStream;


    JFrame frame = new JFrame();
    JTextArea textArea;
    JScrollPane scrollPane;
    JTextField textField;
    JButton sendButton;


    public ChatClient() {

        frame.setLayout(new FlowLayout());

        textArea = new JTextArea(20, 50);
        scrollPane = new JScrollPane(textArea);
        textField = new JTextField(40);
        textArea.setEditable(false);
        textField.grabFocus();
        textField.requestFocus();


        establishConnection();


        sendButton = new JButton("Send");

        sendButton.addActionListener(ae -> {
            sendText(textField);
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    closeSocket();
                    System.out.println("closing");
                    System.exit(0);
                }
            }
        });

        frame.add(scrollPane);
        frame.add(textField);
        frame.add(sendButton);

        new MessageListener();
    }

    private void closeSocket() {
        try {
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void sendText(JTextField textField) {

        String message = textField.getText();
        pStream.println(message);
        textField.setText("");
    }

    private void establishConnection() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 2021);
            dStream = new DataInputStream(socket.getInputStream());
            pStream = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public class MessageListener extends Thread {


        public MessageListener() {
            start();
        }

        @Override
        public void run() {
            while (socket.isConnected()) {
                try {
                    String messageFromOthers = dStream.readLine();
                    System.out.println(messageFromOthers);
                    textArea.append(messageFromOthers + "\n");
                } catch (SocketException e) {
                    e.printStackTrace();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}