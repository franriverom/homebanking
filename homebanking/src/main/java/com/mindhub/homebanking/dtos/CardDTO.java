package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

public class CardDTO {
    private long id;
    private String cardHolder;
    private String number;
    private int cvv;
    private CardType cardType;
    private CardColor cardColor;
    private LocalDateTime fromDate;
    private LocalDateTime thruDate;



    public CardDTO(){

    };
    public CardDTO(Card card){
        this.id = card.getId();
        this.cardHolder = card.getCardHolder();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.cardType = card.getCardType();
        this.cardColor = card.getCardColor();
        this.fromDate = card.getFromDate();
        this.thruDate = card.getThruDate();
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


    public String toString() {
        return "CardDTO{" +
                "id=" + id +
                ", cardHolder='" + cardHolder + '\'' +
                ", number=" + number +
                ", cvv=" + cvv +
                ", cardType=" + cardType +
                ", cardColor=" + cardColor +
                ", fromDate=" + fromDate +
                ", thruDate=" + thruDate +
                '}';
    }
}