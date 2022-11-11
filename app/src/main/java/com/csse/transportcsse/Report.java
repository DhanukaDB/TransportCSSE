package com.ads.lawplus;


public class Report {

    private String from;
    private String to;
    private String cusName;
    private String price;
    ;

    public Report() {
    }

    public Report(String from, String to, String cusName, String price) {
        this.from = from;
        this.to = to;
        this.cusName = cusName;
        this.price = price;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
