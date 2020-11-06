package com.example.innovativebanking.models;

public class UserModel {

    private float balance;
    private String userName;

    public UserModel(float balance, String userName) {
        this.balance = balance;
        this.userName = userName;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "balance=" + balance +
                ", userName='" + userName + '\'' +
                '}';
    }

}
