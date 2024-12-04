package com.sms.sms.db.service;


import com.sms.sms.db.HibernateUtil;
import com.sms.sms.user.entity.Student;
import com.sms.sms.db.repository.JpaRepository;
import com.sms.sms.db.repository.JpaRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
        if (student == null || student.getId() == null) {
            throw new IllegalArgumentException("Student or student ID cannot be null");
        }

        Student existing = repository.findById(student.getId());
        if (existing == null) {
            throw new RuntimeException("Student with ID " + student.getId() + " not found");
        }

        Student updated = repository.update(student.getId(), student);
        log.info("Student updated: {}", updated);
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
    public static Object findFieldByUsername(String fieldName, String username) {
        // Validate inputs
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("Field name cannot be null or empty");
        }

        try (Session session = HibernateUtil.getSession()) {
            // Dynamically construct the HQL query
            String hql = String.format("select s.%s from Student s where s.username = :username", fieldName);

            Query<Object> query = session.createQuery(hql);
            query.setParameter("username", username); // Set the username parameter

            // Return the single field value or null if no match is found
            return query.uniqueResult();
        } catch (Exception e) {
            log.error("Error fetching field '{}' for username '{}': {}", fieldName, username, e.getMessage());
            throw new RuntimeException("Failed to fetch field value", e);
        }
    }



    public static Student findById(UUID userId) {
        return repository.findById(userId);
    }
}
