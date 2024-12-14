# Student Management System (SMS)

The **Student Management System (SMS)** is a comprehensive Java-based application designed to streamline the management of students, courses, grades, and administrative tasks within an educational setting. It offers an intuitive user interface built with **JavaFX**, robust backend integration with **Hibernate ORM**, and follows clean, modular programming practices.

## Features

1. **User Authentication**:
   - Secure login system for different user roles (e.g., Admin, Student, etc.).
   - Authentication with role-based access to different screens.

2. **Student Management**:
   - Add, update, and manage student profiles.
   - Access and visualize student information dynamically.
   - Maintain a seamless workflow for performing administrative tasks.

3. **Course and Grade Management**:
   - Manage courses assigned to students.
   - Record and update grades for different courses.
   - Provide visual feedback for an organized course-grading flow.

4. **Chat and Support**:
   - Simple chat functionality for user interaction.
   - Support screen to assist with queries and navigation.

5. **JavaFX-based GUI**:
   - Feature-rich and interactive forms for input and data display.
   - Navigation through a left-side menu bar.
   - Highly responsive UI design for administrative tasks.

## Project Structure

The project is organized as follows:

### Key Packages and Classes

- **`com.sms.sms.admin`**
  - `AddNewStudentImpl`: Handles adding new students via the UI with back-end integration.
  - `AboutStudents`: View and manage details about existing students.
  
- **`com.sms.sms.db`**
  - `StudentService`, `CourseService`, and `GradeService`: Handle all database operations for their respective modules.
  - `JpaRepositoryImpl`: Custom implementation of a generic repository using Hibernate ORM.

- **`com.sms.sms.security`**
  - `LoginScreenImpl`: Provides a simple login and authentication mechanism.
  - `LoginServiceImpl`: Handles logic related to authenticating users and managing sessions.

- **`com.sms.sms.user`**
  - `ChatScreenImpl`: Implements chat functionality for user interaction.
  - `CoursesScreenImpl`: Displays and manages course data dynamically for students/admin.

- **`com.sms.sms.bars`**
  - `LeftSideBar`: Creates a reusable left navigation bar for switching between screens/modules.
  - `Form`: A base class for creating dynamic forms.

## Prerequisites

Before setting up the project, ensure that you have:

- **Java 21** or compatible JDK installed.
- **Maven** for building the project.
- **Hibernate** configured for ORM integration.
- **JavaFX SDK** for creating the GUI.

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd sms
   ```

2. **Configure the Database**:
   - Update the `hibernate.cfg.xml` file (located in `src/main/resources`) with your database connection properties (e.g., URL, username, and password).

3. **Build the Project**:
   - Use Maven to clean and build:
     ```bash
     mvn clean install
     ```

4. **Run the Application**:
   - Run the `MainApp.java` file via your IDE or use the following command in the terminal:
     ```bash
     java -jar target/sms-1.0-SNAPSHOT.jar
     ```

## Dependencies

The project requires the following dependencies to function:

- **JavaFX**: GUI-based application framework.
- **Hibernate**: ORM framework for data management.
- **Lombok**: Reduces boilerplate code for cleaner Java.
- **JUnit** (Optional): Unit testing framework.

## Features Overview

### Screens

1. **Login Screen**:
   - Handles user login and role-based access.

2. **Admin Dashboard**:
   - Navigate between features like `Add New Student`, `About Students`, and support views.
   - Manage student-related tasks using interactive forms.

3. **Student View**:
   - Access grades, courses, and personal details.

4. **Chat Functionality**:
   - Basic chat interface for communication within the system.

5. **Course and Grade Management**:
   - Administer courses and assign grades dynamically.

## Screenshots (Optional)

You can include screenshots of:

- The login screen
- Navigation bar
- Adding a new student
- Viewing student profiles
- Grade/Report chart visualizations (if applicable)

## License

This project is licensed under the **MIT License**. See the `LICENSE` file for more details.

