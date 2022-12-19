package com.revature.daos;

import com.revature.models.User;

import java.util.ArrayList;

public interface UserDAOInterface {

    ArrayList<User> getUsers();

    User insertUser(User user);

    boolean deleteUserById(int id);

}
