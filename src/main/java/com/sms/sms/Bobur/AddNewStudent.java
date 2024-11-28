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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AddNewStudent extends Application {
    String profile_image_url = "https://s-m-s.s3.eu-north-1.amazonaws.com/i1.png"; // Admin image

    @Override
    public void start(Stage primaryStage) {
        // Header with "Add New Student" label and admin name & text on the same level
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 80, 0, 80)); // Adjusted left padding to match form fields

        Label studentNameLabel = new Label("Add New Student");
        studentNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        studentNameLabel.setTextFill(Color.BLACK);

        VBox adminBox = new VBox(5);
        adminBox.setAlignment(Pos.CENTER_RIGHT);
        adminBox.setPadding(new Insets(10));

        Label adminNameLabel = new Label("Admin Name");
        adminNameLabel.setFont(new Font("Arial", 16));
        adminNameLabel.setTextFill(Color.BLACK);

        Label adminTextLabel = new Label("Admin");
        adminTextLabel.setFont(new Font("Arial", 14));
        adminTextLabel.setTextFill(Color.GRAY);

        adminBox.getChildren().addAll(adminNameLabel, adminTextLabel);

        ImageView adminImage = new ImageView(new Image(profile_image_url));
        adminImage.setFitWidth(60);
        adminImage.setFitHeight(60);
        adminImage.setPreserveRatio(true);

        Circle adminCircle = new Circle(30, 30, 30);
        StackPane adminPane = new StackPane(adminCircle, adminImage);
        adminPane.setAlignment(Pos.CENTER);

        HBox adminInfoBox = new HBox(10, adminBox, adminPane);
        adminInfoBox.setAlignment(Pos.CENTER_RIGHT);

        HBox.setHgrow(adminInfoBox, Priority.ALWAYS);

        header.getChildren().addAll(studentNameLabel, adminInfoBox);

        // Form fields
        VBox form = new VBox(20);
        form.setPadding(new Insets(40, 80, 20, 80));

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


        VBox content = new VBox(10, header, form);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(ChatScreen.sideBar());
        mainLayout.setCenter(content);
        BorderPane.setAlignment(ChatScreen.sideBar(), Pos.TOP_LEFT);

        Scene scene = new Scene(mainLayout);
        primaryStage.setTitle("Student Management System");
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
