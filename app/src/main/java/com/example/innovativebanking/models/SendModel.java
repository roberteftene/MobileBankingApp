package com.example.innovativebanking.models;

public class SendModel {

    private String personName;
    private float value;
    private String account;
    private boolean isPartener;

    public SendModel(String personName, float value, String account, boolean isPartener) {
        this.personName = personName;
        this.value = value;
        this.account = account;
        this.isPartener = isPartener;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isPartener() {
        return isPartener;
    }

    public void setPartener(boolean partener) {
        isPartener = partener;
    }

    @Override
    public String toString() {
        return "SendModel{" +
                "personName='" + personName + '\'' +
                ", value=" + value +
                ", account='" + account + '\'' +
                ", isPartener=" + isPartener +
                '}';
    }
}
