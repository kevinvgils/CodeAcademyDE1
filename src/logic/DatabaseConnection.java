package logic;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection getConnection() {
        String dbConnection = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyIpsum";
        String user = "ipsum";
        String password = "LoremIpsum01";
        try {
            Connection connection = DriverManager.getConnection(dbConnection, user, password);
            return connection;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
