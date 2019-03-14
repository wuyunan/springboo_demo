package com.qingteng.demo.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;


public class CreditCard implements Serializable {

    @Id
    private String id;

    private String name;
    private String cardNumber;
    private int statementDate;
    private int dueDate;

    public CreditCard() {
    }







    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(int statementDate) {
        this.statementDate = statementDate;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }


}
