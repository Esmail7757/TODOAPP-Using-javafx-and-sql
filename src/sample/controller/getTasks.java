package sample.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface getTasks {

    public int getTasksByUser(int userId) throws SQLException, ClassNotFoundException;
}
