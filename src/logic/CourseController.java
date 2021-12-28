package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import domain.course.Course;

public class CourseController {

    private DatabaseConnection DBconnection = new DatabaseConnection();

    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        Connection connection = DBconnection.getConnection();
        String query = "SELECT * FROM course";
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Course course = new Course(resultSet.getString("courseName"), resultSet.getString("level"), resultSet.getString("subject"), resultSet.getString("introductionText"));
                courses.add(course);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return courses;
    }

    public Course getCourse(String courseName) {
        ArrayList<Course> courses = new ArrayList<>();
        Connection connection = DBconnection.getConnection();
        String query = "SELECT * FROM course WHERE courseName = ?";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, courseName);
            resultSet = preparedStmt.executeQuery();

            while (resultSet.next()) {
                Course course = new Course(resultSet.getString("courseName"), resultSet.getString("level"), resultSet.getString("subject"), resultSet.getString("introductionText"));
                courses.add(course);
            }
            connection.close();
            return courses.get(0);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void addCourse(String courseName, String level, String subject, String introductionText) {
        Connection connection = DBconnection.getConnection();
        String query = "INSERT INTO course VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, courseName);
            preparedStmt.setString(2, level);
            preparedStmt.setString(3, subject);
            preparedStmt.setString(4, introductionText);
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteCourse(String courseName) {
        Connection connection = DBconnection.getConnection();
        String query = "DELETE FROM course WHERE courseName = ?";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, courseName);
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateCourse(Course course, String oldCourseName) {
        Connection connection = DBconnection.getConnection();
        String query = "UPDATE course SET courseName = ?, level = ?, subject = ?, introductionText = ? WHERE courseName = ?";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, course.getCourseName());
            preparedStmt.setString(2, course.getLevel());
            preparedStmt.setString(3, course.getSubject());
            preparedStmt.setString(4, course.getIntroductionText());
            preparedStmt.setString(5, oldCourseName);
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
