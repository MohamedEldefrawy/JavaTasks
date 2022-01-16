import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ChatClient {

    private Socket socket;
    private BufferedReader inputStream;
    private BufferedWriter outputStream;


    JFrame frame = new JFrame();
    JTextArea textArea;
    JScrollPane scrollPane;
    JTextField textField;
    JButton sendButton;

    Thread thread;


    public ChatClient() {

        frame.setLayout(new FlowLayout());

        textArea = new JTextArea(20, 50);
        scrollPane = new JScrollPane(textArea);
        textField = new JTextField(40);
        textArea.setEditable(false);
        textField.grabFocus();
        textField.requestFocus();

        try {
            establishConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendButton = new JButton("Send");

        sendButton.addActionListener(ae -> {
            sendText(textArea, textField);
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    closeSessions();
                    System.out.println("closing");
                    System.exit(0);
                }
            }

        });

        frame.add(scrollPane);
        frame.add(textField);
        frame.add(sendButton);

        MessageListener messageListener = new MessageListener();

        thread = new Thread(messageListener);
        thread.start();
    }

    private void closeSessions() {
        try {
//            if (inputStream != null)
            inputStream.close();
//            if (outputStream != null)
            outputStream.close();
//            if (socket != null)
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendText(JTextArea textArea, JTextField textField) {

        textArea.append(textField.getText() + "\n");
        String message = textField.getText();
        textField.setText("");

        try {
            outputStream.write(message);
            outputStream.newLine();
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            closeSessions();
        }
    }

    private void establishConnection() throws IOException {
        socket = new Socket(InetAddress.getLocalHost(), 2021);
        outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }


    public class MessageListener implements Runnable {

        public void listenForMessage() {
            while (socket.isConnected()) {
                try {
                    String messageFromOthers = inputStream.readLine();
                    textArea.append(messageFromOthers + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    closeSessions();
                }
            }

        }

        @Override
        public void run() {
            listenForMessage();
        }
    }

}



