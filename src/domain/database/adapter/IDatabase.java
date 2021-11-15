package domain.database.adapter;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabase {

    Connection connect() throws SQLException;
    void getData(String str) throws SQLException;

}
