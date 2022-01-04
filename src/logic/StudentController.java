package logic;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.student.Student;

public class StudentController {

    private DatabaseConnection DBconnection = new DatabaseConnection();

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = DBconnection.getConnection();
        String query = "SELECT * FROM student";
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Student student = new Student(resultSet.getString("email"), resultSet.getString("name"),
                        resultSet.getDate("dateOfBirth"), resultSet.getInt("gender"), resultSet.getString("zipCode"),
                        resultSet.getInt("houseNumber"), resultSet.getString("street"), resultSet.getString("country"),
                        resultSet.getString("location"));
                students.add(student);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return students;

    }

    public Student getStudent(String email) {
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = DBconnection.getConnection();
        String query = "SELECT * FROM student WHERE email = ?";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, email);
            resultSet = preparedStmt.executeQuery();

            while (resultSet.next()) {
                Student student = new Student(resultSet.getString("email"), resultSet.getString("name"),
                        resultSet.getDate("dateOfBirth"), resultSet.getInt("gender"), resultSet.getString("zipCode"),
                        resultSet.getInt("houseNumber"), resultSet.getString("street"), resultSet.getString("country"),
                        resultSet.getString("location"));
                students.add(student);
            }
            connection.close();
            return students.get(0);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void addStudent(String email, String name, Date dateOfBirth, int gender, String zipCode,
            int houseNumber, String street, String country, String location) {
        Connection connection = DBconnection.getConnection();
        String query = "INSERT INTO student VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, name);
            preparedStmt.setDate(3, dateOfBirth);
            preparedStmt.setInt(4, gender);
            preparedStmt.setString(5, zipCode);
            preparedStmt.setInt(6, houseNumber);
            preparedStmt.setString(7, street);
            preparedStmt.setString(8, country);
            preparedStmt.setString(9, location);
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteStudent(String email) {
        Connection connection = DBconnection.getConnection();
        String query = "DELETE FROM student WHERE email = ?";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, email);
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateStudent(Student student, String oldEmail) {
        Connection connection = DBconnection.getConnection();
        String query = "UPDATE student SET email = ?, name = ?, dateOfBirth = ?, gender = ?, zipCode = ?, houseNumber = ?, street = ?, country = ?, location = ? WHERE email = ?";

        try {
            java.util.Date utilDate = student.getDateOfBirth();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, student.getEmail());
            preparedStmt.setString(2, student.getName());
            preparedStmt.setDate(3, sqlDate);
            preparedStmt.setInt(4, student.getGender());
            preparedStmt.setString(5, student.getZipCode());
            preparedStmt.setInt(6, student.getHouseNumber());
            preparedStmt.setString(7, student.getStreet());
            preparedStmt.setString(8, student.getCountry());
            preparedStmt.setString(9, student.getLocation());
            preparedStmt.setString(10, oldEmail);
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<String[]> getModules(String email, String CourseName){
        ArrayList<String[]> modules = new ArrayList<>();
        Connection connection = DBconnection.getConnection();
        String query = "SELECT C.title, SC.progress FROM contentItem C "+
        "INNER JOIN module M ON C.module_id = M.module_id "+
        "INNER JOIN student_contentItem SC ON C.contentItem_id = SC.contentItem_id "+
        "WHERE SC.email = ? AND M.courseName = ? "+
        "ORDER BY M.orderNumber";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, CourseName);
            resultSet = preparedStmt.executeQuery();

            while (resultSet.next()) {
                String[] row = { resultSet.getString(1), Integer.toString(resultSet.getInt(2)) };
                modules.add(row);
            }

            connection.close();
            return modules;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
