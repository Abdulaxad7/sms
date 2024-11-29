package com.sms.sms.kamron;

import com.sms.sms.achanges.ChatScreen;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

        VBox navPanel = new VBox(20);
        navPanel.setPadding(new Insets(20));
        navPanel.setStyle("-fx-background-color: #aaaafb;");
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

        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #F4F4F4;");
        topBar.setAlignment(Pos.CENTER_LEFT);


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        VBox userInfo = new VBox();
        Label userName = new Label("Username");
        Label userAuthority = new Label("Authority");
        userName.setStyle("-fx-font-size: 14px;");
        userAuthority.setStyle("-fx-font-size: 12px;");

        userInfo.getChildren().addAll(userName, userAuthority);
        userInfo.setAlignment(Pos.CENTER_RIGHT);


        ImageView userPicture = new ImageView();
        userPicture.setFitWidth(40);
        userPicture.setFitHeight(40);
        userPicture.setStyle("-fx-border-color: gray; -fx-border-radius: 20; -fx-background-radius: 20;");

        userPicture.setImage(new Image("https://s-m-s.s3.eu-north-1.amazonaws.com/i1.png"));

        topBar.getChildren().addAll(spacer, userInfo, userPicture);


        VBox titleBox = new VBox(5);
        titleBox.setPadding(new Insets(10));
        titleBox.setAlignment(Pos.TOP_LEFT);

        Label title = new Label("Total Grade");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");


        Label studentNameLabel = new Label("Student Name");
        studentNameLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        titleBox.getChildren().addAll(title, studentNameLabel);

        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.setAlignment(Pos.TOP_CENTER);

        TableView<GradeEntry> gradeTable = new TableView<>();

        gradeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        String[] columnNames = {"Course", "Attendance", "Assignment", "Quiz", "MidTerm", "FinalTerm", "Total", "Grade"};
        gradeTable.setStyle("-fx-background-color: #f9f9f9; -fx-table-cell-border-color: #dddddd;");

        for (int i = 0; i < columnNames.length; i++) {
            final int colIndex = i;
            TableColumn<GradeEntry, String> column = new TableColumn<>(columnNames[i]);
            column.setReorderable(false);

            column.setStyle("-fx-background-color: #d8e4f6; -fx-text-fill: white; -fx-alignment: CENTER;");

            column.setCellValueFactory(cellData -> {
                GradeEntry entry = cellData.getValue();
                return switch (colIndex) {
                    case 0 -> new javafx.beans.property.SimpleStringProperty(entry.course());
                    case 1 -> new javafx.beans.property.SimpleStringProperty(entry.attendance());
                    case 2 -> new javafx.beans.property.SimpleStringProperty(entry.assignment());
                    case 3 -> new javafx.beans.property.SimpleStringProperty(entry.quiz());
                    case 4 -> new javafx.beans.property.SimpleStringProperty(entry.midTerm());
                    case 5 -> new javafx.beans.property.SimpleStringProperty(entry.finalTerm());
                    case 6 -> new javafx.beans.property.SimpleStringProperty(entry.total());
                    case 7 -> new javafx.beans.property.SimpleStringProperty(entry.grade());
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

            gradeTable.getColumns().add(column);
        }


        gradeTable.setStyle("-fx-background-color: #e6e6fa; -fx-table-cell-border-color: transparent;");

        ObservableList<GradeEntry> data = FXCollections.observableArrayList(
                new GradeEntry("Mathematics", "95%", "90%", "92%", "88%", "91%", "91%", "A"),
                new GradeEntry("Computer Science", "92%", "95%", "90%", "93%", "94%", "93%", "A"),
                new GradeEntry("Physics", "88%", "85%", "87%", "90%", "89%", "89%", "B+"),
                new GradeEntry("Chemistry", "90%", "92%", "85%", "87%", "88%", "88%", "B")
        );

        gradeTable.setItems(data);

        content.getChildren().add(gradeTable);


        VBox centerContent = new VBox(10,topBar, titleBox, content);
        centerContent.setPadding(new Insets(10));

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(ChatScreen.sideBar(1,false));
        mainLayout.setCenter(centerContent);

        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Information");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
record GradeEntry(
        String course,
        String attendance,
        String assignment,
        String quiz,
        String midTerm,
        String finalTerm,
        String total,
        String grade
) {}