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
        header.setStyle("-fx-background-color: #F4F4F4;");
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
        title.setFont(new Font("Arial", 36));
        title.setAlignment(Pos.CENTER_LEFT);
        title.setTextFill(Color.DARKSLATEBLUE);



        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-color: #e6e6fa; -fx-border-radius: 15; -fx-background-radius: 15;");
        nameField.setFont(new Font("Arial", 18));
        nameField.setAlignment(Pos.CENTER_LEFT);
        Label fullNameLabelField = new Label("Full Name");
        fullNameLabelField.setFont(new Font("Arial", 18));
        VBox nameBox = new VBox(fullNameLabelField,nameField);

        TextField emailField = new TextField();
        emailField.setStyle("-fx-background-color: #e6e6fa; -fx-border-radius: 15; -fx-background-radius: 15;");
        emailField.setFont(new Font("Arial", 18));
        emailField.setAlignment(Pos.CENTER_LEFT);
        Label emailLabelField = new Label("Email");
        emailLabelField.setFont(new Font("Arial", 18));
        VBox emailFieldBox = new VBox(emailLabelField,emailField);

        TextArea messageField = new TextArea();
        messageField.setStyle("-fx-background-color: #e6e6fa; -fx-border-radius: 15; -fx-background-radius: 15;");
        messageField.setFont(new Font("Arial", 18));
        messageField.setPrefRowCount(5);
        Label messageLabelField = new Label("Message");
        messageLabelField.setFont(new Font("Arial", 18));
        VBox messageFieldBox = new VBox(messageLabelField,messageField);

        VBox fullBox = new VBox(5);
        fullBox.getChildren().addAll(nameBox,emailFieldBox,messageFieldBox);
        Button sendButton = new Button("Save");
        sendButton.setFont(new Font("Arial", 18));
        sendButton.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: #ffffff; -fx-border-radius: 20; -fx-background-radius: 20;");
        sendButton.setPrefSize(100, 40);

        // Create an HBox to align the send button to the right
        HBox buttonBox = new HBox(sendButton);
        buttonBox.setAlignment(Pos.CENTER);

        formBox.getChildren().addAll(header, title, fullBox, sendButton, buttonBox);

        // Main layout
        BorderPane layout = new BorderPane();
        layout.setLeft(ChatScreen.sideBar());
        layout.setCenter(formBox);

        // Scene and Stage
        Scene scene = new Scene(layout, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Help & Support");
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(800);
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);}
}