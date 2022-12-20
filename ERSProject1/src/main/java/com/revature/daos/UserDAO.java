package com.revature.daos;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements UserDAOInterface {

    @Override
    public ArrayList<User> getUsers() {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM ers_users;";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<User> users = new ArrayList();

            while (rs.next()) {
                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("ers_username"),
                        rs.getString("ers_password"),
                        rs.getString("user_first_name"),
                        rs.getString("user_last_name"),
                        null
                );
                int userRoleFK = rs.getInt("user_role_id_fk");
                u.setUser_role_id_fk(userRoleFK);

                RoleDAO rDAO = new RoleDAO();
                Role r = rDAO.getRoleById(userRoleFK);
                users.add(u);
            }

            return users;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User insertUser(User user) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO ers_users(ers_username, ers_password, user_first_name, user_last_name, user_role_id_fk) VALUES (?, ?, ?, ?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getErs_username());
            ps.setString(2, user.getErs_password());
            ps.setString(3, user.getUser_first_name());
            ps.setString(4, user.getUser_last_name());
            ps.setInt(5, user.getUser_role_id_fk());

            ps.executeUpdate();
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUserById(int id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM ers_users WHERE user_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}