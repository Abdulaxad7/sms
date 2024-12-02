package com.sms.sms.db;

import com.sms.sms.User.entity.Course;
import com.sms.sms.User.entity.Grade;
import com.sms.sms.User.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Grade.class)
                    .addAnnotatedClass(Course.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void close() {
        sessionFactory.close();
    }
}
