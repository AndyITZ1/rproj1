package com.revature;

import com.revature.utils.ConnectionUtil;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            System.out.println("Connection Successful!");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed!");
        }

        // Javalin Web Server Setup
        Javalin app = Javalin.create(
                config -> {
                    config.enableCorsForAllOrigins();
                    System.out.println("Running on port 3000!");
                }
        ).start(3000);
        // Running on localhost:3000


    }

}
