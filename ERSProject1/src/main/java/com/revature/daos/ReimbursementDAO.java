package com.revature.daos;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class ReimbursementDAO implements ReimbursementDAOInterface{


    @Override
    public ArrayList<Reimbursement> getAllReimbursements() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ers_reimbursements;";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<Reimbursement> reimbursementList = new ArrayList();
            while(rs.next()) {
                Reimbursement r = new Reimbursement(
                        rs.getInt("reimb_id"),
                        rs.getDouble("reimb_amount"),
                        rs.getString("reimb_description"),
                        null,
                        null,
                        null,
                        null
                );
                // Setting Requester User
                int requesterFK = rs.getInt("requester_id_fk");
                UserDAO uDAO = new UserDAO();
                User requester = uDAO.getUserById(requesterFK);
                r.setRequester(requester);
                r.setRequester_id_fk(requesterFK);

                // Setting Resolver User
                int resolverFK = rs.getInt("resolver_id_fk");
                if (resolverFK != 0) {
                    User resolver = uDAO.getUserById(resolverFK);
                    r.setResolver(resolver);
                    r.setResolver_id_fk(resolverFK);
                }

                // Setting Reimbursement Type
                int reimbTypeFK = rs.getInt("reimb_type_id_fk");
                ReimbursementTypeDAO rTDAO = new ReimbursementTypeDAO();
                ReimbursementType reimbursementType = rTDAO.getReimbursementTypeById(reimbTypeFK);
                r.setReimb_type(reimbursementType);
                r.setReimb_type_id_fk(reimbTypeFK);

                // Setting Reimbursement Status
                int reimbStatusFK = rs.getInt("reimb_status_id_fk");
                ReimbursementStatusDAO rSDAO = new ReimbursementStatusDAO();
                ReimbursementStatus reimbursementStatus = rSDAO.getReimbursementStatusById(reimbStatusFK);
                r.setReimb_status(reimbursementStatus);
                r.setReimb_status_id_fk(reimbStatusFK);

                reimbursementList.add(r);
            }
            return reimbursementList;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Reimbursement> getReimbursementsByStatus(String status) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ers_reimbursements WHERE reimb_status_id_fk = " +
                    "(SELECT reimb_status_id FROM ers_reimbursement_statuses WHERE reimb_status = ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            ArrayList<Reimbursement> reimbursementList = new ArrayList();
            while (rs.next()) {
                Reimbursement r = new Reimbursement(
                        rs.getInt("reimb_id"),
                        rs.getDouble("reimb_amount"),
                        rs.getString("reimb_description"),
                        null,
                        null,
                        null,
                        null
                );
                // Setting Requester User
                int requesterFK = rs.getInt("requester_id_fk");
                UserDAO uDAO = new UserDAO();
                User requester = uDAO.getUserById(requesterFK);
                r.setRequester(requester);
                r.setRequester_id_fk(requesterFK);

                // Setting Resolver User
                int resolverFK = rs.getInt("resolver_id_fk");
                if (resolverFK != 0) {
                    User resolver = uDAO.getUserById(resolverFK);
                    r.setResolver(resolver);
                    r.setResolver_id_fk(resolverFK);
                }

                // Setting Reimbursement Type
                int reimbTypeFK = rs.getInt("reimb_type_id_fk");
                ReimbursementTypeDAO rTDAO = new ReimbursementTypeDAO();
                ReimbursementType reimbursementType = rTDAO.getReimbursementTypeById(reimbTypeFK);
                r.setReimb_type(reimbursementType);
                r.setReimb_type_id_fk(reimbTypeFK);

                // Setting Reimbursement Status
                int reimbStatusFK = rs.getInt("reimb_status_id_fk");
                ReimbursementStatusDAO rSDAO = new ReimbursementStatusDAO();
                ReimbursementStatus reimbursementStatus = rSDAO.getReimbursementStatusById(reimbStatusFK);
                r.setReimb_status(reimbursementStatus);
                r.setReimb_status_id_fk(reimbStatusFK);

                reimbursementList.add(r);
            }
            return reimbursementList;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Reimbursement> getReimbursementsByUserId(int id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ers_reimbursements WHERE requester_id_fk = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Reimbursement> reimbursementList = new ArrayList();
            while (rs.next()) {
                Reimbursement r = new Reimbursement(
                        rs.getInt("reimb_id"),
                        rs.getDouble("reimb_amount"),
                        rs.getString("reimb_description"),
                        null,
                        null,
                        null,
                        null
                );
                // Setting Requester User
                int requesterFK = rs.getInt("requester_id_fk");
                UserDAO uDAO = new UserDAO();
                User requester = uDAO.getUserById(requesterFK);
                r.setRequester(requester);
                r.setRequester_id_fk(requesterFK);

                // Setting Resolver User
                int resolverFK = rs.getInt("resolver_id_fk");
                if (resolverFK != 0) {
                    User resolver = uDAO.getUserById(resolverFK);
                    r.setResolver(resolver);
                    r.setResolver_id_fk(resolverFK);
                }

                // Setting Reimbursement Type
                int reimbTypeFK = rs.getInt("reimb_type_id_fk");
                ReimbursementTypeDAO rTDAO = new ReimbursementTypeDAO();
                ReimbursementType reimbursementType = rTDAO.getReimbursementTypeById(reimbTypeFK);
                r.setReimb_type(reimbursementType);
                r.setReimb_type_id_fk(reimbTypeFK);

                // Setting Reimbursement Status
                int reimbStatusFK = rs.getInt("reimb_status_id_fk");
                ReimbursementStatusDAO rSDAO = new ReimbursementStatusDAO();
                ReimbursementStatus reimbursementStatus = rSDAO.getReimbursementStatusById(reimbStatusFK);
                r.setReimb_status(reimbursementStatus);
                r.setReimb_status_id_fk(reimbStatusFK);

                reimbursementList.add(r);
            }
            return reimbursementList;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reimbursement getReimbursementById(int id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ers_reimbursements WHERE reimb_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Reimbursement r = new Reimbursement(
                        rs.getInt("reimb_id"),
                        rs.getDouble("reimb_amount"),
                        rs.getString("reimb_description"),
                        null,
                        null,
                        null,
                        null
                );
                // Setting Requester User
                int requesterFK = rs.getInt("requester_id_fk");
                UserDAO uDAO = new UserDAO();
                User requester = uDAO.getUserById(requesterFK);
                r.setRequester(requester);
                r.setRequester_id_fk(requesterFK);

                // Setting Resolver User
                int resolverFK = rs.getInt("resolver_id_fk");
                if (resolverFK != 0) {
                    User resolver = uDAO.getUserById(resolverFK);
                    r.setResolver(resolver);
                    r.setResolver_id_fk(resolverFK);
                }

                // Setting Reimbursement Type
                int reimbTypeFK = rs.getInt("reimb_type_id_fk");
                ReimbursementTypeDAO rTDAO = new ReimbursementTypeDAO();
                ReimbursementType reimbursementType = rTDAO.getReimbursementTypeById(reimbTypeFK);
                r.setReimb_type(reimbursementType);
                r.setReimb_type_id_fk(reimbTypeFK);

                // Setting Reimbursement Status
                int reimbStatusFK = rs.getInt("reimb_status_id_fk");
                ReimbursementStatusDAO rSDAO = new ReimbursementStatusDAO();
                ReimbursementStatus reimbursementStatus = rSDAO.getReimbursementStatusById(reimbStatusFK);
                r.setReimb_status(reimbursementStatus);
                r.setReimb_status_id_fk(reimbStatusFK);

                return r;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertReimbursement(double amount, String description, int user_id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO ers_reimbursements(reimb_amount, reimb_description, requester_id_fk) " +
                    "VALUES (?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, description);
            ps.setInt(3, user_id);
            ps.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateReimbursementStatus(int id, String status, int resolverId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE ers_reimbursements SET reimb_status_id_fk = ?, resolver_id_fk = ? WHERE reimb_id = ?;";
            ReimbursementStatusDAO rSDAO = new ReimbursementStatusDAO();
            PreparedStatement ps = conn.prepareStatement(sql);

            // NOTE: If 'status' is not a valid/existing status, then the FK constraint will throw an error
            // This is done on the database side of things.
            ps.setInt(1, rSDAO.getReimbursementStatusIdByStatus(status));
            ps.setInt(2, resolverId);
            ps.setInt(3, id);
            ps.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteReimbursementById(int id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM ers_reimbursements WHERE reimb_id = ?;";
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
