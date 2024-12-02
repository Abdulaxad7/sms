package com.sms.sms.security;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public interface LoginScreen {
    VBox rightPane(Stage primaryStage);

    VBox formBox(VBox rightPane, Stage primaryStage);

    void configurePrimaryStage(Stage primaryStage, Scene scene);
}
