package com.example.student.bank.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection implements IUserDataBaseConnection {
    public Connection con;

    @Override
    public Connection databaseConnection() {
        try {
            String url = System.getenv("SPRING_DATASOURCE_URL");
            String username = System.getenv("SPRING_DATASOURCE_USERNAME");
            String password = System.getenv("SPRING_DATASOURCE_PASSWORD");
            con = DriverManager.getConnection(url, username, password);
            if (con != null) {
                System.out.println("Database connection successful");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
