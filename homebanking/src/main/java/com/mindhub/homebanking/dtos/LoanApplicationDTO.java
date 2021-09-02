package com.mindhub.homebanking.dtos;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class LoanApplicationDTO {
    private String name;
    private double amount;
    private int payments;
    private String toAccountNumber;

    public LoanApplicationDTO(){

    };

    public LoanApplicationDTO( String name, double amount, int payments, String toAccountNumber) {
        this.name = name;
        this.amount = amount;
        this.payments = payments;
        this.toAccountNumber = toAccountNumber;
    };


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    @Override
    public String toString() {
        return "LoanApplicationDTO{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", payments=" + payments +
                ", toAccountNumber='" + toAccountNumber + '\'' +
                '}';
    }
};
