package com.sms.sms.bars.form;

import com.sms.sms.admin.AboutStudents;
import com.sms.sms.db.service.StudentService;
import com.sms.sms.user.entity.Student;
import com.sms.sms.user.mapper.StudentMapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.sms.sms.security.service.LoginServiceImpl.loggedInUsers;
import static com.sms.sms.styles.Colors.CREATE_FORM;
import static com.sms.sms.styles.Colors.INPUT_STYLE;
import static com.sms.sms.styles.Images.AVATAR_ICON1;


public class Form {
    Map<String, TextField> inputFields = new HashMap<>();
    StudentMapper studentMapper = new StudentMapper();
    Font LABEL_FONT = Font.font("Arial", 18);
    Font BUTTON_FONT = Font.font("Arial", 18);
    AboutStudents students = new AboutStudents();


    public VBox createForm(Stage primartStage, Boolean isStudent, String username) {
        VBox form = new VBox(20);
        form.setPadding(new Insets(40, 80, 20, 80));

        String[] labels = isStudent ? new String[]{"Username", "Email", "Phone", "Address", "Password"} :
                new String[]{"FullName", "Username", "Email", "Phone", "Address", "Password"};
        if (isStudent) {
            for (String label : labels)
                form.getChildren().add(createFormField(label, true, String
                        .valueOf(StudentService.findFieldByUsername(label.toLowerCase(),username))));
        } else
            for (String label : labels)
                form.getChildren().add(createFormField(label, false, username));


        final var saveButton = getButton(primartStage, isStudent,username);

        HBox saveButtonBox = new HBox(saveButton);
        saveButtonBox.setAlignment(Pos.CENTER);

        form.getChildren().add(saveButtonBox);
        return form;
    }

    public VBox createFormField(String labelText, Boolean isStudent, String text) {
        System.out.println(labelText + "   " + isStudent + "   " + text);
        Label label = new Label(labelText);
        label.setFont(LABEL_FONT);

        TextField inputField = labelText.equals("Password") ? new PasswordField() : new TextField();
        inputField.setStyle(INPUT_STYLE);
        inputField.setPrefHeight(40);
        if (isStudent && labelText.equals("Username"))
            inputField.setDisable(true);
        if (isStudent && !labelText.equals("Password")) {
            inputField.setText(text);
        }

        inputFields.put(labelText, inputField);

        return new VBox(5, label, inputField);
    }


    public Button getButton(Stage primartStage, Boolean isStudent, String username) {
        Button saveButton = new Button("Save");
        saveButton.setFont(BUTTON_FONT);
        saveButton.setStyle(CREATE_FORM);
        saveButton.setPrefSize(100, 40);
        saveButton.setOnAction(event -> {
            if (isStudent){
                System.out.println(inputFields);
                System.out.printf("Student data has been changed:\n{%s}\n", StudentService.
                        updateStudent(
                                studentMapper
                                        .toStudent(inputFields,
                                                StudentService.findById(loggedInUsers.get(username)).getFullName(),
                                                StudentService.findById(loggedInUsers.get(username)).getPassword(),
                                                StudentService.findById(loggedInUsers.get(username)).getId()
                                        )));
            }
            else{
                StudentService
                        .persistNewStudent(
                                studentMapper
                                        .toStudent(inputFields,null,null,null));
                primartStage.setScene(students.scene(primartStage,username));
            }


        });
        return saveButton;
    }

    public void showValidationError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
