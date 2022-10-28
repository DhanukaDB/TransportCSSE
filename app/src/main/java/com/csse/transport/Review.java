package com.csse.transport;

public class Review {

    String rate;
    String email;
    String other;

    public Review(String rate, String email, String other) {
        this.rate = rate;
        this.email = email;
        this.other = other;
    }

    public Review() {
    }

    public String getRate() {
        return rate;
    }

    public String getEmail() {
        return email;
    }

    public String getOther() {
        return other;
    }
}
