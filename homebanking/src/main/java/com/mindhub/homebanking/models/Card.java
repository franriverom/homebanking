package com.mindhub.homebanking.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jayway.jsonpath.internal.function.text.Concatenate;
import com.mindhub.homebanking.controllers.ClientController;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.query.Query;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String cardHolder;
    private String number;
    private int cvv;
    private CardType cardType;
    private CardColor cardColor;
    private LocalDateTime fromDate;
    private LocalDateTime thruDate;
    
    public Card(){
        
    }
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn (name="client_id")
    private Client client;

    public Card(String cardHolder, String number, int cvv, CardType cardType, CardColor cardColor, LocalDateTime fromDate, LocalDateTime thruDate, Client client) {
        this.cardHolder = client.getFirstName()+" "+client.getLastName();
        this.number = number;
        this.cvv = cvv;
        this.cardType = cardType;
        this.cardColor = cardColor;
        this.fromDate = fromDate;
        this.thruDate = thruDate;
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDateTime thruDate) {
        this.thruDate = thruDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardHolder='" + cardHolder + '\'' +
                ", number=" + number +
                ", cvv=" + cvv +
                ", cardType=" + cardType +
                ", cardColor=" + cardColor +
                ", fromDate=" + fromDate +
                ", thruDate=" + thruDate +
                ", client=" + client +
                '}';
    }
};
