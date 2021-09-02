package com.mindhub.homebanking.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homebanking.controllers.ClientController;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Client() {
    }

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    Set<Account> accounts = new HashSet<>();


    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        account.setClient(this);
        accounts.add(account);
    }

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    Set<ClientLoan> clientLoans = new HashSet<>();
    public Set<ClientLoan> getClientLoans() {
        return clientLoans; }

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    Set<Card> cards = new HashSet<>();
    public Set<Card> getCards() {
        return cards; }



    public List<Loan> getLoans() {
        return clientLoans.stream().map(sub -> sub.getLoan()).collect(toList());
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Client=" + " " + "ID: "+ id +
                "First name:"+ " " + firstName +
                ", Last name:" + " " + lastName +
                ", Email:" +" "+ email + " ";
    }

    public Client(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



};
