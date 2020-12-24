package com.example.innovativebanking.models;

import java.util.Date;

public class TransactionModel {

    private Long id;
    private float money;
    private Date date;
    private String name;
    private String transactionImage;
    private boolean isChecked = false;

    public TransactionModel(float money, Date date, String name, Long id, String image) {
        this.transactionImage = image;
        this.id = id;
        this.money = money;
        this.date = date;
        this.name = name;
    }

    public TransactionModel(TransactionModel c1, int i) {
        this.id = c1.getId() + i;
        this.name = c1.name;
        this.date = c1.date;
        this.money = c1.money;
    }

    public TransactionModel() {

    }

    public String getImage() {
        return transactionImage;
    }

    public void setImage(String transactionImage) {
        this.transactionImage = transactionImage;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setNato(boolean isChecked) {
        isChecked = isChecked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
