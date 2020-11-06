package com.example.innovativebanking.models;

import java.util.Date;

public class TransactionModel {

    private float money;
    private Date date;
    private String name;

    public TransactionModel(float money, Date date, String name) {
        this.money = money;
        this.date = date;
        this.name = name;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " +
                name + "\n" +
                "Money: " + money + "\n" +
                "Date: " + date;
    }

}
