package com.sms.sms.admin.repository;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public interface AddNewStudent {
    Scene scene(Stage primaryStage, String username);
    HBox createHeader(String username);
}
