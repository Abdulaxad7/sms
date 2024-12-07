package com.sms.sms.admin.repository;

import com.sms.sms.user.entity.Student;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public interface AboutStudents {
    Scene scene(Stage stage, String username);
    HBox createTopBar(String username);
    TextField createSearchField();
    HBox createTitleBar(Stage primartStage, String username);
    TableView<Student> createStudentTable();
    TableColumn<Student, String> createTableColumn(int index, String columnName);
    VBox createCenterContent(HBox topBar, HBox titleBar, TableView<Student> studentTable);
}
