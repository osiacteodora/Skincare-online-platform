package com.example.demo.Repository.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static java.sql.Connection context = null;
    private final static String URL = "jdbc:mysql://localhost:3306/proiect1";
    private final static String USER = "client";
    private final static String PASSWORD = "QwErTy120";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static void connectionCreator() {
        try {
            Class.forName(DRIVER);
            context = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static java.sql.Connection getConnection() {
        if (context == null) {
            connectionCreator();
        }
        return context;
    }
}