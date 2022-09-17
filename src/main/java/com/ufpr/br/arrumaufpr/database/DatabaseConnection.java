package com.ufpr.br.arrumaufpr.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/arrumaufpr";
    private String username = "postgres";
    private String password = "admin";

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to connect to database. Reason: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (DatabaseConnection.instance == null || DatabaseConnection.instance.getConnection().isClosed()) {
            DatabaseConnection.instance = new DatabaseConnection();
        }

        return DatabaseConnection.instance;
    }

}
