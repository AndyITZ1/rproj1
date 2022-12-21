package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.TicketDTO;
import io.javalin.http.Handler;


public class ReimbursementController {

    ReimbursementDAO rDAO = new ReimbursementDAO();

    public Handler insertReimbursementHandler = (ctx) -> {
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
            }
            else {
                ctx.status(500);
                ctx.result("For some reason we cannot submit this ticket! " +
                        "This is an internal server problem. Please try again later!");
            }
        }
    };
}
