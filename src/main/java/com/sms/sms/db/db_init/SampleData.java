package com.sms.sms.db.db_init;

import com.sms.sms.user.entity.Course;
import com.sms.sms.user.entity.Grade;

import java.util.List;
import java.util.UUID;

import static com.sms.sms.styles.Images.COURSE_IMAGE1;

public class SampleData {
    public static List<Grade> generateSampleData() {

        return List.of(
                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Calculus 101")
                        .attendance("95%")
                        .assignment("90%")
                        .quiz("85%")
                        .midTerm("92%")
                        .finalTerm("98%")
                        .total("92%")
                        .grade("A")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Physics Fundamentals")
                        .attendance("85%")
                        .assignment("80%")
                        .quiz("75%")
                        .midTerm("88%")
                        .finalTerm("86%")
                        .total("83%")
                        .grade("B")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Introduction to Computer Science")
                        .attendance("98%")
                        .assignment("93%")
                        .quiz("90%")
                        .midTerm("94%")
                        .finalTerm("95%")
                        .total("94%")
                        .grade("A")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Organic Chemistry")
                        .attendance("88%")
                        .assignment("80%")
                        .quiz("78%")
                        .midTerm("85%")
                        .finalTerm("90%")
                        .total("85%")
                        .grade("B")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Advanced Linear Algebra")
                        .attendance("92%")
                        .assignment("94%")
                        .quiz("90%")
                        .midTerm("96%")
                        .finalTerm("97%")
                        .total("94%")
                        .grade("A")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Modern Art History")
                        .attendance("78%")
                        .assignment("65%")
                        .quiz("60%")
                        .midTerm("75%")
                        .finalTerm("80%")
                        .total("72%")
                        .grade("C")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Machine Learning")
                        .attendance("98%")
                        .assignment("95%")
                        .quiz("94%")
                        .midTerm("96%")
                        .finalTerm("99%")
                        .total("98%")
                        .grade("A")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Macroeconomics")
                        .attendance("85%")
                        .assignment("80%")
                        .quiz("82%")
                        .midTerm("87%")
                        .finalTerm("84%")
                        .total("84%")
                        .grade("B")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Psychology 101")
                        .attendance("75%")
                        .assignment("70%")
                        .quiz("65%")
                        .midTerm("72%")
                        .finalTerm("77%")
                        .total("72%")
                        .grade("C")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Environmental Science")
                        .attendance("94%")
                        .assignment("91%")
                        .quiz("90%")
                        .midTerm("96%")
                        .finalTerm("98%")
                        .total("94%")
                        .grade("A")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Data Structures and Algorithms")
                        .attendance("95%")
                        .assignment("92%")
                        .quiz("94%")
                        .midTerm("96%")
                        .finalTerm("97%")
                        .total("95%")
                        .grade("A")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Statistics for Business")
                        .attendance("82%")
                        .assignment("80%")
                        .quiz("75%")
                        .midTerm("85%")
                        .finalTerm("86%")
                        .total("81%")
                        .grade("B")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Philosophy of Ethics")
                        .attendance("83%")
                        .assignment("80%")
                        .quiz("78%")
                        .midTerm("84%")
                        .finalTerm("85%")
                        .total("82%")
                        .grade("B")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Quantum Mechanics")
                        .attendance("95%")
                        .assignment("92%")
                        .quiz("91%")
                        .midTerm("93%")
                        .finalTerm("98%")
                        .total("94%")
                        .grade("A")
                        .build(),

                Grade.builder()
                        .id(UUID.randomUUID())
                        .title("Business Management")
                        .attendance("80%")
                        .assignment("78%")
                        .quiz("70%")
                        .midTerm("75%")
                        .finalTerm("80%")
                        .total("76%")
                        .grade("C")
                        .build()
        );
    }

    public static List<Course> generateSampleCourses() {
        return List.of(
                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Calculus 101")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. John Smith")
                        .description("An introduction to differential and integral calculus.")
                        .grade("A")
                        .status(30.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Physics Fundamentals")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Jane Doe")
                        .description("Fundamentals of classical mechanics and thermodynamics.")
                        .grade("B")
                        .status(60.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Introduction to Computer Science")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Alan Turing")
                        .description("Basics of computer science, algorithms, and programming.")
                        .grade("A")
                        .status(50.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Organic Chemistry")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Marie Curie")
                        .description("Study of the structure, properties, and reactions of organic compounds.")
                        .grade("B")
                        .status(80.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Advanced Linear Algebra")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Carl Gauss")
                        .description("Exploring vector spaces, eigenvalues, and linear transformations.")
                        .grade("A")
                        .status(40.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Modern Art History")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Pablo Picasso")
                        .description("A study of modern art movements and key artists.")
                        .grade("C")
                        .status(70.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Machine Learning")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Ada Lovelace")
                        .description("An introduction to machine learning algorithms and models.")
                        .grade("A")
                        .status(90.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Macroeconomics")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. John Maynard Keynes")
                        .description("Study of the economy as a whole, including inflation, GDP, and unemployment.")
                        .grade("B")
                        .status(55.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Psychology 101")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Sigmund Freud")
                        .description("Introduction to psychology and the study of the human mind and behavior.")
                        .grade("C")
                        .status(65.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Environmental Science")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Rachel Carson")
                        .description("Study of the environment, ecosystems, and human impact.")
                        .grade("A")
                        .status(85.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Data Structures and Algorithms")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Donald Knuth")
                        .description("Learn about data structures, algorithms, and their implementation.")
                        .grade("A")
                        .status(45.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Statistics for Business")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Florence Nightingale")
                        .description("Statistical methods for business applications and decision-making.")
                        .grade("B")
                        .status(75.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Philosophy of Ethics")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Immanuel Kant")
                        .description("Exploring moral theories and the philosophy of ethics.")
                        .grade("B")
                        .status(60.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Quantum Mechanics")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Niels Bohr")
                        .description("Study of quantum theory and the principles of quantum mechanics.")
                        .grade("A")
                        .status(95.0)
                        .build(),

                Course.builder()
                        .id(UUID.randomUUID())
                        .title("Business Management")
                        .imageUrl(COURSE_IMAGE1)
                        .instructorName("Dr. Peter Drucker")
                        .description("Principles and practices of effective business management.")
                        .grade("C")
                        .status(50.0)
                        .build()

        );
    }
}
