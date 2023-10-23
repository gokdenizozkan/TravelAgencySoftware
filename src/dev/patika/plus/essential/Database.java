package dev.patika.plus.essential;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection connection;

    static {
        try {
            Class.forName(Config.Database.DRIVER);
            establishConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void establishConnection() {
        try {
            connection = DriverManager.getConnection(Config.Database.URL, Config.Database.USERNAME,
                    Config.Database.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
