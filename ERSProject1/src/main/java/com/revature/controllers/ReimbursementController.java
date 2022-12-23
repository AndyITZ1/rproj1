package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementStatusDAO;
import com.revature.daos.RoleDAO;
import com.revature.models.Reimbursement;
import com.revature.models.TicketDTO;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.Locale;


public class ReimbursementController {

    ReimbursementDAO rDAO = new ReimbursementDAO();

    public Handler getAllReimbursementsHandler = (ctx) -> {
        RoleDAO roleDAO = new RoleDAO();
        if (AuthController.ses != null) {
            if ((Integer) AuthController.ses.getAttribute("user_role_id")
                    == roleDAO.getRoleIdByRoleTitle("Manager")) {
                ArrayList<Reimbursement> reimbursements = rDAO.getAllReimbursements();
                Gson gson = new Gson();
                String JSONReimbursements = gson.toJson(reimbursements);
                ctx.result(JSONReimbursements);
                ctx.status(202);
            }
            else {
                ctx.result("You are not authorized to perform this functionality!");
                ctx.status(401);
            }
        }
        else {
            ctx.result("You must be logged in to see this!");
            ctx.status(401);
        }
    };

    public Handler getAllPastReimbSubmissionsHandler = (ctx) -> {
        if (AuthController.ses != null) {
            int viewUser = Integer.parseInt(ctx.pathParam("id"));
            RoleDAO roleDAO = new RoleDAO();
            if (((Integer) AuthController.ses.getAttribute("user_id") == viewUser) ||
                    ((Integer) AuthController.ses.getAttribute("user_role_id")
                    == roleDAO.getRoleIdByRoleTitle("Manager"))) {
                ArrayList<Reimbursement> reimbursements =
                        rDAO.getReimbursementsByUserId(Integer.parseInt(ctx.pathParam("id")));
                Gson gson = new Gson();
                String JSONReimbursements = gson.toJson(reimbursements);
                ctx.result(JSONReimbursements);
                ctx.status(202);
            }
            else {
                ctx.result("You are not authorized to view this user's records!");
                ctx.status(401);
            }
        }
        else {
            ctx.result("You must be logged in to see this!");
            ctx.status(401);
        }
    };

    public Handler getAllReimbByStatusHandler = (ctx) -> {
        RoleDAO roleDAO = new RoleDAO();
        if (AuthController.ses != null) {
            if ((Integer) AuthController.ses.getAttribute("user_role_id")
                    == roleDAO.getRoleIdByRoleTitle("Manager")) {
                ArrayList<Reimbursement> reimbursements = rDAO.getReimbursementsByStatus(ctx.pathParam("status"));
                Gson gson = new Gson();
                String JSONReimbursements = gson.toJson(reimbursements);
                ctx.result(JSONReimbursements);
                ctx.status(202);
            }
            else {
                ctx.result("You are not authorized to perform this functionality!");
                ctx.status(401);
            }
        }
        else {
            ctx.result("You must be logged in to see this!");
            ctx.status(401);
        }
    };
    public Handler updateReimbStatusHandler = (ctx) -> {
        RoleDAO roleDAO = new RoleDAO();
        if (AuthController.ses != null) {
            if((Integer) AuthController.ses.getAttribute("user_role_id")
                    == roleDAO.getRoleIdByRoleTitle("Manager")) {

                String newStatus = ctx.body();
                if (newStatus.equalsIgnoreCase("approved") || newStatus.equalsIgnoreCase("denied")) {
                    int reimbID = Integer.parseInt(ctx.pathParam("id"));
                    ReimbursementStatusDAO rSDAO = new ReimbursementStatusDAO();
                    if (rDAO.getReimbursementById(reimbID).getReimb_status_id_fk() != rSDAO.getReimbursementStatusIdByStatus("Pending")) {
                        ctx.status(401);
                        ctx.result("Processing NOT ALLOWED! This reimbursement ticket has been processed already.");
                    }
                    else {
                        if (rDAO.updateReimbursementStatus(reimbID, newStatus,
                                (Integer) AuthController.ses.getAttribute("user_id"))) {
                            ctx.status(202);
                            ctx.result("Processed Ticket ID: " + reimbID);
                        } else {
                            ctx.status(406);
                            ctx.result("Ticket was unable to be processed by system.");
                        }
                    }
                }
                else {
                    ctx.status(406);
                    ctx.result("Tickets can only be processed with 'Approved' or 'Denied'");
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
            if (tDTO == null || tDTO.getAmount() == null || tDTO.getDescription() == null) {
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
