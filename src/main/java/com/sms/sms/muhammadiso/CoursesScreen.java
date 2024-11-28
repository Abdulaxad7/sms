package com.sms.sms.muhammadiso;

import com.sms.sms.achanges.ChatScreen;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CoursesScreen extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox mainPanel = createMainPanel();
        HBox layout = createLayout(mainPanel);

        Scene scene = new Scene(layout, 1000, 800);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SMS - Sidebar and Main Screen");
        primaryStage.show();
    }

    private HBox createLayout(VBox mainPanel) {
        HBox layout = new HBox();
        layout.getChildren().addAll(ChatScreen.sideBar(0, false), mainPanel);
        HBox.setHgrow(mainPanel, Priority.ALWAYS);
        return layout;
    }

    private VBox createMainPanel() {
        VBox mainPanel = new VBox(20);
        mainPanel.setPadding(new Insets(20));
        mainPanel.setStyle("-fx-background-color: #f4f4f4;");

        HBox topBar = createTopBar();
        ScrollPane scrollPane = createScrollPane();
        VBox scrollableContent = createScrollableContent();

        scrollPane.setContent(scrollableContent);

        mainPanel.getChildren().addAll(topBar, scrollPane);
        return mainPanel;
    }

    private ScrollPane createScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle(getScrollBarStyle());
        return scrollPane;
    }

    private String getScrollBarStyle() {
        return ".root {" +
                "    -fx-background-color: transparent;" +
                "}" +
                ".scroll-pane {" +
                "    -fx-background-color: transparent;" +
                "    -fx-background-radius: 10px;" +
                "    -fx-background-insets: 0;" +
                "    -fx-padding: 0;" +
                "}" +
                ".scroll-pane .viewport {" +
                "    -fx-background-color: transparent;" +
                "}" +
                ".scroll-bar:vertical {" +
                "    -fx-background-color: rgba(230, 230, 230, 0.3);" +
                "    -fx-background-radius: 10px;" +
                "    -fx-background-insets: 3;" +
                "}" +
                ".scroll-bar:vertical .track {" +
                "    -fx-background-color: rgba(240, 240, 240, 0.5);" +
                "    -fx-background-radius: 10px;" +
                "}" +
                ".scroll-bar:vertical .thumb {" +
                "    -fx-background-color: rgba(100, 100, 100, 0.6);" +
                "    -fx-background-radius: 10px;" +
                "}" +
                ".scroll-bar:vertical .thumb:hover {" +
                "    -fx-background-color: rgba(80, 80, 80, 0.8);" +
                "}" +
                ".scroll-bar:vertical .increment-button, " +
                ".scroll-bar:vertical .decrement-button {" +
                "    -fx-background-color: transparent;" +
                "}" +
                ".scroll-bar {" +
                "    -fx-padding: 0;" +
                "    -fx-background-insets: 0;" +
                "}";
    }

    private VBox createScrollableContent() {
        VBox scrollableContent = new VBox(20);
        scrollableContent.setPadding(new Insets(20));

        VBox coursesSection = createCoursesSection();
        VBox continueLearningSection = createContinueLearningSection();

        scrollableContent.getChildren().addAll(coursesSection, continueLearningSection);
        return scrollableContent;
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(20);
        topBar.setPadding(new Insets(10, 20, 10, 20));
        topBar.setStyle("-fx-background-color: #f4f4f4;");

        HBox searchBar = createSearchBar();
        VBox profileSection = createProfileSection();

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topBar.getChildren().addAll(searchBar, spacer, profileSection);
        return topBar;
    }

    private HBox createSearchBar() {
        TextField searchField = new TextField();
        searchField.setPromptText("Explore courses...");
        searchField.setStyle("-fx-background-color: #c2c2f4;");
        searchField.setPrefWidth(350);

        addSearchFieldStyles(searchField);

        ImageView searchIcon = new ImageView(new Image("https:s-m-s.s3.eu-north-1.amazonaws.com/searchIcon.png"));
        searchIcon.setFitWidth(30);
        searchIcon.setFitHeight(30);

        HBox searchBar = new HBox(10);
        searchBar.setAlignment(Pos.CENTER_LEFT);
        searchBar.getChildren().addAll(searchField, searchIcon);
        return searchBar;
    }

    private void addSearchFieldStyles(TextField searchField) {
        String searchFieldStyle =
                "-fx-background-color: #f4f4f4;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-radius: 20px;" +
                        "-fx-border-color: #e0e0e0;" +
                        "-fx-border-width: 1px;" +
                        "-fx-padding: 10px 15px;" +
                        "-fx-font-size: 14px;";
        searchField.setStyle(searchFieldStyle);
        searchField.setOnMouseEntered(e -> searchField.setStyle(searchFieldStyle + "-fx-border-color: #a0a0a0;"));
        searchField.setOnMouseExited(e -> searchField.setStyle(searchFieldStyle));
        searchField.setOnMousePressed(e -> searchField.setStyle(searchFieldStyle + "-fx-border-color: #4a90e2;"));
    }

    private VBox createProfileSection() {
        VBox profileSection = new VBox(5);
        profileSection.setAlignment(Pos.CENTER_RIGHT);

        ImageView profileImage = new ImageView(new Image("https:s-m-s.s3.eu-north-1.amazonaws.com/i1.png"));
        profileImage.setFitHeight(40);
        profileImage.setFitWidth(40);

        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(new Font("Arial", 14));
        usernameLabel.setTextFill(Color.BLACK);

        Label authorityLabel = new Label("Authority");
        authorityLabel.setFont(new Font("Arial", 12));
        authorityLabel.setTextFill(Color.GRAY);

        profileSection.getChildren().addAll(profileImage, usernameLabel, authorityLabel);
        return profileSection;
    }

    private VBox createCoursesSection() {
        VBox coursesSection = new VBox(20);
        coursesSection.setPadding(new Insets(20));
        coursesSection.setAlignment(Pos.TOP_LEFT);

        Label sectionTitle = new Label("Courses");
        sectionTitle.setFont(Font.font("Arial", 24));
        sectionTitle.setTextFill(Color.BLACK);

        FlowPane coursesContainer = createCoursesContainer();

        coursesSection.getChildren().addAll(sectionTitle, coursesContainer);
        return coursesSection;
    }

    private FlowPane createCoursesContainer() {
        FlowPane coursesContainer = new FlowPane();
        coursesContainer.setPadding(new Insets(10));
        coursesContainer.setHgap(20);
        coursesContainer.setVgap(20);

        List<Course> courses = getCourses();
        for (Course course : courses) {
            coursesContainer.getChildren().add(createCourseCard(course));
        }
        return coursesContainer;
    }

    private VBox createCourseCard(Course course) {
        VBox courseCard = new VBox(10);
        courseCard.setPadding(new Insets(10));
        courseCard.setStyle("-fx-border-color: #ccc; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-color: #f9f9f9;");
        courseCard.setAlignment(Pos.TOP_CENTER);
        courseCard.setPrefWidth(250);

        ImageView courseImage = new ImageView(new Image(course.imageUrl()));
        courseImage.setFitWidth(230);
        courseImage.setFitHeight(130);

        Label title = new Label(course.title());
        title.setFont(Font.font("Arial", 18));
        title.setTextFill(Color.BLACK);

        Label instructor = new Label(course.instructorName());
        instructor.setFont(Font.font("Arial", 12));
        instructor.setTextFill(Color.GRAY);

        Label description = new Label(course.description());
        description.setFont(Font.font("Arial", 12));
        description.setWrapText(true);

        Label grade = new Label(course.grade());
        grade.setFont(Font.font("Arial", 14));
        grade.setTextFill(Color.GREEN);

        courseCard.getChildren().addAll(courseImage, title, instructor, description, grade);
        return courseCard;
    }

    private VBox createContinueLearningSection() {
        VBox continueLearningSection = new VBox(20);
        continueLearningSection.setPadding(new Insets(20));

        Label sectionTitle = new Label("Continue Learning");
        sectionTitle.setFont(Font.font("Arial", 24));
        sectionTitle.setTextFill(Color.BLACK);


        continueLearningSection.getChildren().add(sectionTitle);
        return continueLearningSection;
    }

    private List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Course 1", "Instructor 1", "Description 1", "A", "https:s-m-s.s3.eu-north-1.amazonaws.com/course_image.png"));
        courses.add(new Course("Course 2", "Instructor 2", "Description 2", "B", "https:s-m-s.s3.eu-north-1.amazonaws.com/course_image.png"));
        return courses;
    }

    public static void main(String[] args) {
        launch(args);
    }
}


record Course(String title, String imageUrl, String instructorName, String description, String grade) {
}


