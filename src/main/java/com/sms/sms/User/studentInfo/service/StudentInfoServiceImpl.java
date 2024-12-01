package com.sms.sms.User.studentInfo.service;

import com.sms.sms.User.entity.Grade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentInfoServiceImpl implements StudentInfoService {
    public ObservableList<Grade> getSampleData() {

        return FXCollections.observableArrayList(
//                new Grade("Mathematics", "95%", "90%", "92%", "88%", "91%", "91%", "A"),
//                new Grade("Computer Science", "92%", "95%", "90%", "93%", "94%", "93%", "A"),
//                new Grade("Physics", "88%", "85%", "87%", "90%", "89%", "89%", "B+"),
//                new Grade("Chemistry", "90%", "92%", "85%", "87%", "88%", "88%", "B")
        );
    }
}
