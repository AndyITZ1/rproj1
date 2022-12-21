package com.revature;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
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

        // Controllers
        AuthController ac = new AuthController();
        UserController uc = new UserController();
        ReimbursementController rc = new ReimbursementController();

        // Login & Register Endpoints
        app.post("/register", ac.registerHandler);
        app.post("/login", ac.loginHandler);

        // Reimbursement Endpoints
        app.get("/reimbursements", null);
        app.post("/reimbursements", rc.insertReimbursementHandler);
        app.patch("/reimbursements/{id}", null);
        app.get("/reimbursements?status={status}", null);
        app.get("/reimbursements?requester={id}", null);





    }

}
