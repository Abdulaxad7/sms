package com.sms.sms.User;

import com.sms.sms.leftbar.LeftSideBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import static com.sms.sms.styles.Colors.*;
import static com.sms.sms.styles.Images.AVATAR_ICON1;


public class ChatScreen {
    public Scene scene() {

        BorderPane chatPane = new BorderPane();
        chatPane.setPadding(new Insets(10));
        chatPane.setStyle(TOP_BAR);

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

        Button sendButton = new Button("Send");
        sendButton.setStyle(BUTTON);

        inputSection.getChildren().addAll(messageField, sendButton);
        chatPane.setBottom(inputSection);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(LeftSideBar.sideBar(2, false));
        mainLayout.setCenter(chatPane);


        return new Scene(mainLayout, 1000, 800);
    }

    static ListView<HBox> chatViewList() {
        ListView<HBox> chatList = new ListView<>();
        chatList.setStyle(VIEW_LIST);

        //todo -> this messages are gonna come from firebase
        chatList.getItems().add(createChatMessage("What's new in the store for the fall season?", true));
        chatList.getItems().add(createChatMessage("We just got in our fresh autumn collection today! 😍", false));
        chatList.getItems().add(createChatMessage("What's new in the store for the fall season?", true));
        chatList.getItems().add(createChatMessage("We just got in our fresh autumn collection today! 😍", false));
        return chatList;
    }


    private static HBox createChatMessage(String message, boolean isUser) {
        HBox messageBox = new HBox(10);
        messageBox.setPadding(new Insets(5));

        ImageView avatar = new ImageView(AVATAR_ICON1);
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

}
