package com.sms.sms.user.repository;

import com.sms.sms.user.CellFactory;
import com.sms.sms.user.entity.Grade;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public interface StudentInfo extends CellFactory {
    VBox createTopBar(String username);

    TableView<Grade> createGradeTable(String username);

    VBox createTitleBox(String username);

    SimpleStringProperty createCellValue(Grade entry, int colIndex);

    VBox createCenterContent(String username);
}
