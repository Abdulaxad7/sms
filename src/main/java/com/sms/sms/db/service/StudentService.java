package com.sms.sms.db.service;


import com.sms.sms.User.entity.Student;
import com.sms.sms.db.repository.JpaRepository;
import com.sms.sms.db.repository.JpaRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class StudentService {
    private static final JpaRepository<Student, UUID> repository = new JpaRepositoryImpl<>(Student.class);

    public static void persistNewStudent(Student student) {
        log.info("Student inserted: {}", repository.save(student));
    }

    public static List<Student> findAllStudents() {
        List<Student> students = repository.findAll();
        log.info("Founded students: {}", students);
        return students;
    }

    public static void removeStudentById(UUID student) {
        repository.deleteById(student);
        log.info("Student removed: {}", student);
    }

    public static Student updateStudent(Student student) {
        Student updated = repository.update(student.getId(), student);
        log.info("Student updated: {}", student);
        return updated;
    }

    public static Student findStudentByUsername(String username) {
        List<Student> students = repository.findByField("username", username);
        if (students.isEmpty()) {
            log.info("No student found with username: {}", username);
            return null;
        }
        log.info("Found student: {}", students.getFirst());
        return students.getFirst();
    }
}
