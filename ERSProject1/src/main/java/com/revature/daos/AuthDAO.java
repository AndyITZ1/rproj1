package com.revature.daos;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthDAO implements AuthDAOInterface {

    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public User register(String username, String password) {
        UserDAO uDAO = new UserDAO();
        return uDAO.insertUser(new User(username, password));
    }
}
