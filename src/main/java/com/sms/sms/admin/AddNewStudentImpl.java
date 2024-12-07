package com.sms.sms.admin;


import com.sms.sms.admin.repository.AddNewStudent;
import com.sms.sms.admin.service.AdminService;
import com.sms.sms.bars.form.Form;

import com.sms.sms.bars.leftBar.LeftSideBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import static com.sms.sms.security.service.LoginServiceImpl.loggedInUsers;

import static com.sms.sms.styles.Images.PROFILE_IMAGE_URL;

@Slf4j
public class AddNewStudentImpl extends Form implements AddNewStudent {

    public Scene scene(Stage primaryStage,String username) {
        HBox header = createHeader(username);

        VBox form = createForm(primaryStage,false,username);

        VBox content = new VBox(10, header, form);
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(LeftSideBar.sideBar(0, true, username));
        mainLayout.setCenter(content);

        return new Scene(mainLayout, 1000, 800);
    }

    public HBox createHeader(String username) {
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 80, 0, 80));

        Label studentNameLabel = new Label("Add New Student");
        studentNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        studentNameLabel.setTextFill(Color.BLACK);

        VBox adminBox = new VBox(5);
        adminBox.setAlignment(Pos.CENTER_RIGHT);
        adminBox.setPadding(new Insets(10));

        Label adminNameLabel = new Label(AdminService.findById(loggedInUsers.get(username)).getFullName());
        adminNameLabel.setFont(new Font("Arial", 16));
        adminNameLabel.setTextFill(Color.BLACK);

        Label adminTextLabel = new Label("Admin");
        adminTextLabel.setFont(new Font("Arial", 14));
        adminTextLabel.setTextFill(Color.GRAY);

        adminBox.getChildren().addAll(adminNameLabel, adminTextLabel);

        ImageView adminImage = new ImageView(PROFILE_IMAGE_URL);
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
        return header;
    }

}
