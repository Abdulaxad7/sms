package com.sms.sms.Bobur;

import com.sms.sms.achanges.ChatScreen;
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
import javafx.stage.Stage;

public class Settings extends Application {

    String profile_image_url = "https://listinstorage.s3.eu-north-1.amazonaws.com/i1.png";

    @Override
    public void start(Stage primaryStage) {
        // Header with student avatar and name
        ImageView studentImage = new ImageView(new Image(profile_image_url));
        studentImage.setFitWidth(100);
        studentImage.setFitHeight(100);
        studentImage.setPreserveRatio(true);  // Ensure the image maintains its aspect ratio
        studentImage.setSmooth(true);  // Make sure the image is rendered smoothly

        Label studentNameLabel = new Label("Student Name");
        studentNameLabel.setFont(new Font("Arial", 36));
        studentNameLabel.setTextFill(Color.BLACK);

        HBox header = new HBox(20, studentImage, studentNameLabel);
        header.setPadding(new Insets(20));
        header.setAlignment(Pos.CENTER_LEFT);

        // Form fields
        VBox form = new VBox(20);
        form.setPadding(new Insets(40, 80, 20, 80));

        form.getChildren().add(header);

        String[] labels = {"Username", "Email", "Phone", "Address", "Password"};
        for (String label : labels) {
            Label lbl = new Label(label);
            lbl.setFont(new Font("Arial", 18));

            TextField inputField = label.equals("Password") ? new PasswordField() : new TextField();
            inputField.setStyle("-fx-background-color: #e6e6fa; -fx-border-radius: 15; -fx-background-radius: 15;");
            inputField.setPrefHeight(40);

            VBox fieldBox = new VBox(5, lbl, inputField);
            form.getChildren().add(fieldBox);
        }

        Button saveButton = new Button("Save");
        saveButton.setFont(new Font("Arial", 18));
        saveButton.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white; -fx-border-radius: 20; -fx-background-radius: 20;");
        saveButton.setPrefSize(100, 40);

        HBox saveButtonBox = new HBox(saveButton);
        saveButtonBox.setAlignment(Pos.CENTER);
        form.getChildren().add(saveButtonBox);

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(ChatScreen.sideBar());
        mainLayout.setCenter(form);

        Scene scene = new Scene(mainLayout, 1200, 800);
        primaryStage.setTitle("Student Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}