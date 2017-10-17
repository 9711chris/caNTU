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

    WaitingDabaoer(int id, String canteenName, String stallName, String deliveryTo, String status){
        this.id= id;
        this.canteenName = canteenName;
        this.stallName = stallName;
        this.deliveryTo = deliveryTo;
        this.status = status;
    }

    public int getId(){return id;}
    public String getCanteenName(){return canteenName;}
    public String getStallName(){return stallName;}
    public String getDeliveryTo(){return deliveryTo;}
    public String getStatus(){return status;}
}
