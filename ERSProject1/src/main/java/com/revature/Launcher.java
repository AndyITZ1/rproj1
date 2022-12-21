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
//        UserController uc = new UserController();
        ReimbursementController rc = new ReimbursementController();

        // Login & Register Endpoints
        app.post("/register", ac.registerHandler);
        app.post("/login", ac.loginHandler);

        // Reimbursement Endpoints

        // Get All Reimbursements for Pending
        app.get("/reimbursements/filter/status={status}", rc.getAllReimbByStatusHandler);
        // View ALl Reimbursements for an Employee
        app.get("/reimbursements/filter/requester={id}", rc.getAllPastReimbSubmissionsHandler);
        // Get All Reimbursements
        app.get("/reimbursements", rc.getAllReimbursementsHandler);
        // Submit a Reimbursement Ticket
        app.post("/reimbursements", rc.insertReimbursementHandler);
        // Update Status of a Specific Reimbursement
        app.patch("/reimbursements/{id}", rc.updateReimbStatusHandler);






    }

}
