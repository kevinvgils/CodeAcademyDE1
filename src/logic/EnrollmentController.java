package logic;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.student.Enrollment;

public class EnrollmentController { 
    private DatabaseConnection DBconnection = new DatabaseConnection();

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
                        resultSet.getInt("certificate_id"), resultSet.getString("courseName"), resultSet.getDate("dateOfEnrollment"));
                enrollments.add(enrollment);
            }
            connection.close();
            return enrollments;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    } 

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
                        resultSet.getInt("certificate_id"), resultSet.getString("courseName"), resultSet.getDate("dateOfEnrollment"));
                enrollments.add(enrollment);
            }
            connection.close();
            return enrollments.get(0);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void deleteEnrollment(Integer enrollmentId){
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

    public void addEnrollment(String email, String courseName, Date dateOfEnrollment) {
        Connection connection = DBconnection.getConnection();
        String query = "INSERT INTO enrollment(email, courseName, dateOfEnrollment) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, courseName);
            preparedStmt.setDate(3, dateOfEnrollment);
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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


    
