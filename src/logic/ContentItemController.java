package logic;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContentItemController {
    private DatabaseConnection DBconnection = new DatabaseConnection();

    public ArrayList<String> getTop3Webcasts() {

        Connection connection = DBconnection.getConnection();
        ArrayList<String> webcasts = new ArrayList<>();

        String query = "SELECT title, COUNT(*) AS Amount FROM contentItem AS ci INNER JOIN student_contentItem AS sci ON sci.contentItem_id = ci.contentItem_id WHERE ci.webcast_id IS NOT NULL GROUP BY title ORDER BY Amount DESC";
        ResultSet resultSet;
        Statement statement;
        Integer i = 0;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next() && i < 3) {
                webcasts.add(resultSet.getString("title") + ": " + resultSet.getInt("Amount"));
                i++;
            }
            connection.close();
            return webcasts;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
