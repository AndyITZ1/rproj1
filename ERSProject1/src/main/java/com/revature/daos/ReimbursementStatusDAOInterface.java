package com.revature.daos;

import com.revature.models.ReimbursementStatus;

public interface ReimbursementStatusDAOInterface {

    ReimbursementStatus getReimbursementStatusById(int id);

    int getReimbursementStatusIdByStatus(String status);

}
