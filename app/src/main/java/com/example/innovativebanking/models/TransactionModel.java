package com.example.innovativebanking.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "transaction")
public class TransactionModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "transaction_value")
    private float money;
    @ColumnInfo(name = "transaction_date")
    private String date;
    @ColumnInfo(name = "transaction_name")
    private String name;
    @ColumnInfo(name = "userId")
    private int userId;

    @Ignore
    private String transactionImage;
    @Ignore
    private boolean isChecked = false;

    public TransactionModel(float money, String date, String name, int userId) {
        this.money = money;
        this.date = date;
        this.name = name;
        this.userId = userId;
    }

    public TransactionModel(float money, String date, String name, String image) {
        this.transactionImage = image;
        this.money = money;
        this.date = date;
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return " " + name + "    " + money + " RON" + "\n" +
                " " + date;
    }
}
