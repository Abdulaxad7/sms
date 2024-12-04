package com.sms.sms.user.mapper;

import com.sms.sms.user.entity.Course;
import com.sms.sms.user.entity.Grade;
import com.sms.sms.user.entity.Student;
import com.sms.sms.db.service.CourseService;
import com.sms.sms.db.service.GradeService;
import com.sms.sms.security.pass.PasswordEncoder;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StudentMapper {
    public Student toStudent(Map<String, TextField> textFieldMap, String fullName, String password, UUID uuid) {
        List<Grade> grades = GradeService.getRandomGrades();
        for (TextField textField : textFieldMap.values())
            System.out.println(textField.getText());
        return Student
                .builder()
                .id(uuid == null ? UUID.randomUUID() : uuid)
                .fullName(fullName == null ? textFieldMap.get("FullName").getText() : fullName)
                .username(textFieldMap.get("Username").getText())
                .email(textFieldMap.get("Email").getText())
                .phone(textFieldMap.get("Phone").getText())
                .address(textFieldMap.get("Address").getText())
                .password(textFieldMap.get("Password").getText() != null ? String.valueOf(PasswordEncoder.encodePassword(textFieldMap.get("Password").getText())):password)
                .grades(grades)
                .courses(getRandomCourses(grades))
                .build();
    }

    private List<Course> getRandomCourses(List<Grade> grades) {
        List<Course> courses = new ArrayList<>();
        for (Grade grade: grades)
           courses.add(CourseService.findCourseByCourseName(grade.getTitle()));
        return courses;
    }
}
