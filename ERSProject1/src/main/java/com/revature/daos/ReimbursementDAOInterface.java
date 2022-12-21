package com.revature.daos;

import com.revature.models.Reimbursement;

import java.util.ArrayList;

public interface ReimbursementDAOInterface {

    // Methods that involved manipulating/accessing the Reimbursement Table
    ArrayList<Reimbursement> getAllReimbursements();
    ArrayList<Reimbursement> getReimbursementsByStatus(String status);
    ArrayList<Reimbursement> getReimbursementsByUserId(int id);
    Reimbursement getReimbursementById(int id);
    boolean insertReimbursement(double amount, String description, int user_id);
    boolean updateReimbursementStatus(int id, String status, int resolverId);
    boolean deleteReimbursementById(int id);

}
