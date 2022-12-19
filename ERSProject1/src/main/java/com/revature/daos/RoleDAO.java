package com.revature.daos;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO implements RoleDAOInterface{

    @Override
    public Role getRoleById(int id) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ers_user_roles WHERE user_role_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Role role = new Role(
                        rs.getInt("user_role_id"),
                        rs.getString("user_role")
                );

                return role;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
