package com.sms.sms.Bobur;

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

    String logo_url = "https://listinstorage.s3.eu-north-1.amazonaws.com/logo.png";
    String profile_image_url = "https://listinstorage.s3.eu-north-1.amazonaws.com/i1.png"; // Admin image

    private VBox sideBar() {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(30, 0, 30, 0));
        sidebar.setStyle("-fx-background-color: #1a1a2e;");
        sidebar.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(sidebar, Priority.ALWAYS);

        ImageView logo = new ImageView(new Image(logo_url));
        logo.setFitHeight(100);
        logo.setFitWidth(100);
        logo.setPreserveRatio(true);

        Circle logoCircle = new Circle(50, 50, 50);
        StackPane logoPane = new StackPane(logoCircle, logo);
        logoPane.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Student Management System");
        titleLabel.setFont(new Font("Arial", 14));
        titleLabel.setTextFill(Color.LIGHTGRAY);

        VBox menu = new VBox(15);
        menu.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(20, 10, 20, 10));

        Button studentsButton = createSidebarButton("Students", "");
        Button logoutButton = createSidebarButton("Logout", "");

        menu.getChildren().addAll(studentsButton, logoutButton);
        sidebar.getChildren().addAll(logoPane, titleLabel, menu);

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
        // Top toolbar with search functionality and admin image
        HBox topToolbar = new HBox(15);
        topToolbar.setAlignment(Pos.CENTER_RIGHT);
        topToolbar.setPadding(new Insets(15, 15, 15, 15)); // Added top padding

        TextField searchField = new TextField();
        searchField.setPromptText("Search...");
        searchField.setPrefHeight(40);
        searchField.setStyle("-fx-background-color: #e6e6fa; -fx-border-radius: 15; -fx-background-radius: 15;");

        Button searchButton = new Button("Search");
        searchButton.setFont(new Font("Arial", 16));
        searchButton.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white; -fx-border-radius: 15; -fx-background-radius: 15;");
        searchButton.setPrefSize(100, 40);

        ImageView adminImage = new ImageView(new Image(profile_image_url));
        adminImage.setFitWidth(60);
        adminImage.setFitHeight(60);
        adminImage.setPreserveRatio(true);

        Circle adminCircle = new Circle(30, 30, 30);
        StackPane adminPane = new StackPane(adminCircle, adminImage);
        adminPane.setAlignment(Pos.CENTER);

        topToolbar.getChildren().addAll(searchField, searchButton, adminPane);

        // Form fields
        VBox form = new VBox(20);
        form.setPadding(new Insets(40, 80, 20, 80));

        Label studentNameLabel = new Label("Add New Student");
        studentNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        studentNameLabel.setTextFill(Color.BLACK);

        form.getChildren().add(studentNameLabel);

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
        VBox sidebar = sideBar();
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(sidebar);
        mainLayout.setTop(topToolbar);
        mainLayout.setCenter(form);
        BorderPane.setAlignment(sidebar, Pos.TOP_LEFT); // Align sidebar to the top

        Scene scene = new Scene(mainLayout, 1200, 800);
        primaryStage.setTitle("Student Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
