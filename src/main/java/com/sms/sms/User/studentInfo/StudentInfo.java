package com.sms.sms.User.studentInfo;

import com.sms.sms.User.CellFactory;
import com.sms.sms.User.entity.Grade;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public interface StudentInfo extends CellFactory {
    VBox createTopBar();

    TableView<Grade> createGradeTable();

    VBox createTitleBox();

    SimpleStringProperty createCellValue(Grade entry, int colIndex);

    VBox createCenterContent();
}
