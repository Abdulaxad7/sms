package com.sms.sms.bars.leftBar;

import com.sms.sms.admin.AboutStudentsImpl;
import com.sms.sms.user.*;
import com.sms.sms.user.StudentInfoImpl;
import com.sms.sms.db.service.StudentService;
import com.sms.sms.security.LoginScreenImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static com.sms.sms.security.service.LoginServiceImpl.loggedInUsers;
import static com.sms.sms.styles.Colors.SIDE_BAR1;
import static com.sms.sms.styles.Colors.SIDE_BAR2;
import static com.sms.sms.styles.Images.*;

public class LeftSideBar {
    private static final StudentInfoImpl studentInfo = new StudentInfoImpl();
    private static final ChatScreenImpl chatScreen = new ChatScreenImpl();
    private static final CoursesScreenImpl courseScreen = new CoursesScreenImpl();
    private static final SupportScreenImpl supportScreen = new SupportScreenImpl();
    private static final LoginScreenImpl loginScreen = new LoginScreenImpl();
    private static final SettingsScreenImpl settingsScreen = new SettingsScreenImpl();
    private static final AboutStudentsImpl students = new AboutStudentsImpl();
    public static Stage primaryStage;


    public static VBox sideBar(int i, boolean isAdmin,String username) {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle(SIDE_BAR1);

        ImageView logo = new ImageView(LOGO);
        logo.setFitHeight(100);
        logo.setFitWidth(100);
        logo.setPreserveRatio(true);

        Label titleLabel = new Label("Student management system");
        titleLabel.setFont(new Font("Arial", 14));
        titleLabel.setTextFill(Color.LIGHTGRAY);

        String[] texts = {"Courses", "Total Grade", "Chat", "Settings", "Logout", "Help"};
        switch (i) {
            case 0 -> texts[0] += "   <";
            case 1 -> texts[1] += "   <";
            case 2 -> texts[2] += "   <";
            case 3 -> texts[3] += "   <";
            case 5 -> texts[5] += "   <";
        }
        Region spacer = new Region();
        spacer.setPrefHeight(5);
        Region spacer1 = new Region();
        spacer1.setPrefHeight(10);


        Button coursesButton, gradesButton, chatButton, settingsButton, logoutButton, helpButton;
        if (!isAdmin) {
            coursesButton = createSidebarButton(texts[0], COURSES_ICON);
            gradesButton = createSidebarButton(texts[1], GRADES_ICON);
            chatButton = createSidebarButton(texts[2], CHAT_ICON);
            settingsButton = createSidebarButton(texts[3], SETTINGS_ICON);
            logoutButton = createSidebarButton(texts[4], LOGOUT_ICON);
            helpButton = createSidebarButton(texts[5], HELP_ICON);

            coursesButton.setOnAction(actionEvent -> primaryStage.setScene(courseScreen.scene(StudentService.findById(loggedInUsers.get(username)).getUsername())));
            gradesButton.setOnAction(actionEvent -> primaryStage.setScene(studentInfo.scene(StudentService.findById(loggedInUsers.get(username)).getUsername())));
            chatButton.setOnAction(actionEvent -> primaryStage.setScene(chatScreen.scene(StudentService.findById(loggedInUsers.get(username)).getUsername())));
            settingsButton.setOnAction(actionEvent -> primaryStage.setScene(settingsScreen.scene(primaryStage,StudentService.findById(loggedInUsers.get(username)).getUsername())));
            logoutButton.setOnAction(actionEvent -> loginScreen.scene());
            helpButton.setOnAction(actionEvent -> primaryStage.setScene(supportScreen.scene(StudentService.findById(loggedInUsers.get(username)).getUsername())));

            Region spacer2 = new Region();
            spacer2.setPrefHeight(40);
            sidebar.getChildren().addAll(
                    logo,
                    spacer,
                    titleLabel,
                    spacer1,
                    coursesButton,
                    gradesButton,
                    chatButton,
                    settingsButton,
                    spacer2,
                    logoutButton,
                    helpButton);

        } else {
            Button studentTable = createSidebarButton("Students   <", STUDENTS_ICON);
            logoutButton = createSidebarButton(texts[4], LOGOUT_ICON);
            logoutButton.setOnAction(actionEvent -> loginScreen.scene());
            studentTable.setOnAction(actionEvent -> primaryStage.setScene(students.scene(primaryStage,username)));
            sidebar.getChildren().addAll(
                    logo,
                    spacer,
                    titleLabel,
                    spacer1,
                    studentTable,
                    logoutButton);
        }

        return sidebar;
    }


    private static Button createSidebarButton(String text, String iconUrl) {
        Button button = new Button(text);
        button.setStyle(SIDE_BAR2);
        button.setPrefWidth(150);
        button.setAlignment(Pos.CENTER_LEFT);

        if (iconUrl != null && !iconUrl.isEmpty()) {
            ImageView icon = new ImageView(new Image(iconUrl));
            icon.setFitWidth(16);
            icon.setFitHeight(16);
            button.setGraphic(icon);
        }

        return button;
    }

}
