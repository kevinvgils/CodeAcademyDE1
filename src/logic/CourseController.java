package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

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
                Course course = new Course(resultSet.getString("courseName"), resultSet.getString("level"),
                        resultSet.getString("subject"), resultSet.getString("introductionText"));
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
                Course course = new Course(resultSet.getString("courseName"), resultSet.getString("level"),
                        resultSet.getString("subject"), resultSet.getString("introductionText"));
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

    public ArrayList<Course> getRecommendedCourses(String courseName) {
        ArrayList<Course> courses = new ArrayList<>();
        Connection connection = DBconnection.getConnection();
        String query = "SELECT * FROM course " +
                "WHERE courseName IN (SELECT R.recommendedCourseName FROM recommended_course R " +
                "INNER JOIN course C on C.courseName = R.courseName " +
                "WHERE C.courseName = ?)";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, courseName);
            resultSet = preparedStmt.executeQuery();

            while (resultSet.next()) {
                Course course = new Course(resultSet.getString("courseName"), resultSet.getString("level"),
                        resultSet.getString("subject"), resultSet.getString("introductionText"));
                courses.add(course);
            }
            connection.close();
            return courses;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(query);
            return null;
        }
    }

    public void addRecommended(String courseName, String RecommendedName) {
        Connection connection = DBconnection.getConnection();
        String query = "INSERT INTO recommended_course VALUES(?, ?)";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, courseName);
            preparedStmt.setString(2, RecommendedName);
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteRecommended(String courseName, String RecommendedName) {
        Connection connection = DBconnection.getConnection();
        String query = "DELETE FROM recommended_course WHERE courseName = ? AND recommendedCourseName = ?";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, courseName);
            preparedStmt.setString(2, RecommendedName);
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Integer getAmountPassedForCourse(String courseName) {
        Connection connection = DBconnection.getConnection();
        String query = "SELECT COUNT(*) FROM course AS c INNER JOIN enrollment AS e ON e.courseName = c.courseName WHERE c.courseName = ? AND e.certificate_id IS NOT NULL GROUP BY c.courseName";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, courseName);
            resultSet = preparedStmt.executeQuery();
            Integer passed = 0;
            if (resultSet.next()) {
                passed = resultSet.getInt(1);
            }
            connection.close();
            return passed;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<int[]> CertificatePerGender(String courseName) {
        ArrayList<int[]> CertificatesPerGender = new ArrayList<>();
        Connection connection = DBconnection.getConnection();
        String query = "SELECT s.gender, COUNT (e.certificate_id) AS CertificatesPerGender FROM enrollment as e INNER JOIN student as s ON s.email = e.email WHERE e.certificate_id IS NOT NULL AND e.courseName = ? GROUP BY s.gender";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, courseName);
            resultSet = preparedStmt.executeQuery();

            while (resultSet.next()) {
                int[] row = { resultSet.getInt(1), resultSet.getInt(2) };
                CertificatesPerGender.add(row);
            }

            connection.close();
            return CertificatesPerGender;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<int[]> getAllEnrollments(String courseName) {
        ArrayList<int[]> TotalEnrollments = new ArrayList<>();
        Connection connection = DBconnection.getConnection();
        String query = "SELECT s.gender, COUNT (e.enrollment_id) AS TotalEnrollments " +
                "FROM enrollment as e " +
                "INNER JOIN student as s " +
                "ON s.email = e.email " +
                "WHERE e.enrollment_id IS NOT NULL " +
                "AND e.courseName = ? " +
                "GROUP by s.gender";
        ResultSet resultSet;

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, courseName);
            resultSet = preparedStmt.executeQuery();

            while (resultSet.next()) {
                int[] row = { resultSet.getInt(1), resultSet.getInt(2) };
                TotalEnrollments.add(row);
            }

            connection.close();
            return TotalEnrollments;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public HashMap<String, String> getAvergareProgressPerModule(String courseName) {
        Connection connection = DBconnection.getConnection();
        String query = "SELECT c.courseName, ci.title, AVG(sc.progress) AS avg FROM course AS c INNER JOIN module AS e ON e.courseName = c.courseName INNER JOIN contentItem AS ci ON ci.module_id = e.module_id INNER JOIN student_contentItem as sc ON ci.contentItem_id = sc.contentItem_id GROUP BY c.courseName, ci.title HAVING c.courseName = ?";
        ResultSet resultSet;
        HashMap<String, String> list = new HashMap<>();

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, courseName);
            resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                list.put(resultSet.getString("title"), resultSet.getString("avg"));
            }
            connection.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<double[]> getPercentCertificate(String courseName) {
        ArrayList<double[]> percents = new ArrayList<>();
        ArrayList<int[]> certificates = CertificatePerGender(courseName);
        ArrayList<int[]> enrollments = getAllEnrollments(courseName);

        if (certificates.size() != 0 && enrollments.size() != 0) {
            for (int i = 0; i < certificates.size(); i++) {
                double[] percentRow = {
                        (double) certificates.get(i)[0],
                        (100.0 / enrollments.get(i)[1] * certificates.get(i)[1])
                };
                percents.add(percentRow);
            }
        }
        return percents;
    }
}
