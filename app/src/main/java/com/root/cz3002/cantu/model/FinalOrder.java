package com.root.cz3002.cantu.model;

/**
 * Created by brigi on 2/11/2017.
 */

public class FinalOrder {
    private String id;
    private String foodName;
    private long qty;
    private String canteenName;
    private String stallName;
    private String deliveryTo;
    private String status;
    private String collectStatus;
    private String user;
    private String timestamp;
    private double price;
    private double totalPrice;
    private String orderStatus;

    public FinalOrder(){}

    public FinalOrder(String id, String foodName, long qty, String canteenName, String stallName, String deliveryTo, String status,
                      String collectStatus, String user, String timestamp){
        this.id = id;
        this.foodName = foodName;
        this.qty = qty;
        this.canteenName = canteenName;
        this.stallName = stallName;
        this.deliveryTo = deliveryTo;
        this.status = status;
        this.collectStatus = collectStatus;
        this.user = user;
        this.timestamp = timestamp;
    }

    public String getId(){return id;}
    public String getFoodName(){return foodName;}
    public Long getQty(){return qty;}
    public String getCanteenName(){return canteenName;}
    public String getStallName(){return stallName;}
    public String getDeliveryTo(){return deliveryTo;}
    public String getStatus(){return status;}
    public String getCollectStatus(){return collectStatus;}
    public String getUser(){return user;}
    public String getTimestamp(){return timestamp;}
    public double getPrice(){return price;}
    public double getTotalPrice(){return totalPrice;}
    public String getOrderStatus(){return orderStatus;}

    public void setId(String id){this.id = id;}
    public void setFoodName(String foodName){this.foodName = foodName;}
    public void setQty(Long qty) {this.qty = qty;}
    public void setCanteenName(String canteenName){this.canteenName=canteenName;}
    public void setStallName(String stallName){this.stallName = stallName;}
    public void setDeliveryTo(String deliveryTo){this.deliveryTo = deliveryTo;}
    public void setStatus(String status){this.status = status;}
    public void setCollectStatus(String collectStatus){this.collectStatus = collectStatus;}
    public void setTimestamp(String timestamp){this.timestamp = timestamp;}
    public void setUser(String user){this.user = user;}
    public void setPrice(double price){this.price = price;}
    public void setTotalPrice(double totalPrice){this.totalPrice = totalPrice;}
    public void setOrderStatus(String orderStatus){this.orderStatus = orderStatus;}
}
