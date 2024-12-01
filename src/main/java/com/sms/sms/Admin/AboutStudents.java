package com.sms.sms.Admin;

import com.sms.sms.User.CellFactory;
import com.sms.sms.User.CoursesScreen;
import com.sms.sms.leftbar.LeftSideBar;
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

import static com.sms.sms.styles.Colors.*;
import static com.sms.sms.styles.Images.SEARCH_ICON;

public class AboutStudents implements CellFactory {
    private static final AddNewStudent addStudent = new AddNewStudent();
    public Scene scene(Stage stage) {
        HBox topBar = createTopBar();

        HBox titleBar = createTitleBar(stage);

        TableView<Students> studentTable = createStudentTable();

        VBox centerContent = createCenterContent(topBar, titleBar, studentTable);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(LeftSideBar.sideBar(0, true));
        mainLayout.setCenter(centerContent);

        return new Scene(mainLayout, 1000, 800);
    }

    private HBox createTopBar() {
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

        VBox profileSection = CoursesScreen.createProfileSection();

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


    private HBox createTitleBar(Stage stage) {
        HBox titleBar = new HBox(10);
        titleBar.setAlignment(Pos.CENTER_LEFT);
        titleBar.setPadding(new Insets(10));

        Label title = new Label("Students");
        title.setStyle(TITLE_BAR1);

        Button addStudentButton = new Button("Add New Student");
        addStudentButton.setStyle(TITLE_BAR2);
        addStudentButton.setPadding(new Insets(5, 10, 5, 10));
        addStudentButton.setOnAction(actionEvent ->  stage.setScene(addStudent.scene()));
        Region titleSpacer = new Region();
        HBox.setHgrow(titleSpacer, Priority.ALWAYS);

        titleBar.getChildren().addAll(title, titleSpacer, addStudentButton);
        return titleBar;
    }

    private TableView<Students> createStudentTable() {
        TableView<Students> studentTable = new TableView<>();
        studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        String[] columnNames = {"Student", "Email", "Phone", "Course"};
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn<Students, String> column = createTableColumn(i, columnNames[i]);
            studentTable.getColumns().add(column);
        }

        ObservableList<Students> data = FXCollections.observableArrayList(
                new Students("Alice Smith", "alice.smith@example.com", "123-456-7890", "Mathematics"),
                new Students("Bob Johnson", "bob.johnson@example.com", "987-654-3210", "Physics"),
                new Students("Charlie Brown", "charlie.brown@example.com", "456-123-7890", "Computer Science"),
                new Students("Diana Prince", "diana.prince@example.com", "321-654-9870", "Chemistry")
        );
        studentTable.setItems(data);
        return studentTable;
    }

    private TableColumn<Students, String> createTableColumn(int index, String columnName) {
        TableColumn<Students, String> column = new TableColumn<>(columnName);
        column.setReorderable(false);
        column.setStyle(TABLE_COLUMN);

        column.setCellValueFactory(cellData -> {
            Students entry = cellData.getValue();
            return switch (index) {
                case 0 -> new SimpleStringProperty(entry.name());
                case 1 -> new SimpleStringProperty(entry.email());
                case 2 -> new SimpleStringProperty(entry.phone());
                case 3 -> new SimpleStringProperty(entry.course());
                default -> null;
            };
        });
        CellFactory.cellFactory(column);
        return column;
    }

    private VBox createCenterContent(HBox topBar, HBox titleBar, TableView<Students> studentTable) {
        VBox centerContent = new VBox(10, topBar, titleBar, studentTable);
        centerContent.setPadding(new Insets(10));
        return centerContent;
    }

}

record Students(String name, String email, String phone, String course) {}
