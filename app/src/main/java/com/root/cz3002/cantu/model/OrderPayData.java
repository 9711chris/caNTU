package com.root.cz3002.cantu.model;

/**
 * Created by brigi on 14/10/2017.
 */

public class OrderPayData {
    private boolean isChecked;
    private boolean isPaid;
    private String orderId; // order sequence from child("dabao:)
    private String id;
    private double price;
    private String foodName;
    private String stallName;
    private String canteenName;
    private String username;
    private long qty;
    private double totalPrice;
    private String deliverTo; // asked when

    public OrderPayData(boolean isChecked, String id, String username, double price, String foodName, String stallName, String canteenName,  long qty){
        this.isChecked = isChecked;
        this.price = price;
        this.id = id;
        this.foodName = foodName;
        this.username = username;
        this.stallName = stallName;
        this.canteenName = canteenName;
        this.qty = qty;

        computeTotalPrice();
    }

    public OrderPayData()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIsChecked(){return isChecked;}
    public void setIsChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
    public double getPrice(){return price;}
    public String getFoodName(){return foodName;}
    public String getStallName(){return stallName;}
    public String getCanteenName(){return canteenName;}
    public long getQty(){return qty;}
    public double getTotalPrice(){return totalPrice;}

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliverTo(){return deliverTo;}

    public boolean getIsPaid(){return isPaid;}


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setDeliverTo(String deliverTo){
        this.deliverTo = deliverTo;
    }

    public void computeTotalPrice(){
        totalPrice = price * qty;
    }
}
