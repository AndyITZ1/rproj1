package com.revature.daos;

import com.revature.models.ReimbursementType;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReimbursementTypeDAO implements ReimbursementTypeDAOInterface {

    @Override
    public ReimbursementType getReimbursementTypeById(int id) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ers_reimbursement_types WHERE reimb_type_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ReimbursementType reimbType = new ReimbursementType(
                        rs.getInt("reimb_type_id"),
                        rs.getString("reimb_type")
                );

                return reimbType;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
