package org.dolta.hmp.utils;

import java.sql.SQLException;

import static org.dolta.hmp.utils.DataBaseConnection.db;

public class ShutdownHook extends Thread {
    @Override
    public void run() {
        try {
        if (db.isClosed()){
            return;
        }
        db.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}