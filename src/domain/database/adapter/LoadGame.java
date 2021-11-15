package domain.database.adapter;

import java.sql.*;
import java.util.ArrayList;

public class LoadGame implements IDatabase {

    ResultSet response;
    Connection connection;
    ArrayList gameData;

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

    public void getData(String str) throws SQLException {

        if(validateUsername(str)){
            /*while (response.next()) {
                gameData.add(response);
            }*/
            System.out.println("username doğru");
        }else System.out.println("username yok");
        //else username bulunamadı diye pop-up error tetikle

    }

    private boolean validateUsername(String username) throws SQLException {
        Connection conn = connect();
        String query = "SELECT * FROM load_game WHERE `username` = " + username;
        Statement stmt = conn.createStatement();
        response = stmt.executeQuery(query);

        return response != null;
    }

}
