package com.csse.transport;


public class Bookdetails {

    private String from;
    private String to;
    private String date;
    private String time;
    private String category;
    private String email;
    private String qty;

    public Bookdetails() {
    }

    public Bookdetails(String from, String to, String date, String time, String category, String email, String qty) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.category = category;
        this.email = email;
        this.qty = qty;
    }

    //getters

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCategory() {
        return category;
    }

    public String getEmail() {
        return email;
    }

    public String getQty() {
        return qty;
    }


    //setters


    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
