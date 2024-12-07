package com.sms.sms.user.repository;

import com.sms.sms.user.entity.Course;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public interface CoursesScreen extends Scenes{
    HBox createLayout(VBox mainPanel, String username);
    VBox createMainPanel(String username);
    ScrollPane createScrollPane();
    VBox createScrollableContent(String username);
    HBox createTopBar(String username);
    HBox createSearchBar();
    void addSearchFieldStyles(TextField searchField);
    VBox createCoursesSection(String username);
    FlowPane createCoursesContainer(String username);
    VBox createCourseCard(Course course);
    VBox createContinueLearningContainer(String username);
    VBox createContinueLearningSection(Course course);
}
