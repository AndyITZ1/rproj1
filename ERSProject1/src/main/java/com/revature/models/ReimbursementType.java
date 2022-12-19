package com.revature.models;

public class ReimbursementType {
    private int reimb_type_id;
    private String reimb_type;

    public ReimbursementType() {

    }

    public ReimbursementType(int reimb_type_id, String reimb_type) {
        this.reimb_type_id = reimb_type_id;
        this.reimb_type = reimb_type;
    }

    public ReimbursementType(String reimb_type) {
        this.reimb_type = reimb_type;
    }

    public int getReimb_type_id() {
        return reimb_type_id;
    }

    public void setReimb_type_id(int reimb_type_id) {
        this.reimb_type_id = reimb_type_id;
    }

    public String getReimb_type() {
        return reimb_type;
    }

    public void setReimb_type(String reimb_type) {
        this.reimb_type = reimb_type;
    }

    @Override
    public String toString() {
        return "ReimbursementType{" +
                "reimb_type_id=" + reimb_type_id +
                ", reimb_type='" + reimb_type + '\'' +
                '}';
    }
}
