package com.root.cz3002.cantu.model;

/**
 * Created by brigi on 12/10/2017.
 */

public class WaitingDabaoer {
    private String id;
    private String canteenName;
    private String stallName;
    private String deliveryTo;
    private String status;
    private long qty;
    private String user;
    private String timestamp;
    private String foodName;

    public WaitingDabaoer()
    {}

    public WaitingDabaoer(String id, String foodName, String canteenName, String stallName, String deliveryTo, String status,String timestamp, String user){
        this.id= id;
        this.foodName = foodName;
        this.canteenName = canteenName;
        this.stallName = stallName;
        this.deliveryTo = deliveryTo;
        this.status = status;
        this.timestamp = timestamp;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public void setDeliveryTo(String deliveryTo) {
        this.deliveryTo = deliveryTo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getId(){return id;}
    public String getFoodName(){return foodName;}
    public String getCanteenName(){return canteenName;}
    public String getStallName(){return stallName;}
    public String getDeliveryTo(){return deliveryTo;}
    public String getStatus(){return status;}
    public java.lang.String getTimestamp() {return timestamp;}
}
