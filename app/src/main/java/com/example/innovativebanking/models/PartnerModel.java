package com.example.innovativebanking.models;

public class PartnerModel {

    private String name;
    private String account;

    public PartnerModel(String name, String account) {
        this.name = name;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return name + '\n' + account;
    }
}
