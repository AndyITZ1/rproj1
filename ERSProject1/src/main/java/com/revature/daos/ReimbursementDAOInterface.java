package com.revature.daos;

import com.revature.models.Reimbursement;

import java.util.ArrayList;

public interface ReimbursementDAOInterface {

    // Methods that involved manipulating/accessing the Reimbursement Table
    ArrayList<Reimbursement> getAllReimbursements();
    ArrayList<Reimbursement> getAllPendingReimbursements();
    ArrayList<Reimbursement> getReimbursementsByUserId(int id);
    Reimbursement insertReimbursement(Reimbursement reimb);
    boolean updateReimbursementStatus(int id, String status);
    boolean deleteReimbursementById(int id);

}
