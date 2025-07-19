package com.example.bookshopmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseOperations {

    // JDBC URL for Windows Authentication
    private static final String JDBC_URL = "jdbc:sqlserver://DESKTOP-O53IIHF\\SQLEXPRESS:1433;databaseName=BookShopManagementSystem;integratedSecurity=true;TrustServerCertificate=true;";

    public static Connection establishConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
