package com.sms.sms.User.studentInfo;

import com.sms.sms.User.entity.Grade;
import com.sms.sms.User.studentInfo.service.StudentInfoServiceImpl;
import com.sms.sms.leftbar.LeftSideBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.beans.property.SimpleStringProperty;

import static com.sms.sms.styles.Colors.*;
import static com.sms.sms.styles.Images.AVATAR_ICON1;

public class StudentInfoImpl implements StudentInfo {
    private final StudentInfoServiceImpl infoService = new StudentInfoServiceImpl();
    public Scene scene() {
        BorderPane mainLayout = new BorderPane();

        mainLayout.setLeft(LeftSideBar.sideBar(1,false));
        mainLayout.setCenter(createCenterContent());

        return new Scene(mainLayout, 1000, 800);
    }


    public VBox createTopBar() {
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(10));
        topBar.setStyle(TOP_BAR);
        topBar.setAlignment(Pos.CENTER_LEFT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        VBox userInfo = new VBox();
        Label userName = new Label("Username");
        Label userAuthority = new Label("Authority");
        userName.setStyle(USERNAME);
        userAuthority.setStyle(USER_AUTHORITY);
        userInfo.getChildren().addAll(userName, userAuthority);
        userInfo.setAlignment(Pos.CENTER_RIGHT);

        ImageView userPicture = new ImageView();
        userPicture.setFitWidth(40);
        userPicture.setFitHeight(40);
        userPicture.setStyle(BORDER_COLOR);
        userPicture.setImage(new Image(AVATAR_ICON1));

        topBar.getChildren().addAll(spacer, userInfo, userPicture);

        return new VBox(topBar);
    }

    public VBox createTitleBox() {
        VBox titleBox = new VBox(5);
        titleBox.setPadding(new Insets(10));
        titleBox.setAlignment(Pos.TOP_LEFT);

        Label title = new Label("Total Grade");
        title.setStyle(TITLE_BOX);

        Label studentNameLabel = new Label("Student Name");
        studentNameLabel.setStyle(TITLE_BOX2);

        titleBox.getChildren().addAll(title, studentNameLabel);

        return titleBox;
    }

    public TableView<Grade> createGradeTable() {
        TableView<Grade> gradeTable = new TableView<>();
        gradeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        gradeTable.setStyle(GRADE_TABLE);

        String[] columnNames = {"Course", "Attendance", "Assignment", "Quiz", "MidTerm", "FinalTerm", "Total", "Grade"};

        for (int i = 0; i < columnNames.length; i++) {
            final int colIndex = i;
            TableColumn<Grade, String> column = new TableColumn<>(columnNames[i]);
            column.setReorderable(false);
            column.setStyle(TABLE_COLUMN);
            column.setCellValueFactory(cellData -> createCellValue(cellData.getValue(), colIndex));

            gradeTable.getColumns().add(column);
        }

        gradeTable.setItems(infoService.getSampleData());
        return gradeTable;
    }

    public SimpleStringProperty createCellValue(Grade entry, int colIndex) {
        return switch (colIndex) {
            case 0 -> new SimpleStringProperty(entry.getTitle());
            case 1 -> new SimpleStringProperty(entry.getAttendance());
            case 2 -> new SimpleStringProperty(entry.getAssignment());
            case 3 -> new SimpleStringProperty(entry.getQuiz());
            case 4 -> new SimpleStringProperty(entry.getMidTerm());
            case 5 -> new SimpleStringProperty(entry.getFinalTerm());
            case 6 -> new SimpleStringProperty(entry.getTotal());
            case 7 -> new SimpleStringProperty(entry.getGrade());
            default -> null;
        };
    }

    public VBox createCenterContent() {
        VBox centerContent = new VBox(10, createTopBar(), createTitleBox(), createGradeTable());
        centerContent.setPadding(new Insets(10));
        return centerContent;
    }
}
