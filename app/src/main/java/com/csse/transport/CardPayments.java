package com.csse.transport;

public class CardPayments {

    String name;
    String address;
    String number;
    String card;
    String expiry;
    String ccv;
    String id;

    public CardPayments(String name, String address, String number, String card, String expiry, String ccv) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.card = card;
        this.expiry = expiry;
        this.ccv = ccv;
    }

    public CardPayments(String name, String address, String number, String id) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.id = id;
    }

    public CardPayments() {
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getCard() {
        return card;
    }

    public String getExpiry() {
        return expiry;
    }

    public String getCcv() {
        return ccv;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
