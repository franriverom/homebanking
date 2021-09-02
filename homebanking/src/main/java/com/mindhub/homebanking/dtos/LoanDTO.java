package com.mindhub.homebanking.dtos;
import com.mindhub.homebanking.models.Loan;

import java.util.List;


public class LoanDTO {
    private long id;
    private String name;
    private Double amount;
    private Double interest;
    private List<Integer> payments;

    public LoanDTO(){

    };

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.amount = loan.getMaxAmount();
        this.interest = loan.getInterest();
        this.payments = loan.getPayments();
    };



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterest() { return interest; }

    public void setInterest(Double interest) { this.interest = interest; }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "LoanDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", interest=" + interest +
                ", payments=" + payments +
                '}';
    }
};
