package com.mindhub.homebanking.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;


    private Double amount;
    private int payments;
    private double totalAmount;
    private String account;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="loan_id")
    private Loan loan;

    public ClientLoan(Double amount, int payments, Client client, Loan loan, String account) {
        this.amount = amount;
        this.payments = payments;
        this.totalAmount = amount + (amount * (loan.getInterest() / 100));
        this.client = client;
        this.loan = loan;
        this.account = account;
    };

    public ClientLoan(){

    };



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @JsonIgnore public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @JsonIgnore public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "ClientLoan{" +
                "id=" + id +
                ", amount=" + amount +
                ", payments=" + payments +
                ", totalAmount=" + totalAmount +
                ", account='" + account + '\'' +
                ", client=" + client +
                ", loan=" + loan +
                '}';
    }
}
