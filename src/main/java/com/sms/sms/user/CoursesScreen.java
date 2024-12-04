package com.sms.sms.user;

import com.sms.sms.admin.service.AdminService;
import com.sms.sms.bars.form.Form;
import com.sms.sms.user.entity.Course;
import com.sms.sms.db.service.StudentService;
import com.sms.sms.bars.leftBar.LeftSideBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;
import java.util.Objects;


import static com.sms.sms.security.service.LoginServiceImpl.loggedInUsers;

import static com.sms.sms.styles.Colors.*;

import static com.sms.sms.styles.Images.*;

public class CoursesScreen extends Form {
    public Scene scene(String userName) {
        VBox mainPanel = createMainPanel(userName);
        HBox layout = createLayout(mainPanel,userName);

        return new Scene(layout, 1000, 800);
    }

    private HBox createLayout(VBox mainPanel,String username) {
        HBox layout = new HBox();
        layout.getChildren().addAll(LeftSideBar.sideBar(0, false,username), mainPanel);
        HBox.setHgrow(mainPanel, Priority.ALWAYS);
        return layout;
    }

    private VBox createMainPanel(String username) {
        VBox mainPanel = new VBox(20);
        mainPanel.setPadding(new Insets(20));
        mainPanel.setStyle(CREATE_MAIN_PANEL);

        HBox topBar = createTopBar(username);
        ScrollPane scrollPane = createScrollPane();
        VBox scrollableContent = createScrollableContent(username);

        scrollPane.setContent(scrollableContent);

        mainPanel.getChildren().addAll(topBar, scrollPane);
        return mainPanel;
    }

    private ScrollPane createScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle(GET_SCROLL_BAR_STYLE);
        return scrollPane;
    }



    private VBox createScrollableContent(String username) {
        VBox scrollableContent = new VBox(20);
        scrollableContent.setPadding(new Insets(20));

        VBox coursesSection = createCoursesSection(username);
        VBox continueLearningSection = createContinueLearningContainer(username);

        scrollableContent.getChildren().addAll(coursesSection, continueLearningSection);
        return scrollableContent;
    }

    private HBox createTopBar(String username) {
        HBox topBar = new HBox(20);
        topBar.setPadding(new Insets(10, 20, 10, 20));
        topBar.setStyle(CREATE_TOP_BAR);

        HBox searchBar = createSearchBar();
        VBox profileSection = createProfileSection(username);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topBar.getChildren().addAll(searchBar, spacer, profileSection);
        return topBar;
    }

    private HBox createSearchBar() {
        TextField searchField = new TextField();
        searchField.setPromptText("Explore courses...");
        searchField.setStyle(CREATE_SEARCH_BAR);
        searchField.setPrefWidth(350);

        addSearchFieldStyles(searchField);

        ImageView searchIcon = new ImageView(SEARCH_ICON);
        searchIcon.setFitWidth(30);
        searchIcon.setFitHeight(30);

        HBox searchBar = new HBox(10);
        searchBar.setAlignment(Pos.CENTER_LEFT);
        searchBar.getChildren().addAll(searchField, searchIcon);
        return searchBar;
    }

    private void addSearchFieldStyles(TextField searchField) {
        searchField.setStyle(SEARCH_FIELD);
        searchField.setOnMouseEntered(e -> searchField.setStyle(SEARCH_FIELD ));
        searchField.setOnMouseExited(e -> searchField.setStyle(SEARCH_FIELD));
        searchField.setOnMousePressed(e -> searchField.setStyle(SEARCH_FIELD ));

    }


    private VBox createCoursesSection(String username) {
        VBox coursesSection = new VBox(20);
        coursesSection.setPadding(new Insets(20));
        coursesSection.setAlignment(Pos.TOP_LEFT);
        Label sectionTitle = new Label("Courses");
        sectionTitle.setFont(Font.font("Arial", 24));
        sectionTitle.setTextFill(Color.BLACK);

        FlowPane coursesContainer = createCoursesContainer(username);

        coursesSection.getChildren().addAll(sectionTitle, coursesContainer);
        return coursesSection;
    }

    private FlowPane createCoursesContainer(String username) {
        FlowPane coursesContainer = new FlowPane();
        coursesContainer.setPadding(new Insets(10));
        coursesContainer.setHgap(20);
        coursesContainer.setVgap(20);

        List<Course> courses = StudentService.findById(loggedInUsers.get(username)).getCourses();
        for (Course course : courses) {
            coursesContainer.getChildren().add(createCourseCard(course));
        }
        return coursesContainer;
    }

    private VBox createCourseCard(Course course) {
        VBox courseCard = new VBox(10);
        courseCard.setPadding(new Insets(10));
        courseCard.setStyle(CREATE_COURSE_CARD);
        courseCard.setAlignment(Pos.TOP_CENTER);
        courseCard.setPrefWidth(250);

        ImageView courseImage = new ImageView(new Image(course.getImageUrl()));
        courseImage.setFitWidth(230);
        courseImage.setFitHeight(130);

        Label title = new Label(course.getTitle());
        title.setFont(Font.font("Arial", 18));
        title.setTextFill(Color.BLACK);

        Label instructor = new Label(course.getInstructorName());
        instructor.setFont(Font.font("Arial", 12));
        instructor.setTextFill(Color.GRAY);

        Label description = new Label(course.getDescription());
        description.setFont(Font.font("Arial", 12));
        description.setWrapText(true);

        Label grade = new Label(course.getGrade());
        grade.setFont(Font.font("Arial", 14));
        grade.setTextFill(Color.GREEN);

        courseCard.getChildren().addAll(courseImage, title, instructor, description, grade);
        return courseCard;
    }

    private VBox createContinueLearningContainer(String username) {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        Label sectionTitle = new Label("Continue Learning");
        sectionTitle.setFont(Font.font("Arial", 24));
        sectionTitle.setTextFill(Color.BLACK);
        vBox.getChildren().addAll(sectionTitle);
        List<Course> courses = StudentService.findById(loggedInUsers.get(username)).getCourses();
        for (Course course : courses) {
            vBox.getChildren().add(createContinueLearningSection(course));
        }
        return vBox;
    }

    private VBox createContinueLearningSection(Course course) {
        VBox continueLearningSection = new VBox(20);
        continueLearningSection.setPadding(new Insets(20));


        HBox courseCard = new HBox(20);
        courseCard.setPadding(new Insets(10));
        courseCard.setStyle(CREATE_CONTINUE_LEARNING_SECTION);
        courseCard.setAlignment(Pos.CENTER_LEFT);

        ImageView courseImage = new ImageView(new Image(course.getImageUrl()));
        courseImage.setFitWidth(100);
        courseImage.setFitHeight(100);

        VBox courseDetails = new VBox(10);
        Label title = new Label(course.getTitle());
        title.setFont(Font.font("Arial", 18));
        title.setTextFill(Color.BLACK);

        Label instructor = new Label(course.getDescription());
        instructor.setFont(Font.font("Arial", 12));
        instructor.setTextFill(Color.GRAY);

        ProgressBar progressBar = new ProgressBar(course.getStatus());
        Label progressLabel = new Label("2 lectures of 2 weeks left");

        courseDetails.getChildren().addAll(title, instructor, progressBar, progressLabel);
        courseCard.getChildren().addAll(courseImage, courseDetails);

        continueLearningSection.getChildren().addAll(courseCard);
        return continueLearningSection;
    }



    public static VBox createProfileSection(String username) {
        VBox profileSection = new VBox(5);
        profileSection.setAlignment(Pos.CENTER_RIGHT);

        ImageView profileImage = new ImageView(AVATAR_ICON1);
        profileImage.setFitHeight(40);
        profileImage.setFitWidth(40);
        System.out.println(username);

        boolean isStudent = username.startsWith("U");
        Label authorityLabel = isStudent ? new Label("Student")
                : new Label("Admin");
        Label usernameLabel = isStudent ? new Label(Objects
                .requireNonNull(StudentService
                        .findStudentByUsername(username))
                .getFullName()):
                new Label(Objects
                        .requireNonNull(AdminService
                                .findAdminByUsername(username))
                        .getFullName());

        usernameLabel.setFont(new Font("Arial", 14));
        usernameLabel.setTextFill(Color.BLACK);
        authorityLabel.setFont(new Font("Arial", 12));
        authorityLabel.setTextFill(Color.GRAY);

        profileSection.getChildren().addAll(profileImage, usernameLabel, authorityLabel);
        return profileSection;
    }

}

