package domain.database;

import domain.Controller;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;


public class LoadMap {
    Connection connection;
    public ArrayList<String> mapIDList= new ArrayList<>();
    Controller controller = Controller.getInstance();

    public LoadMap() throws SQLException {
        connection = connect();
        exists();
    }

    public Connection connect() throws SQLException {
        String host = "ec2-54-73-152-36.eu-west-1.compute.amazonaws.com";
        String database = "daj79qig51jba3";
        String url = "jdbc:postgresql://" + host + "/" + database;
        String user = "txrnpayubogotd";
        String password = "71eee9c2342118989eee3a3079e78a93291b6564de098fbcafcb1f7466596459";

        return DriverManager.getConnection(url, user, password);
    }

    public void exists() throws SQLException {
        String str = "SELECT DISTINCT map_id FROM maps";
        ResultSet rs = connection.createStatement().executeQuery(str);
        while (rs.next()) {
            if(!mapIDList.contains(rs.getString(1)))
            mapIDList.add(rs.getString(1));
        }
    }

    public void getData(String str) throws SQLException {
        String query = "SELECT * FROM maps WHERE map_id= '" + str+ "'";
        ResultSet rs = connection.createStatement().executeQuery(query);
        int i=0;
        while(rs.next()){
            i++;
            controller.addObstacle(rs.getString("obstacle_type"));
            controller.obstacles.get(controller.spawnLocation.get(i)).setCoordinates(new Point(rs.getInt("x"),rs.getInt("y")));
            controller.obstacles.get(controller.spawnLocation.get(i)).setFirmness(rs.getInt("firmness"));
            controller.obstacles.get(controller.spawnLocation.get(i)).setGift(rs.getBoolean("gift"));
            controller.obstacles.get(controller.spawnLocation.get(i)).setMovement(rs.getBoolean("movement"));
        }

    }

}
