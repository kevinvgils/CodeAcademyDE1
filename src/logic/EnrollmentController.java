package logic;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.student.Enrollment;

public class EnrollmentController {
    private DatabaseConnection DBconnection = new DatabaseConnection();

    // Collects all enrollments from the database
    public ArrayList<Enrollment> getEnrollments(String email) {

        Connection connection = DBconnection.getConnection();
        ArrayList<Enrollment> enrollments = new ArrayList<>();

        String query = "SELECT * FROM enrollment WHERE email = ?";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, email);
            resultSet = preparedStmt.executeQuery();

            while (resultSet.next()) {
                Enrollment enrollment = new Enrollment(resultSet.getInt("enrollment_id"), resultSet.getString("email"),
                        resultSet.getInt("certificate_id"), resultSet.getString("courseName"),
                        resultSet.getDate("dateOfEnrollment"));
                enrollments.add(enrollment);
            }
            connection.close();
            return enrollments;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Get the top 3 courses from the database
    public ArrayList<String> getTop3Courses() {

        Connection connection = DBconnection.getConnection();
        ArrayList<String> enrollments = new ArrayList<>();

        String query = "SELECT courseName, COUNT(*) AS Amount FROM enrollment as e WHERE e.enrollment_id IS NOT NULL GROUP BY courseName ORDER BY Amount DESC";
        ResultSet resultSet;
        Statement statement;
        Integer i = 0;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next() && i < 3) {
                enrollments.add(resultSet.getString("courseName") + ": " + resultSet.getInt("Amount"));
                i++;
            }
            connection.close();
            return enrollments;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Selects a specific enrollment
    public Enrollment getEnrollment(int id) {
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        Connection connection = DBconnection.getConnection();
        String query = "SELECT * FROM enrollment WHERE enrollment_id = ?";
        ResultSet resultSet;
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            resultSet = preparedStmt.executeQuery();

            while (resultSet.next()) {
                Enrollment enrollment = new Enrollment(resultSet.getInt("enrollment_id"), resultSet.getString("email"),
                        resultSet.getInt("certificate_id"), resultSet.getString("courseName"),
                        resultSet.getDate("dateOfEnrollment"));
                enrollments.add(enrollment);
            }
            connection.close();
            return enrollments.get(0);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Deletes an enrollment from the database
    public void deleteEnrollment(Integer enrollmentId) {
        Connection connection = DBconnection.getConnection();
        String query = "DELETE FROM enrollment WHERE enrollment_id = ?";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, enrollmentId);
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Adds an enrollment to the database
    public void addEnrollment(String email, String courseName, Date dateOfEnrollment) {
        Connection connection = DBconnection.getConnection();
        String query = "INSERT INTO enrollment(email, courseName, dateOfEnrollment) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, enrollment.getEmail());
            preparedStmt.setString(2, enrollment.getCourseName());
            preparedStmt.setDate(3, enrollment.getDateOfEnrollment());
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Updates an enrollment
    public void updateEnrollment(Enrollment enrollment, int oldEnrollmentId) {
        Connection connection = DBconnection.getConnection();
        String query = "UPDATE enrollment SET courseName = ?, dateOfEnrollment = ? WHERE enrollment_id = ?";

        try {
            java.util.Date utilDate = enrollment.getDateOfEnrollment();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, enrollment.getCourseName());
            preparedStmt.setDate(2, sqlDate);
            preparedStmt.setInt(3, oldEnrollmentId);
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
