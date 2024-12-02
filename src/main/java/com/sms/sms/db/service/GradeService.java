package com.sms.sms.db.service;


import com.sms.sms.User.entity.Grade;
import com.sms.sms.db.repository.JpaRepository;
import com.sms.sms.db.repository.JpaRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class GradeService {
    private static final JpaRepository<Grade, UUID> repository = new JpaRepositoryImpl<>(Grade.class);

    public static void persistNewGrade(Grade grade) {
        log.info("Grade inserted: {}", repository.save(grade));
    }

    public static List<Grade> findAllGrade() {
        List<Grade> grade = repository.findAll();
        log.info("Founded grade: {}", grade);
        return grade;
    }

    public static void removeGradeById(UUID student) {
        repository.deleteById(student);
        log.info("Grade removed: {}", student);
    }

    public static Grade updateGrade(Grade grade) {
        Grade updated = repository.update(grade.getId(), grade);
        log.info("Grade updated: {}", grade);
        return updated;
    }

    public static Grade findGradeByUsername(String grade) {
        List<Grade> grades = repository.findByField("username", grade);
        if (grades.isEmpty()) {
            log.info("No grades found with grade: {}", grade);
            return null;
        }
        log.info("Found student: {}", grades.getFirst());
        return grades.getFirst();
    }

    public static List<Grade> getRandomGrades() {
        return null;
    }
}
