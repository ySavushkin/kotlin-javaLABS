package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseConnection {
    private static Connection connection;

    static {
        try {
            //для себе робив контейнер з базою у докері
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Transport", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
