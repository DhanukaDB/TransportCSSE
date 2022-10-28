package com.csse.transport;

public class TimaTableDetails {

    String id;
    String busNo;
    String Startfrom;
    String goTo;
    String time;
    String driverName;

    public TimaTableDetails() {
    }

    public TimaTableDetails(String id, String busNo, String startfrom, String goTo, String time, String driverName) {
        this.id = id;
        this.busNo = busNo;
        Startfrom = startfrom;
        this.goTo = goTo;
        this.time = time;
        this.driverName = driverName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public String getStartfrom() {
        return Startfrom;
    }

    public void setStartfrom(String startfrom) {
        Startfrom = startfrom;
    }

    public String getGoTo() {
        return goTo;
    }

    public void setGoTo(String goTo) {
        this.goTo = goTo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
