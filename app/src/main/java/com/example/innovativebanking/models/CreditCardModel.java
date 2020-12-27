package com.example.innovativebanking.models;

public class CreditCardModel {

    private long cardNumber;
    private String cardPersonName;
    private int cardCVV;
    private String cardExpiryDate;

    public CreditCardModel(long cardNumber, String cardPersonName, int cardCVV, String cardExpiryDate) {
        this.cardNumber = cardNumber;
        this.cardPersonName = cardPersonName;
        this.cardCVV = cardCVV;
        this.cardExpiryDate = cardExpiryDate;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardPersonName() {
        return cardPersonName;
    }

    public void setCardPersonName(String cardPersonName) {
        this.cardPersonName = cardPersonName;
    }

    public int getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(int cardCVV) {
        this.cardCVV = cardCVV;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    @Override
    public String toString() {
        return "CreditCardModel{" +
                "cardNumber=" + cardNumber +
                ", cardPersonName='" + cardPersonName + '\'' +
                ", cardCVV=" + cardCVV +
                ", cardExpiryDate='" + cardExpiryDate + '\'' +
                '}';
    }
}
