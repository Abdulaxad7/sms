package com.sms.sms.Admin;

import com.sms.sms.User.ChatScreen;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class AboutStudents extends Application {

    @Override
    public void start(Stage primaryStage) {
        HBox topBar = createTopBar();

        HBox titleBar = createTitleBar();

        TableView<Students> studentTable = createStudentTable();

        VBox centerContent = createCenterContent(topBar, titleBar, studentTable);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(ChatScreen.sideBar(0, true,null));
        mainLayout.setCenter(centerContent);

        Scene scene = new Scene(mainLayout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Management System");
        primaryStage.show();
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(20);
        topBar.setPadding(new Insets(10, 20, 10, 20));
        topBar.setStyle("-fx-background-color: #f4f4f4;");

        TextField searchField = createSearchField();

        ImageView searchIcon = new ImageView(new Image("https://s-m-s.s3.eu-north-1.amazonaws.com/searchIcon.png"));
        searchIcon.setFitWidth(30);
        searchIcon.setFitHeight(30);

        HBox searchBar = new HBox(10);
        searchBar.setAlignment(Pos.CENTER_LEFT);
        searchBar.getChildren().addAll(searchField, searchIcon);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        VBox profileSection = createProfileSection();

        topBar.getChildren().addAll(searchBar, spacer, profileSection);
        return topBar;
    }

    private TextField createSearchField() {
        TextField searchField = new TextField();
        searchField.setPromptText("Search student...");
        searchField.setStyle("-fx-background-color: #f4f4f4; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-border-color: #e0e0e0; -fx-border-width: 1px; -fx-padding: 10px 15px; -fx-font-size: 14px;");
        searchField.setPrefWidth(350);

        searchField.setOnMouseEntered(e -> searchField.setStyle(searchField.getStyle() + "-fx-border-color: #a0a0a0;"));
        searchField.setOnMouseExited(e -> searchField.setStyle(searchField.getStyle()));
        searchField.setOnMousePressed(e -> searchField.setStyle(searchField.getStyle() + "-fx-border-color: #4a90e2;"));
        return searchField;
    }

    private VBox createProfileSection() {
        VBox profileSection = new VBox(5);
        profileSection.setAlignment(Pos.CENTER_RIGHT);

        ImageView profileImage = new ImageView(new Image("https://s-m-s.s3.eu-north-1.amazonaws.com/i1.png"));
        profileImage.setFitHeight(40);
        profileImage.setFitWidth(40);

        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(new Font("Arial", 14));
        usernameLabel.setTextFill(Color.BLACK);

        Label authorityLabel = new Label("Authority");
        authorityLabel.setFont(new Font("Arial", 12));
        authorityLabel.setTextFill(Color.GRAY);

        profileSection.getChildren().addAll(profileImage, usernameLabel, authorityLabel);
        return profileSection;
    }

    private HBox createTitleBar() {
        HBox titleBar = new HBox(10);
        titleBar.setAlignment(Pos.CENTER_LEFT);
        titleBar.setPadding(new Insets(10));

        Label title = new Label("Students");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button addStudentButton = new Button("Add New Student");
        addStudentButton.setStyle("-fx-background-color: #c2c2f4; -fx-text-fill: black; -fx-font-size: 14px;");
        addStudentButton.setPadding(new Insets(5, 10, 5, 10));

        Region titleSpacer = new Region();
        HBox.setHgrow(titleSpacer, Priority.ALWAYS);

        titleBar.getChildren().addAll(title, titleSpacer, addStudentButton);
        return titleBar;
    }

    private TableView<Students> createStudentTable() {
        TableView<Students> studentTable = new TableView<>();
        studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        String[] columnNames = {"Student", "Email", "Phone", "Course"};
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn<Students, String> column = createTableColumn(i, columnNames[i]);
            studentTable.getColumns().add(column);
        }

        ObservableList<Students> data = FXCollections.observableArrayList(
                new Students("Alice Smith", "alice.smith@example.com", "123-456-7890", "Mathematics"),
                new Students("Bob Johnson", "bob.johnson@example.com", "987-654-3210", "Physics"),
                new Students("Charlie Brown", "charlie.brown@example.com", "456-123-7890", "Computer Science"),
                new Students("Diana Prince", "diana.prince@example.com", "321-654-9870", "Chemistry")
        );
        studentTable.setItems(data);
        return studentTable;
    }

    private TableColumn<Students, String> createTableColumn(int index, String columnName) {
        TableColumn<Students, String> column = new TableColumn<>(columnName);
        column.setReorderable(false);
        column.setStyle("-fx-background-color: #d8e4f6; -fx-text-fill: white; -fx-alignment: CENTER;");

        column.setCellValueFactory(cellData -> {
            Students entry = cellData.getValue();
            return switch (index) {
                case 0 -> new SimpleStringProperty(entry.name());
                case 1 -> new SimpleStringProperty(entry.email());
                case 2 -> new SimpleStringProperty(entry.phone());
                case 3 -> new SimpleStringProperty(entry.course());
                default -> null;
            };
        });

        column.setCellFactory(columnFactory -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });

        return column;
    }

    private VBox createCenterContent(HBox topBar, HBox titleBar, TableView<Students> studentTable) {
        VBox centerContent = new VBox(10, topBar, titleBar, studentTable);
        centerContent.setPadding(new Insets(10));
        return centerContent;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

record Students(String name, String email, String phone, String course) {}
