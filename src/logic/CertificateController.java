package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CertificateController {

    private DatabaseConnection DBconnection = new DatabaseConnection();

    public void addCertificate(Integer grade, String staffName) {
        Connection connection = DBconnection.getConnection();
        String query = "INSERT INTO certificate VALUES(?, ?)";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, grade);
            preparedStmt.setString(2, staffName);
            preparedStmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
