package com.sms.sms.security;

import com.sms.sms.Admin.AboutStudents;
import com.sms.sms.User.CoursesScreen;
import com.sms.sms.User.entity.Course;
import com.sms.sms.User.entity.Grade;
import com.sms.sms.User.entity.Student;
import com.sms.sms.db.HibernateUtil;
import com.sms.sms.leftbar.LeftSideBar;
import com.sms.sms.security.service.LoginServiceImpl;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.hibernate.Session;


import java.util.List;

import static com.sms.sms.styles.Colors.*;
import static com.sms.sms.styles.Images.ILLUSTRATION_ICON;
import static com.sms.sms.styles.Images.WHITE_LOGO;

public class LoginScreenImpl extends Application implements LoginScreen {
    private final AboutStudents students = new AboutStudents();
    private final CoursesScreen courseScreen = new CoursesScreen();
    private final LoginServiceImpl loginService = new LoginServiceImpl();
    public void scene() {
        LeftSideBar.primaryStage.close();
        start(new Stage());
    }
    @Override
    public void start(Stage primaryStage) {
        HBox root = new HBox();
        root.setStyle(START_STYLE);

        root.getChildren().addAll(leftPane(), rightPane(primaryStage));

        Scene scene = new Scene(root, 1000, 800);

        configurePrimaryStage(primaryStage, scene);
        primaryStage.show();
    }

    private VBox leftPane() {
        VBox leftPane = new VBox(20);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPadding(new Insets(20));
        leftPane.setStyle(START_STYLE);

        ImageView logo = new ImageView(WHITE_LOGO);
        logo.setFitHeight(150);
        logo.setFitWidth(150);
        logo.setPreserveRatio(true);

        ImageView illustration = new ImageView(ILLUSTRATION_ICON);
        illustration.setPreserveRatio(true);

        illustration.fitWidthProperty().bind(leftPane.widthProperty().multiply(0.8));
        illustration.fitHeightProperty().bind(leftPane.heightProperty().multiply(0.6));

        leftPane.getChildren().addAll(logo, illustration);

        leftPane.setPrefWidth(500);
        HBox.setHgrow(leftPane, Priority.ALWAYS);
        leftPane.setMinWidth(300);

        return leftPane;
    }

    public VBox rightPane(Stage primaryStage) {
        VBox rightPane = new VBox(20);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPadding(new Insets(40));
        rightPane.setStyle(RIGHT_PANE);

        Label welcomeLabel = new Label("Welcome Back!");
        welcomeLabel.setFont(Font.font("System", FontWeight.BOLD, 32));
        welcomeLabel.setTextFill(Color.WHITE);
        welcomeLabel.setWrapText(true);

        HBox accountPrompt = new HBox(5);
        accountPrompt.setAlignment(Pos.CENTER);
        Label noAccountLabel = new Label("Don't have an account yet?");
        noAccountLabel.setTextFill(Color.WHITE);
        Hyperlink contactLink = new Hyperlink("Contact us");
        contactLink.setTextFill(Color.web("#8B9FFF"));
        accountPrompt.getChildren().addAll(noAccountLabel, contactLink);

        rightPane.setPrefWidth(400);

        HBox.setHgrow(rightPane, Priority.ALWAYS);
        rightPane.getChildren().addAll(
                welcomeLabel,
                accountPrompt,
                formBox(rightPane, primaryStage)
        );
        rightPane.setMinWidth(300);
        return rightPane;
    }

    public VBox formBox(VBox rightPane, Stage primaryStage) {
        VBox formBox = new VBox(10);
        formBox.setAlignment(Pos.CENTER);
        formBox.maxWidthProperty().bind(rightPane.widthProperty().multiply(0.9));
        formBox.minWidthProperty().bind(rightPane.widthProperty().multiply(0.5));

        Label usernameLabel = new Label("Username");
        usernameLabel.setTextFill(Color.WHITE);
        TextField usernameField = new TextField();
        usernameField.setStyle(FORM_BOX1);
        usernameField.setPrefHeight(40);
        usernameField.maxWidthProperty().bind(formBox.widthProperty());

        Label passwordLabel = new Label("Password");
        passwordLabel.setTextFill(Color.WHITE);
        PasswordField passwordField = new PasswordField();
        passwordField.setStyle(FORM_BOX1);
        passwordField.setPrefHeight(40);
        passwordField.maxWidthProperty().bind(formBox.widthProperty());

        HBox optionsBox = new HBox();
        optionsBox.setAlignment(Pos.CENTER_LEFT);
        optionsBox.maxWidthProperty().bind(formBox.widthProperty());
        CheckBox rememberMe = new CheckBox("Keep me logged in");
        rememberMe.setTextFill(Color.WHITE);
        Hyperlink forgotPassword = new Hyperlink("Forgot Password?");
        forgotPassword.setTextFill(Color.web("#8B9FFF"));
        HBox.setHgrow(forgotPassword, Priority.ALWAYS);
        forgotPassword.setAlignment(Pos.CENTER_RIGHT);
        optionsBox.getChildren().addAll(rememberMe, forgotPassword);
        Button loginButton = new Button("Login");
        loginButton.setStyle(FORM_BOX2);
        loginButton.maxWidthProperty().bind(formBox.widthProperty());
        loginButton.setOnAction(e -> {
            LeftSideBar.primaryStage = primaryStage;
            boolean [] isValid = loginService.validateCredentials(usernameField.getText(), passwordField.getText());
            if (isValid[0] && isValid[1]) {
                primaryStage.setScene(students.scene(primaryStage));
            } else if (isValid[1]) {
                primaryStage.setScene(courseScreen.scene());
            }
        });
        formBox.getChildren().addAll(
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                optionsBox,
                loginButton
        );

        return formBox;
    }

    public void configurePrimaryStage(Stage primaryStage, Scene scene) {
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(800);
        primaryStage.setTitle("SMS Login");
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        try {
            // Start a transaction
            session.beginTransaction();

            // Create a new User object
            Student student = Student.builder().name("name").username("uname").password("pass")
                    .courses(List.of(Course.builder().build()))
                    .grades(List.of(Grade.builder().build())).build();


            // Save the user object
            session.save(student);

            // Commit the transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        HibernateUtil.close();

        launch(args);
    }

}
