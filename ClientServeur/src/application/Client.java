import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Application {
    private TextField messageField;
    private TextArea receivedMessagesArea;
    private Socket socket;
    private PrintWriter output;

    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();
        TextField ipField = new TextField();
        TextField portField = new TextField();
        Button connectButton = new Button("Connect");
        messageField = new TextField();
        Button sendButton = new Button("Send");
        receivedMessagesArea = new TextArea();

        connectButton.setOnAction(e -> connect(ipField.getText(), Integer.parseInt(portField.getText())));
        sendButton.setOnAction(e -> sendMessage(messageField.getText()));

        vbox.getChildren().addAll(ipField, portField, connectButton, messageField, sendButton, receivedMessagesArea);
        Scene scene = new Scene(vbox, 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    private void connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            output = new PrintWriter(socket.getOutputStream(), true);
            new Thread(() -> {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String serverMessage;
                    while ((serverMessage = input.readLine()) != null) {
                        receivedMessagesArea.appendText(serverMessage + "\n");
                    }
                } catch (IOException e) {
                    System.out.println("Server error: " + e.getMessage());
                }
            }).start();
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }

    private void sendMessage(String message) {
        output.println(message);
    }
}