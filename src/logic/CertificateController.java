package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.student.Certificate;

public class CertificateController {

    private DatabaseConnection DBconnection = new DatabaseConnection();

    // Adds certificate to DB

    public void addCertificate(Integer enrollmentId, Certificate certificate) {
        Connection connection = DBconnection.getConnection();
        String certificateQuery = "INSERT INTO certificate VALUES(?, ?)";
        String updateEnrollmentQuery = "UPDATE enrollment SET certificate_id = ? WHERE enrollment_id = ?";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(certificateQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, certificate.getGrade());
            preparedStmt.setString(2, certificate.getStaffName());
            preparedStmt.executeUpdate();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            rs.next();
            Integer certificateId = rs.getInt(1);
            PreparedStatement preparedCertificate = connection.prepareStatement(updateEnrollmentQuery);
            preparedCertificate.setInt(1, certificateId);
            preparedCertificate.setInt(2, enrollmentId);
            preparedCertificate.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
