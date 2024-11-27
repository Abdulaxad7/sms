package com.sms.sms.kamron;

import com.sms.sms.achanges.ChatScreen;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentInfo extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Left Navigation Panel
        VBox navPanel = new VBox(20);
        navPanel.setPadding(new Insets(20));
        navPanel.setStyle("-fx-background-color: #2D3E50;");
        navPanel.setPrefWidth(220);

        Label smsLabel = new Label("Student Management System");
        smsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        Button coursesButton = new Button("Courses");
        Button totalGradeButton = new Button("Total Grade");
        Button chatButton = new Button("Chat");
        Button settingsButton = new Button("Settings");
        Button logoutButton = new Button("Logout");
        Button helpButton = new Button("Help");

        for (Button btn : new Button[]{coursesButton, totalGradeButton, chatButton, settingsButton, logoutButton, helpButton}) {
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
            btn.setAlignment(Pos.CENTER_LEFT);
        }

        navPanel.getChildren().addAll(smsLabel, coursesButton, totalGradeButton, chatButton, settingsButton, logoutButton, helpButton);

        // Top Bar with TextField and User Info
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #F4F4F4;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        TextField searchField = new TextField();
        searchField.setPromptText("Explore courses...");
        searchField.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);  // Pushes user info to the right

        VBox userInfo = new VBox();
        Label userName = new Label("Username");
        Label userAuthority = new Label("Authority");
        userName.setStyle("-fx-font-size: 14px;");
        userAuthority.setStyle("-fx-font-size: 12px;");

        userInfo.getChildren().addAll(userName, userAuthority);
        userInfo.setAlignment(Pos.CENTER_RIGHT);

        // User Picture Placeholder
        ImageView userPicture = new ImageView();
        userPicture.setFitWidth(40);
        userPicture.setFitHeight(40);
        userPicture.setStyle("-fx-border-color: gray; -fx-border-radius: 20; -fx-background-radius: 20;");  // Placeholder border style

        userPicture.setImage(new Image("https://s-m-s.s3.eu-north-1.amazonaws.com/i1.png")); // Replace with actual image path

        topBar.getChildren().addAll(searchField, spacer, userInfo, userPicture);

        // Title and Student Name Below Search
        VBox titleBox = new VBox(5);
        titleBox.setPadding(new Insets(10));
        titleBox.setAlignment(Pos.TOP_LEFT);

        Label title = new Label("Total Grade");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;"); // Slightly smaller font

        Label studentNameLabel = new Label("Student Name");
        studentNameLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;"); // Bigger font than Total Grade

        titleBox.getChildren().addAll(title, studentNameLabel);

        VBox content = new VBox(10); // Create a vertical box layout with 10px spacing
        content.setPadding(new Insets(10)); // Add padding around the content
        content.setAlignment(Pos.TOP_CENTER); // Align content to the top-center

// Create TableView for Grades
        TableView<String> gradeTable = new TableView<>();
        gradeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Columns automatically resize to fit the table

// Define Columns with static headers and custom style
        String[] columns = {"Course", "Attendance", "Assignment", "Quiz", "MidTerm", "FinalTerm", "Total", "Grade"};
        for (String col : columns) {
            TableColumn<String, String> column = new TableColumn<>(col); // Create a new table column with the header text
            column.setReorderable(false); // Prevent the column from being reordered
            column.setStyle("-fx-alignment: CENTER; -fx-font-size: 14px; -fx-text-fill: white;"); // Style for the column headers
            gradeTable.getColumns().add(column); // Add column to the table
        }

// Set table style to have a dark background for better contrast
        gradeTable.setStyle("-fx-background-color: #2D3E50; -fx-table-cell-border-color: transparent;");

// Add the table to the content VBox
        content.getChildren().add(gradeTable);


        // Main Layout with Sidebar and Content
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(ChatScreen.sideBar());

        VBox mainContent = new VBox(topBar, titleBox, content);  // TopBar, Titles, Table
        mainLayout.setCenter(mainContent);

        // Set sidebar to extend full height
        VBox.setVgrow(navPanel, Priority.ALWAYS);
        VBox.setVgrow(mainContent, Priority.ALWAYS);

        Scene scene = new Scene(mainLayout, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}