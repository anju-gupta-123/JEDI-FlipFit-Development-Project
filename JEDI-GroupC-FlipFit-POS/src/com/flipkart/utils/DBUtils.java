package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/flipfit_db";
    private static final String USER = "root";
    private static final String PASSWORD = "t@x1.n00n.t0Ll";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    // Static block to load the driver
    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.err.println("Database Driver not found: " + e.getMessage());
        }
    }

    // Method to establish and return a connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

