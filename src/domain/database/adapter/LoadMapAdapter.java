package domain.database.adapter;

import java.sql.Connection;
import java.sql.SQLException;

public class LoadMapAdapter implements IDatabase{

    LoadMap loadMap = new LoadMap();

    public LoadMapAdapter() throws SQLException {

    }

    public Connection connect() throws SQLException {
        return null;
    }


    public void getData(String str) throws SQLException {

    }
}
