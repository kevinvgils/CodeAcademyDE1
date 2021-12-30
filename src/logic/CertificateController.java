package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CertificateController {

    private DatabaseConnection DBconnection = new DatabaseConnection();

    public void addCertificate(Integer enrollmentId, Integer grade, String staffName) {
        Connection connection = DBconnection.getConnection();
        String certificateQuery = "INSERT INTO certificate VALUES(?, ?)";
        String updateEnrollmentQuery = "UPDATE enrollment SET certificate_id = ? WHERE enrollment_id = ?";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(certificateQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, grade);
            preparedStmt.setString(2, staffName);
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
