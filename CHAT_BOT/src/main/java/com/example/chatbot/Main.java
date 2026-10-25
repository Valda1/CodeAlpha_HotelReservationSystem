package com.example.chatbot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Label title = new Label("My Chatbot");
        Label response = new Label("Hello! Send me a message.");

        TextField input = new TextField();
        input.setPromptText("Type your message...");

        Button sendButton = new Button("Send");

        sendButton.setOnAction(event -> {
            String message = input.getText().trim();

            if (!message.isEmpty()) {
                response.setText("You said: " + message);
                input.clear();
            }
        });

        HBox inputRow = new HBox(10, input, sendButton);
        VBox layout = new VBox(15, title, response, inputRow);
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 450, 200);

        stage.setTitle("NLP Chatbot");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}