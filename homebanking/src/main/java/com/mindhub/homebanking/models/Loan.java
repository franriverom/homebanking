package com.mindhub.homebanking.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private Double maxAmount;
    private Double interest;
    @ElementCollection
    @Column (name = "payments")
    private List<Integer> payments = new ArrayList<>();

    @OneToMany(mappedBy="loan", fetch=FetchType.EAGER)
    Set<ClientLoan> clientLoans = new HashSet<>();

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public List<Client> getClients() {
        return clientLoans.stream().map(sub -> sub.getClient()).collect(toList());
    }

    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public Loan() {
    };

    public Loan(String name, Double maxAmount, Double interest, List<Integer> payments) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.interest = interest;
        this.payments = (List<Integer>) payments;
    }

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

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }


    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxAmount=" + maxAmount +
                ", interest=" + interest +
                ", payments=" + payments +
                ", clientLoans=" + clientLoans +
                '}';
    }
}