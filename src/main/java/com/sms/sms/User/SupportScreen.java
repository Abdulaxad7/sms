package com.sms.sms.User;

import com.sms.sms.leftbar.LeftSideBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static com.sms.sms.styles.Images.MAIL_ICON;
import static com.sms.sms.styles.Images.PHONE_ICON;

public class SupportScreen {

    public Scene scene(String username) {
        HBox header = createHeader();

        VBox formBox = createForm(header);

        BorderPane layout = createMainLayout(formBox,username);

        return new Scene(layout, 1000, 800);
    }


    private HBox createHeader() {
        HBox header = new HBox(50);
        header.setPadding(new Insets(10));
        header.setStyle("-fx-background-color: #F4F4F4;");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.TOP_CENTER);

        HBox phoneBox = createContactBox(PHONE_ICON, "+999 99 999-99-99");
        HBox emailBox = createContactBox(MAIL_ICON, "example@support.com");

        HBox contactBox = new HBox(50, phoneBox, emailBox);
        contactBox.setAlignment(Pos.TOP_CENTER);

        header.getChildren().add(contactBox);
        return header;
    }

    private HBox createContactBox(String iconUrl, String text) {
        ImageView icon = new ImageView(new Image(iconUrl));
        icon.setFitWidth(30);
        icon.setFitHeight(30);

        Label label = new Label(text);
        label.setFont(Font.font("Arial", 14));
        label.setTextFill(Color.DARKSLATEGRAY);

        HBox contactBox = new HBox(10, icon, label);
        contactBox.setAlignment(Pos.CENTER);
        return contactBox;
    }

    private VBox createForm(HBox header) {
        VBox formBox = new VBox(10);
        formBox.setSpacing(30);
        formBox.setPadding(new Insets(20));

        Label title = createTitle();
        VBox fullBox = createInputFields();
        HBox hBox = createSubmitButton();

        formBox.getChildren().addAll(header, title, fullBox, hBox);
        return formBox;
    }

    private Label createTitle() {
        Label title = new Label("Help & Support Center");
        title.setFont(new Font("Arial", 36));
        title.setAlignment(Pos.CENTER_LEFT);
        title.setTextFill(Color.DARKSLATEBLUE);
        return title;
    }

    private VBox createInputFields() {
        VBox fullBox = new VBox(5);

        VBox nameBox = createInputField("Full Name", false);
        VBox emailFieldBox = createInputField("Email", false);
        VBox messageFieldBox = createInputField("Message", true);

        fullBox.getChildren().addAll(nameBox, emailFieldBox, messageFieldBox);
        return fullBox;
    }

    private VBox createInputField(String labelText, boolean isTextArea) {
        Label label = new Label(labelText);
        label.setFont(new Font("Arial", 18));

        if (isTextArea) {
            TextArea textArea = new TextArea();
            textArea.setStyle("-fx-background-color: #e6e6fa; -fx-border-radius: 15; -fx-background-radius: 15;");
            textArea.setFont(new Font("Arial", 18));
            textArea.setPrefRowCount(5);
            return new VBox(label, textArea);
        } else {
            TextField textField = new TextField();
            textField.setStyle("-fx-background-color: #e6e6fa; -fx-border-radius: 15; -fx-background-radius: 15;");
            textField.setFont(new Font("Arial", 18));
            textField.setAlignment(Pos.CENTER_LEFT);
            return new VBox(label, textField);
        }
    }

    private HBox createSubmitButton() {
        Button sendButton = new Button("Save");
        sendButton.setFont(new Font("Arial", 18));
        sendButton.setAlignment(Pos.CENTER);
        sendButton.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: #ffffff; -fx-border-radius: 20; -fx-background-radius: 20;");
        sendButton.setPrefSize(100, 40);

        HBox hBox = new HBox(sendButton);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private BorderPane createMainLayout(VBox formBox,String username) {
        BorderPane layout = new BorderPane();
        layout.setLeft(LeftSideBar.sideBar(5, false,username));
        layout.setCenter(formBox);
        return layout;
    }

    private void setupStage(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setTitle("Help & Support");
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(800);
        primaryStage.show();
    }


}
