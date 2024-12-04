package com.sms.sms.User.studentInfo;

import com.sms.sms.User.CellFactory;
import com.sms.sms.User.entity.Grade;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.util.UUID;

public interface StudentInfo extends CellFactory {
    VBox createTopBar(String username);

    TableView<Grade> createGradeTable(String username);

    VBox createTitleBox();

    SimpleStringProperty createCellValue(Grade entry, int colIndex);

    VBox createCenterContent(String username);
}
