package com.revature.daos;

import com.revature.models.ReimbursementStatus;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReimbursementStatusDAO implements ReimbursementStatusDAOInterface{


    @Override
    public ReimbursementStatus getReimbursementStatusById(int id) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ers_reimbursement_statuses WHERE reimb_status_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ReimbursementStatus reimbStatus = new ReimbursementStatus(
                        rs.getInt("reimb_status_id"),
                        rs.getString("reimh_status")
                );

                return reimbStatus;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
