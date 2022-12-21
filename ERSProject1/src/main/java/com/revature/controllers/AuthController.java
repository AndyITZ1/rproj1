package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.AuthDAO;
import com.revature.models.AuthDTO;
import com.revature.models.User;
import io.javalin.http.Handler;

import javax.servlet.http.HttpSession;

public class AuthController {

    AuthDAO aDAO = new AuthDAO();

    public static HttpSession ses;

    public Handler loginHandler = (ctx) -> {
        if (ses != null) {
        String body = ctx.body();
        Gson gson = new Gson();
        AuthDTO aDTO = gson.fromJson(body, AuthDTO.class);
        User loggedInUser = aDAO.login(aDTO.getUsername(), aDTO.getPassword());
        if (loggedInUser != null) {
            ses = ctx.req.getSession();

            ses.setAttribute("user_role_id", loggedInUser.getUser_role_id_fk());
            ses.setAttribute("user_id", loggedInUser.getUser_id());

            String userJSON = gson.toJson(loggedInUser);

            ctx.result(userJSON);
            ctx.status(202);
        }
        else {
            ctx.status(401);
        }
    };

    public Handler registerHandler = (ctx) -> {
        String body = ctx.body();
        Gson gson = new Gson();
        AuthDTO aDTO = gson.fromJson(body, AuthDTO.class);
        if (aDTO.getPassword() == null || aDTO.getUsername() == null) {
            ctx.status(400);
            ctx.result("Failed to register! Need both a username and password to REGISTER!");
        }
        else {
            User registeredUser = aDAO.register(aDTO.getUsername(), aDTO.getPassword());
            if (registeredUser != null) {
                String userJSON = gson.toJson(registeredUser);
                ctx.result(userJSON);
                ctx.status(201);
            }
            else {
                /*
                Decide to separate Username check later if necessary
                Meaning maybe make a SELECT to see if username is there prior to inserting
                even though inserting will crash SELECT as a query before that means 2 queries will
                run also only reason to separate is that if the INSERT crashes separate from duplicate username
                then we know it is a real error probably with the system.
                */
                ctx.status(400);
                ctx.result("Failed to register! Username taken already!");
            }
        }
    };
}
