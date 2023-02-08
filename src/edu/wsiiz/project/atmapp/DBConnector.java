package edu.wsiiz.project.atmapp;

import java.sql.*;

public class DBConnector {
    public static Connection connect() {
        // initialize connection
        Connection con = null;
        try {
            // Load driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            // provide credentials
            String PASSWD = "root";
            String URL = "jdbc:mysql://localhost:3306/atm";
            String USER = "root";
            // Create connect
            con = DriverManager.getConnection(URL, USER, PASSWD);
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }
        return con;
    }
}