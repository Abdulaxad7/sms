package com.sms.sms.user.repository;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public interface SupportScreen extends Scenes{
    HBox createHeader();
    HBox createContactBox(String iconUrl, String text);
    VBox createForm(HBox header);
    Label createTitle();
    VBox createInputFields();
    VBox createInputField(String labelText, boolean isTextArea);
    HBox createSubmitButton();
    void handleFormSubmission();
    void clearFormFields();
    void showConfirmationStage();
    BorderPane createMainLayout(VBox formBox, String username);
}
