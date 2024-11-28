package com.sms.sms.achanges;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.EventListener;

public class ChatScreen extends Application implements EventListener {
    @Override
    public void start(Stage primaryStage) {
        // Chat area
        BorderPane chatPane = new BorderPane();
        chatPane.setPadding(new Insets(10));
        chatPane.setStyle("-fx-background-color: #F4F4F4;");


        // Header
        HBox header = new HBox(10);
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER_LEFT);

        Label chatTitle = new Label("Chat");
        chatTitle.setFont(new Font("Arial", 20));

        Label groupLabel = new Label("Group-number");
        groupLabel.setFont(new Font("Arial", 14));
        groupLabel.setTextFill(Color.GRAY);

        header.getChildren().addAll(chatTitle, groupLabel);

        chatPane.setTop(header);
        chatPane.setCenter(chatViewList());

        // Message input section
        HBox inputSection = new HBox(10);
        inputSection.setPadding(new Insets(10));
        inputSection.setAlignment(Pos.CENTER);

        TextField messageField = new TextField();
        messageField.setPromptText("Type a message...");
        messageField.setPrefWidth(400);

        Button sendButton = new Button("Send");
        sendButton.setStyle("-fx-background-color: #8B9FFF; -fx-text-fill: white;");

        inputSection.getChildren().addAll(messageField, sendButton);
        chatPane.setBottom(inputSection);

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(sideBar());
        mainLayout.setCenter(chatPane);


        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(800);
        primaryStage.setTitle("Chat UI Example");
        primaryStage.show();
    }

    static ListView<HBox> chatViewList() {
        ListView<HBox> chatList = new ListView<>();
        chatList.setStyle("-fx-background-color: #F9F9F9;");

        //todo -> this messages are gonna come from messages table which will have one to many relation-ship with users table
        chatList.getItems().add(createChatMessage("What's new in the store for the fall season?", true));
        chatList.getItems().add(createChatMessage("We just got in our fresh autumn collection today! 😍", false));
        chatList.getItems().add(createChatMessage("What's new in the store for the fall season?", true));
        chatList.getItems().add(createChatMessage("We just got in our fresh autumn collection today! 😍", false));
        return chatList;
    }

    public static VBox sideBar() {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #1a1a2e;");

        // Sidebar logo
        ImageView logo = new ImageView(new Image("https://listinstorage.s3.eu-north-1.amazonaws.com/logo.png"));
        logo.setFitHeight(100);
        logo.setFitWidth(100);
        logo.setPreserveRatio(true);

        // Sidebar title
        Label titleLabel = new Label("Student Management System");
        titleLabel.setFont(new Font("Arial", 14));
        titleLabel.setTextFill(Color.LIGHTGRAY);

        // Buttons with different icons
        Button coursesButton = createSidebarButton("Courses", "https://cdn-icons-png.flaticon.com/128/10433/10433048.png");
        Button gradesButton = createSidebarButton("Total Grade", "https://cdn-icons-png.flaticon.com/128/9913/9913544.png");
        Button chatButton = createSidebarButton("Chat   <", "https://cdn-icons-png.flaticon.com/128/724/724715.png");
        Button settingsButton = createSidebarButton("Settings", "https://cdn-icons-png.flaticon.com/128/3953/3953226.png");
        Button logoutButton = createSidebarButton("Logout", "https://cdn-icons-png.flaticon.com/128/1828/1828490.png");
        Button helpButton = createSidebarButton("Help", "https://cdn-icons-png.flaticon.com/128/189/189665.png");

        // Spacers
        Region spacer = new Region();
        spacer.setPrefHeight(5);
        Region spacer1 = new Region();
        spacer1.setPrefHeight(10);
        Region spacer2 = new Region();
        spacer2.setPrefHeight(40);

        // Add items to the sidebar
        sidebar.getChildren().addAll(
                logo,
                spacer,
                titleLabel,
                spacer1,
                coursesButton,
                gradesButton,
                chatButton,
                settingsButton,
                spacer2,
                logoutButton,
                helpButton
        );

        return sidebar;
    }


    private static Button createSidebarButton(String text, String iconUrl) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: lightgray; -fx-font-size: 14px;");
        button.setPrefWidth(150);
        button.setAlignment(Pos.CENTER_LEFT);


        if (iconUrl != null && !iconUrl.isEmpty()) {
            ImageView icon = new ImageView(new Image(iconUrl));
            icon.setFitWidth(16);
            icon.setFitHeight(16);
            button.setGraphic(icon);
        }

        return button;
    }

    // Helper method to create a chat message
    private static HBox createChatMessage(String message, boolean isUser) {
        HBox messageBox = new HBox(10);
        messageBox.setPadding(new Insets(5));

        // Avatar
        ImageView avatar = new ImageView(new Image("https://s-m-s.s3.eu-north-1.amazonaws.com/i1.png"));
        avatar.setFitWidth(40);
        avatar.setFitHeight(40);
        Circle clip = new Circle(20, 20, 20);
        avatar.setClip(clip);

        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setFont(new Font("Arial", 14));
        messageLabel.setStyle("-fx-background-color: " + (isUser ? "#8B9FFF;" : "#EAEAEA; -fx-text-fill: black;") + " -fx-padding: 10; -fx-background-radius: 10;");

        if (isUser) {
            messageBox.getChildren().addAll(messageLabel, avatar);
            messageBox.setAlignment(Pos.CENTER_RIGHT);
        } else {
            messageBox.getChildren().addAll(avatar, messageLabel);
            messageBox.setAlignment(Pos.CENTER_LEFT);
        }

        return messageBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
