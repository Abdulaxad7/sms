package com.sms.sms.db;

import com.sms.sms.User.entity.Course;
import com.sms.sms.User.entity.Grade;
import com.sms.sms.User.entity.Student;
import com.sms.sms.exceptions.FailedToStartHibernate;
import org.hibernate.Session;

import java.util.List;

import static com.sms.sms.styles.Images.COURSE_IMAGE1;

public class Hibernate {
    public static void start(){
        try (Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            Student student = Student.builder().name("name").username("uname").password("pass")
                    .courses(List.of( Course.builder()
                            .title("Course 1")
                            .imageUrl(COURSE_IMAGE1)
                            .instructorName(" Instructor 1")
                            .description("Description 1")
                            .grade("A")
                            .status(0.75)
                            .build()))
                    .grades(List.of(
                            Grade.builder()
                                    .title("Mathematics")
                                    .assignment("95%")
                                    .attendance("90%")
                                    .quiz("92%")
                                    .midTerm("88%")
                                    .finalTerm("91%")
                                    .total("91%")
                                    .grade("A")
                                    .build()
                    )).build();

            session.persist(student);

            session.getTransaction().commit();
        } catch (Exception e) {
            throw new FailedToStartHibernate("Exception: "+e.getMessage());
        }
        HibernateUtil.close();

    }
}
