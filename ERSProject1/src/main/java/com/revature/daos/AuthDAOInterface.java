package com.revature.daos;

import com.revature.models.User;

public interface AuthDAOInterface {

    User login(String username, String password);

    User register(String username, String password);
}
