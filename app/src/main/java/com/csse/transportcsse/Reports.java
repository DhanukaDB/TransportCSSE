package com.csse.transportcsse;


public class Reports {

    private String from;
    private String to;
    private String userid;
    private String price;
    private String date;
    private String time;
    private String id;
    ;

    public Reports() {
    }

    public Reports(String from, String to, String userid, String date, String price,String time, String id) {
        this.from = from;
        this.to = to;
        this.userid = userid;
        this.price = price;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    //implement getters and setters
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}