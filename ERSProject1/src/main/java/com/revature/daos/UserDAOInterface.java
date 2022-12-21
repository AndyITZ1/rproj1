package com.revature.daos;

import com.revature.models.User;

import java.util.ArrayList;

public interface UserDAOInterface {

    ArrayList<User> getAllUsers();

    User getUserById(int id);

    User insertUser(User user);

    boolean updateUserRole(int id, int roleId);

    boolean deleteUserById(int id);

}
