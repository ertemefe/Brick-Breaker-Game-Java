package domain.database;

import domain.objects.obstacles.Obstacle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SaveMap {

    public Connection connect() throws SQLException {
        String host = "ec2-54-73-152-36.eu-west-1.compute.amazonaws.com";
        String database = "daj79qig51jba3";
        String url = "jdbc:postgresql://" + host + "/" + database;
        String user = "txrnpayubogotd";
        String password = "71eee9c2342118989eee3a3079e78a93291b6564de098fbcafcb1f7466596459";

        Connection conn = DriverManager.getConnection(url, user, password);
        if (conn != null) {
            System.out.println("Connected to the database!");
            return conn;
        } else {
            System.out.println("Failed to make connection!");
            return null;
        }
    }

    public void saveValue(List<Obstacle> obstacleList, String map_id) throws SQLException {
        Connection conn = connect();
        String query = " INSERT INTO maps"
                + " (map_id, obstacle_type, x, y, firmness, gift, movement) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(query);

        for(Obstacle obstacle: obstacleList){
            statement.setString(1, map_id);
            statement.setString(2, obstacle.getType());
            statement.setInt(3, obstacle.getCoordinates().x);
            statement.setInt(4,obstacle.getCoordinates().y);
            statement.setInt(5, obstacle.getFirmness());
            statement.setBoolean(6, obstacle.isGift());
            statement.setBoolean(7,obstacle.isMovement());

            statement.addBatch();
            statement.executeBatch();
        }


    }

}
