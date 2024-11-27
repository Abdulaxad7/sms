package com.sms.sms.achanges;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SupportScreen extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Header
        HBox header = new HBox(50);
        header.setPadding(new Insets(10));
        header.setStyle("-fx-background-color: #FFFFFF;");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.TOP_CENTER);

        // Phone Section
        ImageView phoneIcon = new ImageView(new Image("https://s-m-s.s3.eu-north-1.amazonaws.com/phoneImage.png"));
        phoneIcon.setFitWidth(30);
        phoneIcon.setFitHeight(30);
        Label phoneLabel = new Label("+999 99 999-99-99");
        phoneLabel.setFont(Font.font("Arial", 14));
        phoneLabel.setTextFill(Color.DARKSLATEGRAY);
        HBox phoneBox = new HBox(10, phoneIcon, phoneLabel);
        phoneBox.setAlignment(Pos.CENTER);

        // Email Section
        ImageView emailIcon = new ImageView(new Image("https://s-m-s.s3.eu-north-1.amazonaws.com/mailImage.png"));
        emailIcon.setFitWidth(30);
        emailIcon.setFitHeight(30);
        Label emailLabel = new Label("example@support.com");
        emailLabel.setFont(Font.font("Arial", 14));
        emailLabel.setTextFill(Color.DARKSLATEGRAY);
        HBox emailBox = new HBox(10, emailIcon, emailLabel);
        emailBox.setAlignment(Pos.CENTER);

        // Combine Phone and Email
        HBox contactBox = new HBox(50, phoneBox, emailBox);
        contactBox.setAlignment(Pos.TOP_CENTER);
        header.getChildren().add(contactBox);

        // Form
        VBox formBox = new VBox(10);
        formBox.setSpacing(30);
        formBox.setPadding(new Insets(20));

        Label title = new Label("Help & Support Center");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER_LEFT);
        title.setTextFill(Color.DARKSLATEBLUE);


        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 15; -fx-background-radius: 15;");
        nameField.setAlignment(Pos.CENTER_LEFT);
        nameField.setPromptText("Full Name");

        TextField emailField = new TextField();
        emailField.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 15; -fx-background-radius: 15;");
        emailField.setAlignment(Pos.CENTER_LEFT);
        emailField.setPromptText("Email");

        TextArea messageField = new TextArea();
        messageField.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 15; -fx-background-radius: 15;");
        messageField.setPromptText("Message");
        messageField.setPrefRowCount(5);

        Button sendButton = new Button("Save");
        sendButton.setFont(new Font("Arial", 18));
        sendButton.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: #ffffff; -fx-border-radius: 20; -fx-background-radius: 20;");
        sendButton.setPrefSize(100, 40);

        // Create an HBox to align the send button to the right
        HBox buttonBox = new HBox(sendButton);
        buttonBox.setAlignment(Pos.CENTER);

        formBox.getChildren().addAll(header, title, nameField, emailField, messageField, buttonBox);

        // Main layout
        BorderPane layout = new BorderPane();
        layout.setLeft(ChatScreen.sideBar());
        layout.setCenter(formBox);

        // Scene and Stage
        Scene scene = new Scene(layout, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Help & Support");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);}
}