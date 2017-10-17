package com.root.cz3002.cantu.model;

/**
 * Created by brigi on 12/10/2017.
 */

public class WaitingDabaoer {
    private int id;
    private String canteenName;
    private String stallName;
    private String deliveryTo;
    private String status;
    private String timestamp;
    private String foodName;

    public WaitingDabaoer(int id, String foodName, String canteenName, String stallName, String deliveryTo, String status,String timestamp){
        this.id= id;
        this.foodName = foodName;
        this.canteenName = canteenName;
        this.stallName = stallName;
        this.deliveryTo = deliveryTo;
        this.status = status;
        this.timestamp = timestamp;
    }

    public int getId(){return id;}
    public String getFoodName(){return foodName;}
    public String getCanteenName(){return canteenName;}
    public String getStallName(){return stallName;}
    public String getDeliveryTo(){return deliveryTo;}
    public String getStatus(){return status;}
    public java.lang.String getTimestamp() {return timestamp;}
}
