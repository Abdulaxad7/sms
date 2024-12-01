package com.sms.sms.Admin;

import com.sms.sms.User.entity.Student;
import com.sms.sms.db.service.StudentService;
import com.sms.sms.leftbar.LeftSideBar;
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

import static com.sms.sms.styles.Colors.CREATE_FORM;
import static com.sms.sms.styles.Colors.INPUT_STYLE;
import static com.sms.sms.styles.Images.PROFILE_IMAGE_URL;

public class AddNewStudent {
    private static final Font LABEL_FONT = Font.font("Arial", 18);
    private static final Font BUTTON_FONT = Font.font("Arial", 18);


    public Scene scene()  {
        HBox header = createHeader();

        VBox form = createForm();

        VBox content = new VBox(10, header, form);
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(LeftSideBar.sideBar(0, true));
        mainLayout.setCenter(content);

        return new Scene(mainLayout, 1000, 800);
    }

    private HBox createHeader() {
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 80, 0, 80));

        Label studentNameLabel = new Label("Add New Student");
        studentNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        studentNameLabel.setTextFill(Color.BLACK);

        VBox adminBox = new VBox(5);
        adminBox.setAlignment(Pos.CENTER_RIGHT);
        adminBox.setPadding(new Insets(10));

        Label adminNameLabel = new Label("Admin Name");
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

    public static VBox createForm() {
        VBox form = new VBox(20);
        form.setPadding(new Insets(40, 80, 20, 80));

        String[] labels = {"FullName","Username", "Email", "Phone", "Address", "Password"};
        for (String label : labels) {
            form.getChildren().add(createFormField(label));
        }

        Button saveButton = new Button("Save");
        saveButton.setFont(BUTTON_FONT);
        saveButton.setStyle(CREATE_FORM);
        saveButton.setPrefSize(100, 40);
        saveButton.setOnAction(event -> {
            if (saveButton.getText().trim().isEmpty()) {

                showValidationError("This field cannot be empty!");
                saveButton.setStyle(INPUT_STYLE + "; -fx-border-color: red;");
            } else {
                //todo -> Process form if validation passes
                saveButton.setStyle(INPUT_STYLE);  // Reset style
                StudentService.persistNewStudent(Student
                        .builder()
                        .name("Abdaulaxad")
                        .username("Is")
                        .email("a.i@gmail.com")
                        .phone("+99899999999")
                        .password("1234qwer")
                        .build()
                );
                System.out.println("Form submitted with: " + saveButton.getText());
            }
        });

        HBox saveButtonBox = new HBox(saveButton);
        saveButtonBox.setAlignment(Pos.CENTER);

        form.getChildren().add(saveButtonBox);
        return form;
    }

    public static VBox createFormField(String labelText) {
        Label label = new Label(labelText);
        label.setFont(LABEL_FONT);

        TextField inputField = labelText.equals("Password") ? new PasswordField() : new TextField();
        inputField.setStyle(INPUT_STYLE);
        inputField.setPrefHeight(40);

        return new VBox(5, label, inputField);
    }
    private static void showValidationError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
