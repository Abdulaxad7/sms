package com.sms.sms.User;

import com.sms.sms.Admin.AddNewStudent;
import com.sms.sms.db.service.StudentService;
import com.sms.sms.leftbar.LeftSideBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static com.sms.sms.security.service.LoginServiceImpl.loggedInUsers;
import static com.sms.sms.styles.Images.PROFILE_IMAGE_URL;

public class Settings {


    public Scene scene(Stage primartStage,String username) {
        HBox header = createHeader(username);
        VBox form = new VBox();
        form.getChildren().addAll(header, AddNewStudent.createForm(primartStage, "student"));
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(LeftSideBar.sideBar(3, false,username));
        mainLayout.setCenter(form);

        return new Scene(mainLayout, 1000, 800);
    }

    private HBox createHeader(String username) {
        ImageView studentImage = new ImageView(new Image(PROFILE_IMAGE_URL));
        studentImage.setFitWidth(100);
        studentImage.setFitHeight(100);
        studentImage.setPreserveRatio(true);
        studentImage.setSmooth(true);

        Label studentNameLabel = new Label(StudentService.findById(loggedInUsers.get(username)).getFullName());
        studentNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        studentNameLabel.setTextFill(Color.BLACK);

        HBox header = new HBox(20, studentImage, studentNameLabel);
        header.setPadding(new Insets(20));
        header.setAlignment(Pos.CENTER_LEFT);

        return header;
    }
}
