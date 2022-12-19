package com.revature.models;

public class Reimbursement {

    private int reimb_id;
    private double reimb_amount;
    private String reimb_description;
    private int requester_id_fk;
    private User requester;
    private int resolver_id_fk;
    private User resolver;
    private int reimb_type_id_fk;
    private ReimbursementType reimb_type;
    private int reimb_status_id_fk;
    private ReimbursementStatus reimb_status;

    public Reimbursement(int reimb_id, double reimb_amount, String reimb_description, User requester, User resolver, ReimbursementType reimb_type, ReimbursementStatus reimb_status) {
        this.reimb_id = reimb_id;
        this.reimb_amount = reimb_amount;
        this.reimb_description = reimb_description;
        this.requester = requester;
        this.resolver = resolver;
        this.reimb_type = reimb_type;
        this.reimb_status = reimb_status;
    }

    public Reimbursement(double reimb_amount, String reimb_description, User requester, User resolver, ReimbursementType reimb_type, ReimbursementStatus reimb_status) {
        this.reimb_amount = reimb_amount;
        this.reimb_description = reimb_description;
        this.requester = requester;
        this.resolver = resolver;
        this.reimb_type = reimb_type;
        this.reimb_status = reimb_status;
    }

    public Reimbursement(double reimb_amount, String reimb_description, int requester_id_fk, int resolver_id_fk, int reimb_type_id_fk, int reimb_status_id_fk) {
        this.reimb_amount = reimb_amount;
        this.reimb_description = reimb_description;
        this.requester_id_fk = requester_id_fk;
        this.resolver_id_fk = resolver_id_fk;
        this.reimb_type_id_fk = reimb_type_id_fk;
        this.reimb_status_id_fk = reimb_status_id_fk;
    }

    public int getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(int reimb_id) {
        this.reimb_id = reimb_id;
    }

    public double getReimb_amount() {
        return reimb_amount;
    }

    public void setReimb_amount(double reimb_amount) {
        this.reimb_amount = reimb_amount;
    }

    public String getReimb_description() {
        return reimb_description;
    }

    public void setReimb_description(String reimb_description) {
        this.reimb_description = reimb_description;
    }

    public int getRequester_id_fk() {
        return requester_id_fk;
    }

    public void setRequester_id_fk(int requester_id_fk) {
        this.requester_id_fk = requester_id_fk;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public int getResolver_id_fk() {
        return resolver_id_fk;
    }

    public void setResolver_id_fk(int resolver_id_fk) {
        this.resolver_id_fk = resolver_id_fk;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public int getReimb_type_id_fk() {
        return reimb_type_id_fk;
    }

    public void setReimb_type_id_fk(int reimb_type_id_fk) {
        this.reimb_type_id_fk = reimb_type_id_fk;
    }

    public ReimbursementType getReimb_type() {
        return reimb_type;
    }

    public void setReimb_type(ReimbursementType reimb_type) {
        this.reimb_type = reimb_type;
    }

    public int getReimb_status_id_fk() {
        return reimb_status_id_fk;
    }

    public void setReimb_status_id_fk(int reimb_status_id_fk) {
        this.reimb_status_id_fk = reimb_status_id_fk;
    }

    public ReimbursementStatus getReimb_status() {
        return reimb_status;
    }

    public void setReimb_status(ReimbursementStatus reimb_status) {
        this.reimb_status = reimb_status;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimb_id=" + reimb_id +
                ", reimb_amount=" + reimb_amount +
                ", reimb_description='" + reimb_description + '\'' +
                ", requester_id_fk=" + requester_id_fk +
                ", requester=" + requester +
                ", resolver_id_fk=" + resolver_id_fk +
                ", resolver=" + resolver +
                ", reimb_type_id_fk=" + reimb_type_id_fk +
                ", reimb_type=" + reimb_type +
                ", reimb_status_id_fk=" + reimb_status_id_fk +
                ", reimb_status=" + reimb_status +
                '}';
    }
}
