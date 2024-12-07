package com.sms.sms.user.repository;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public interface SettingsScreen {
    Scene scene(Stage primartStage, String username);
    HBox createHeader(String username);

}
