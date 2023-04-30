package org.dolta.hmp.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    static public final Connection db;

    static {
        try {
            db = DriverManager.getConnection("jdbc:postgresql://portaler.org:5445/hmp_test", "postgres", "161616");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DataBaseConnection() throws SQLException {
    }
}