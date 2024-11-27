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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BoburUI extends Application {



    String logo_url = "https://listinstorage.s3.eu-north-1.amazonaws.com/logo.png";


    private VBox sideBar() {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(30, 0, 30, 0));
        sidebar.setStyle("-fx-background-color: #1a1a2e;");
        sidebar.setAlignment(Pos.TOP_CENTER);

        // Logo with circular background
        ImageView logo = new ImageView(new Image(logo_url));
        logo.setFitHeight(100);
        logo.setFitWidth(100);
        logo.setPreserveRatio(true);

        Circle logoCircle = new Circle(50);
        logoCircle.setFill(Color.WHITE);
        logo.setClip(logoCircle);

        Label titleLabel = new Label("Student Management System");
        titleLabel.setFont(new Font("Arial", 14));
        titleLabel.setTextFill(Color.LIGHTGRAY);

        // Sidebar buttons with icons
        VBox menu = new VBox(15);
        menu.setAlignment(Pos.TOP_LEFT);
        menu.setPadding(new Insets(20, 10, 20, 10));

        Button coursesButton = createSidebarButton("Courses", "");
        Button gradesButton = createSidebarButton("Total Grade", "");
        Button chatButton = createSidebarButton("Chat", "");
        Button settingsButton = createSidebarButton("Settings", "⚙");
        Button logoutButton = createSidebarButton("Logout", "");
        Button helpButton = createSidebarButton("Help", "");

        menu.getChildren().addAll(coursesButton, gradesButton, chatButton, settingsButton, logoutButton, helpButton);

        sidebar.getChildren().addAll(logo, titleLabel, menu);
        return sidebar;
    }

    private Button createSidebarButton(String text, String icon) {
        Button button = new Button(icon + "  " + text);
        button.setFont(new Font("Arial", 16));
        button.setStyle("-fx-background-color: #16213e; -fx-text-fill: white; -fx-padding: 10;");
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPrefSize(200, 40);
        return button;
    }

    @Override
    public void start(Stage primaryStage) {
        // Header with student avatar and name
        ImageView studentImage = new ImageView(new Image(logo_url));
        studentImage.setFitWidth(100);
        studentImage.setFitHeight(100);
        studentImage.setClip(new Circle(50)); // Circular avatar

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
        mainLayout.setLeft(sideBar());
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