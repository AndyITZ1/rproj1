package com.revature.daos;

import com.revature.models.Reimbursement;

import java.util.ArrayList;

public interface ReimbursementDAOInterface {

    // Methods that involved manipulating/accessing the Reimbursement Table
    ArrayList<Reimbursement> getAllReimbursements();
    ArrayList<Reimbursement> getAllPendingReimbursements();
    ArrayList<Reimbursement> getReimbursementsByUserId(int id);
    boolean insertReimbursement(double amount, String description, int user_id);
    boolean updateReimbursementStatus(int id, String status);
    boolean deleteReimbursementById(int id);

}
