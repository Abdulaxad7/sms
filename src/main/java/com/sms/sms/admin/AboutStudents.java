package com.sms.sms.admin;

import com.sms.sms.admin.service.AdminService;
import com.sms.sms.bars.form.Form;
import com.sms.sms.user.CellFactory;
import com.sms.sms.user.CoursesScreen;
import com.sms.sms.user.entity.Student;
import com.sms.sms.db.service.StudentService;
import com.sms.sms.bars.leftBar.LeftSideBar;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static com.sms.sms.security.service.LoginServiceImpl.loggedInUsers;
import static com.sms.sms.styles.Colors.*;
import static com.sms.sms.styles.Images.SEARCH_ICON;

public class AboutStudents implements CellFactory {
    private static final AddNewStudent addStudent = new AddNewStudent();
    public Scene scene(Stage stage,String username) {
        HBox topBar = createTopBar(username);

        HBox titleBar = createTitleBar(stage,username);

        TableView<Student> studentTable = createStudentTable();

        VBox centerContent = createCenterContent(topBar, titleBar, studentTable);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(LeftSideBar.sideBar(0, true,username));
        mainLayout.setCenter(centerContent);

        return new Scene(mainLayout, 1000, 800);
    }

    private HBox createTopBar(String username) {
        HBox topBar = new HBox(20);
        topBar.setPadding(new Insets(10, 20, 10, 20));
        topBar.setStyle(TOP_BAR);

        TextField searchField = createSearchField();

        ImageView searchIcon = new ImageView(SEARCH_ICON);
        searchIcon.setFitWidth(30);
        searchIcon.setFitHeight(30);

        HBox searchBar = new HBox(10);
        searchBar.setAlignment(Pos.CENTER_LEFT);
        searchBar.getChildren().addAll(searchField, searchIcon);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        VBox profileSection = CoursesScreen.createProfileSection(username);

        topBar.getChildren().addAll(searchBar, spacer, profileSection);
        return topBar;
    }

    private TextField createSearchField() {
        TextField searchField = new TextField();
        searchField.setPromptText("Search student...");
        searchField.setStyle(SEARCH_FIELD_COLOR);
        searchField.setPrefWidth(350);

        searchField.setOnMouseEntered(e -> searchField.setStyle(searchField.getStyle() + SEARCH_BORDER1));
        searchField.setOnMouseExited(e -> searchField.setStyle(searchField.getStyle()));
        searchField.setOnMousePressed(e -> searchField.setStyle(searchField.getStyle() + SEARCH_BORDER2));
        return searchField;
    }


    private HBox createTitleBar(Stage primartStage, String username) {
        HBox titleBar = new HBox(10);
        titleBar.setAlignment(Pos.CENTER_LEFT);
        titleBar.setPadding(new Insets(10));

        Label title = new Label("Students");
        title.setStyle(TITLE_BAR1);

        Button addStudentButton = new Button("Add New Student");
        addStudentButton.setStyle(TITLE_BAR2);
        addStudentButton.setPadding(new Insets(5, 10, 5, 10));
        addStudentButton.setOnAction(actionEvent ->  primartStage.setScene(addStudent.scene(primartStage,username)));
        Region titleSpacer = new Region();
        HBox.setHgrow(titleSpacer, Priority.ALWAYS);

        titleBar.getChildren().addAll(title, titleSpacer, addStudentButton);
        return titleBar;
    }

    private TableView<Student> createStudentTable() {
        TableView<Student> studentTable = new TableView<>();
        studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        String[] columnNames = {"Student", "Username", "Email", "Phone","Address"};
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn<Student, String> column = createTableColumn(i, columnNames[i]);
            studentTable.getColumns().add(column);
        }

        ObservableList<Student> data = FXCollections
                .observableArrayList(StudentService.findAllStudents());
        studentTable.setItems(data);
        return studentTable;
    }

    private TableColumn<Student, String> createTableColumn(int index, String columnName) {
        TableColumn<Student, String> column = new TableColumn<>(columnName);
        column.setReorderable(false);
        column.setStyle(TABLE_COLUMN);

        column.setCellValueFactory(cellData -> {
            Student entry = cellData.getValue();
            return switch (index) {
                case 0 -> new SimpleStringProperty(entry.getFullName());
                case 1 -> new SimpleStringProperty(entry.getUsername());
                case 2 -> new SimpleStringProperty(entry.getEmail());
                case 3 -> new SimpleStringProperty(entry.getPhone());
                case 4 -> new SimpleStringProperty(entry.getAddress());
                default -> null;
            };
        });
        CellFactory.cellFactory(column);
        return column;
    }

    private VBox createCenterContent(HBox topBar, HBox titleBar, TableView<Student> studentTable) {
        VBox centerContent = new VBox(10, topBar, titleBar, studentTable);
        centerContent.setPadding(new Insets(10));
        return centerContent;
    }

}
