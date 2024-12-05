package com.sms.sms.user;

import com.sms.sms.bars.leftBar.LeftSideBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.sms.sms.styles.Colors.*;
import static com.sms.sms.styles.Images.MAIL_ICON;
import static com.sms.sms.styles.Images.PHONE_ICON;

public class SupportScreen {
    private TextField textField = new TextField();
    private TextField textField2 = new TextField();
    private TextArea textArea = new TextArea();

    public Scene scene(String username) {
        HBox header = createHeader();

        VBox formBox = createForm(header);

        BorderPane layout = createMainLayout(formBox, username);

        return new Scene(layout, 1000, 800);
    }


    private HBox createHeader() {
        HBox header = new HBox(50);
        header.setPadding(new Insets(10));
        header.setStyle(CREATE_HEADER);
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
            textArea = new TextArea();
            textArea.setStyle(CREATE_INPUT_HEADER);
            textArea.setFont(new Font("Arial", 18));
            textArea.setPrefRowCount(5);
            return new VBox(label, textArea);
        } else if (labelText.equals("Email")) {
            textField = new TextField();
            textField.setStyle(CREATE_INPUT_HEADER);
            textField.setFont(new Font("Arial", 18));
            textField.setAlignment(Pos.CENTER_LEFT);
            return new VBox(label, textField);
        } else {
            textField2 = new TextField();
            textField2.setStyle(CREATE_INPUT_HEADER);
            textField2.setFont(new Font("Arial", 18));
            textField2.setAlignment(Pos.CENTER_LEFT);
            return new VBox(label, textField2);
        }
    }

    private HBox createSubmitButton() {
        Button sendButton = new Button("Send");
        sendButton.setFont(new Font("Arial", 18));
        sendButton.setAlignment(Pos.CENTER);
        sendButton.setStyle(CREATE_SUBMIT_BUTTON);
        sendButton.setPrefSize(100, 40);
        sendButton.setOnAction(event -> handleFormSubmission());
        HBox hBox = new HBox(sendButton);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private void handleFormSubmission() {
        clearFormFields();

        showConfirmationStage();
    }

    private void clearFormFields() {
        textArea.clear();
        textField.clear();
        textField2.clear();
    }


    private void showConfirmationStage() {
        Stage confirmationStage = new Stage();
        confirmationStage.setTitle("Request Submitted");

        Path tick = new Path();
        tick.setStroke(Color.GREEN);
        tick.setStrokeWidth(4);
        tick.setFill(Color.TRANSPARENT);

        MoveTo moveTo = new MoveTo(10, 20);
        LineTo line1 = new LineTo(20, 35);
        LineTo line2 = new LineTo(40, 10);

        tick.getElements().addAll(moveTo, line1, line2);
        StackPane tickPane = new StackPane(tick);
        tickPane.setPrefSize(50, 50);
        tickPane.setStyle("-fx-background-color: #e8f5e9; " +
                "-fx-border-color: #c8e6c9; " +
                "-fx-border-radius: 25; " +
                "-fx-background-radius: 25;");

        Label confirmationMessage = new Label("Your request has been successfully submitted!");
        confirmationMessage.setFont(new Font("Arial", 16));
        confirmationMessage.setTextFill(Color.DARKSLATEBLUE);
        confirmationMessage.setWrapText(true);
        confirmationMessage.setAlignment(Pos.CENTER);

        HBox messageBox = new HBox(10, tickPane, confirmationMessage);
        messageBox.setAlignment(Pos.CENTER);
        messageBox.setPadding(new Insets(10));

        Button closeButton = getButton(confirmationStage);

        // Main layout container
        VBox layout = new VBox(20, messageBox, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f3f4f6; " +
                "-fx-border-color: #6c63ff; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 10, 0, 0, 4);");

        // Scene setup
        Scene scene = new Scene(layout, 400, 250);
        confirmationStage.setScene(scene);
        confirmationStage.show();
    }

    @NotNull
    private static Button getButton(Stage confirmationStage) {
        Button closeButton = new Button("Close");
        closeButton.setFont(new Font("Arial", 14));
        closeButton.setStyle("-fx-background-color: #6c63ff; " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 10; " +
                "-fx-padding: 10;");
        closeButton.setOnAction(event -> confirmationStage.close());
        closeButton.setOnMouseEntered(e -> closeButton.setStyle(
                "-fx-background-color: #514dc1; -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 10;"));
        closeButton.setOnMouseExited(e -> closeButton.setStyle(
                "-fx-background-color: #6c63ff; -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 10;"));
        return closeButton;
    }


    private BorderPane createMainLayout(VBox formBox, String username) {
        BorderPane layout = new BorderPane();
        layout.setLeft(LeftSideBar.sideBar(5, false, username));
        layout.setCenter(formBox);
        return layout;
    }


}
