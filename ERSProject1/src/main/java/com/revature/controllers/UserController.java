package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.UserDAO;
import com.revature.models.User;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class UserController {

    UserDAO uDAO = new UserDAO();

    public Handler getUsersHandler = (ctx) -> {
        ArrayList<User> users = uDAO.getAllUsers();
        Gson gson = new Gson();
        String JSONUsers = gson.toJson(users);
        ctx.result(JSONUsers);
        ctx.status(200);
    };
}
