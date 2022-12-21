package com.revature.daos;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO implements AuthDAOInterface {

    @Override
    public User login(String username, String password) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ers_users WHERE ers_username = ? AND ers_password = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("ers_username"),
                        rs.getString("ers_password"),
                        rs.getString("user_first_name"),
                        rs.getString("user_last_name"),
                        null
                );

                int userRoleFK = rs.getInt("user_role_id_fk");
                RoleDAO rDAO = new RoleDAO();
                user.setRole(rDAO.getRoleById(userRoleFK));
                user.setUser_role_id_fk(userRoleFK);
                return user;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User register(String username, String password) {
        UserDAO uDAO = new UserDAO();
        return uDAO.insertUser(new User(username, password));
    }
}
