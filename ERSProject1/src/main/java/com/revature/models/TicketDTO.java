package com.revature.models;

public class TicketDTO {

    private Double amount;
    private String description;

    public TicketDTO(Double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
