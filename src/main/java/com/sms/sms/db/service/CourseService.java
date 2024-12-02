package com.sms.sms.db.service;

import com.sms.sms.User.entity.Course;

import com.sms.sms.db.repository.JpaRepository;
import com.sms.sms.db.repository.JpaRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class CourseService {

    private static final JpaRepository<Course, UUID> repository = new JpaRepositoryImpl<>(Course.class);

    public static void persistNewCourse(Course course) {
        log.info("Course inserted: {}", repository.save(course));
    }

    public static List<Course> findAllCourses() {
        List<Course> courses = repository.findAll();
        log.info("Founded courses: {}", courses);
        return courses;
    }

    public static void removeCourseById(UUID id) {
        repository.deleteById(id);
        log.info("Course removed with id: {}", id);
    }

    public static Course updateCourse(Course course) {
        Course updated = repository.update(course.getId(), course);
        log.info("Course updated: {}", updated);
        return updated;
    }

    public static Course findCourseByUsername(String course) {
        List<Course> courses = repository.findByField("username", course);
        if (courses.isEmpty()) {
            log.info("No courses found with course: {}", course);
            return null;
        }
        log.info("Found courses: {}", courses.getFirst());
        return courses.getFirst();
    }

    public static List<Course> getRandomCourses() {
        return null;
    }
}
