package com.mindhub.homebanking.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String number;
    private LocalDateTime creationDate;
    private Double balance;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn (name="client_id")
    private Client client;

    @OneToMany(mappedBy="account", fetch=FetchType.EAGER)
    Set<Transaction> transactions = new HashSet<>();

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transaction.setAccount(this);
        transactions.add(transaction);
    }

    public Account() {
};
    public Account(String number, LocalDateTime creationDate, Double balance, Client client) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client = client;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @JsonIgnore public Client getClient(){
        return client;
    }

    @Override
    public String toString() {
        return "Account Information" + " " + "ID: " + id +
                "Account Number:" + " " + number +
                ", Creation date:" + " " + creationDate +
                ", Balance:" + " " + balance + " ";
    }

    public void setClient(Client client) {
    }
};

