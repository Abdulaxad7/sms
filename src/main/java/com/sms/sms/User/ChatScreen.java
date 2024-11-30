package com.sms.sms.User;

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
    private static final StudentInfo studentInfo = new StudentInfo();

    @Override
    public void start(Stage primaryStage) {

        BorderPane chatPane = new BorderPane();
        chatPane.setPadding(new Insets(10));
        chatPane.setStyle("-fx-background-color: #F4F4F4;");

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

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(sideBar(2,false,null));
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

    public static VBox sideBar(int i, boolean isAdmin, Stage primaryStage) {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #1a1a2e;");

        ImageView logo = new ImageView(new Image("https://listinstorage.s3.eu-north-1.amazonaws.com/logo.png"));
        logo.setFitHeight(100);
        logo.setFitWidth(100);
        logo.setPreserveRatio(true);

        Label titleLabel = new Label("Student management system");
        titleLabel.setFont(new Font("Arial", 14));
        titleLabel.setTextFill(Color.LIGHTGRAY);

        String [] texts = {"Courses","Total Grade","Chat","Settings","Logout","Help"};
        switch (i){
            case 0-> texts[0]+="   <";
            case 1-> texts[1]+="   <";
            case 2-> texts[2]+="   <";
            case 3-> texts[3]+="   <";
            case 5-> texts[5]+="   <";
        }
        Region spacer = new Region();
        spacer.setPrefHeight(5);
        Region spacer1 = new Region();
        spacer1.setPrefHeight(10);


        Button coursesButton,gradesButton,chatButton,settingsButton,logoutButton,helpButton;
        if (!isAdmin){
             coursesButton = createSidebarButton(texts[0], "https://cdn-icons-png.flaticon.com/128/10433/10433048.png");
             gradesButton = createSidebarButton(texts[1], "https://cdn-icons-png.flaticon.com/128/9913/9913544.png");
            gradesButton.setOnAction(actionEvent -> primaryStage.setScene(studentInfo.scene()));
             chatButton = createSidebarButton(texts[2], "https://cdn-icons-png.flaticon.com/128/724/724715.png");
             settingsButton = createSidebarButton(texts[3], "https://cdn-icons-png.flaticon.com/128/3953/3953226.png");
             logoutButton = createSidebarButton(texts[4], "https://cdn-icons-png.flaticon.com/128/1828/1828490.png");
             helpButton = createSidebarButton(texts[5], "https://cdn-icons-png.flaticon.com/128/189/189665.png");
            Region spacer2 = new Region();
            spacer2.setPrefHeight(40);
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
                    helpButton);

        }else{
           Button studentTable = createSidebarButton("Students   <", "https:s-m-s.s3.eu-north-1.amazonaws.com/studentIcon.png");
            logoutButton = createSidebarButton(texts[4], "https://cdn-icons-png.flaticon.com/128/1828/1828490.png");
            sidebar.getChildren().addAll(
                    logo,
                    spacer,
                    titleLabel,
                    spacer1,
                    studentTable,
                    logoutButton);
        }

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

    private static HBox createChatMessage(String message, boolean isUser) {
        HBox messageBox = new HBox(10);
        messageBox.setPadding(new Insets(5));

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
