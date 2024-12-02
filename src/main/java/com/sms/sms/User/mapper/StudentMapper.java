package com.sms.sms.User.mapper;

import com.sms.sms.User.entity.Student;
import com.sms.sms.db.service.CourseService;
import com.sms.sms.db.service.GradeService;
import com.sms.sms.security.pass.PasswordEncoder;
import javafx.scene.control.TextField;

import java.util.Map;

public class StudentMapper {
    public Student toStudent(Map<String, TextField> stringMap) {
        return Student
                .builder()
                .fullName(stringMap.get("FullName").getText())
                .username(stringMap.get("Username").getText())
                .email(stringMap.get("Email").getText())
                .phone(stringMap.get("Phone").getText())
                .address(stringMap.get("Address").getText())
                .password(String.valueOf(PasswordEncoder.encodePassword(stringMap.get("Password").getText())))
                .grades(GradeService.getRandomGrades())
                .courses(CourseService.getRandomCourses())
                .build();
    }
}
