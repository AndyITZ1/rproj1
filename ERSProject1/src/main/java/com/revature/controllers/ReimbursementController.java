package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.RoleDAO;
import com.revature.models.TicketDTO;
import io.javalin.http.Handler;


public class ReimbursementController {

    ReimbursementDAO rDAO = new ReimbursementDAO();

    public Handler updateReimbStatusHandler = (ctx) -> {
        RoleDAO roleDAO = new RoleDAO();
        if (AuthController.ses != null) {
            if((Integer) AuthController.ses.getAttribute("user_role_id")
                    == roleDAO.getRoleIdByRoleTitle("Manager")) {

                String newStatus = ctx.body();
                int reimbID = Integer.parseInt(ctx.pathParam("id"));

                if (rDAO.updateReimbursementStatus(reimbID, newStatus,
                        (Integer) AuthController.ses.getAttribute("user_id"))) {
                    ctx.status(202);
                    ctx.result("Processed Ticket ID: " + reimbID);
                }
                else {
                    ctx.status(406);
                    ctx.result("Ticket was unable to process.");
                }
            }
            else {
                ctx.status(401);
                ctx.result("Only managers can process tickets!");
            }
        }
        else {
            ctx.status(401);
            ctx.result("You must be logged in to perform this action!");
        }
    };

    public Handler insertReimbursementHandler = (ctx) -> {
        if (AuthController.ses != null) {
            String body = ctx.body();
            Gson gson = new Gson();

            TicketDTO tDTO = gson.fromJson(body, TicketDTO.class);
            if (tDTO.getAmount() == 0.0 || tDTO.getDescription() == null) {
                ctx.status(400);
                ctx.result("To submit a reimbursement ticket you must have an amount and a description!");
            }
            else {
                if (rDAO.insertReimbursement(
                        tDTO.getAmount(),
                        tDTO.getDescription(),
                        (Integer) AuthController.ses.getAttribute("user_id")
                )) {
                    ctx.status(201);
                    ctx.result("Your ticket was added! Amount: " +
                            tDTO.getAmount() + " Description: " + tDTO.getDescription());
                } else {
                    ctx.status(500);
                    ctx.result("For some reason we cannot submit this ticket! " +
                            "This is an internal server problem. Please try again later!");
                }
            }
        }
        else {
            ctx.result("You must be logged in to do this action!");
            ctx.status(401);
        }
    };
}
