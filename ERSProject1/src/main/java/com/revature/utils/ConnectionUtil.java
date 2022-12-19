package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Problem occurred locating driver!");
        }

        // No hardcoding of credentials, all credentials are now environment variables.
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");

        return DriverManager.getConnection(url, user, password);
    }
}
