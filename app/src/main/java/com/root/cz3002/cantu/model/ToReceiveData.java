package com.root.cz3002.cantu.model;

/**
 * Created by brigi on 15/10/2017.
 */

public class ToReceiveData {
    private double price;
    private String foodName;
    private String stallName;
    private String canteenName;
    private int qty;
    private double totalPrice;
    private String status;

    public ToReceiveData(double price, String foodName, String stallName, String canteenName, int qty, double totalPrice, String status){
        this.price = price;
        this.foodName = foodName;
        this.stallName = stallName;
        this.canteenName = canteenName;
        this.qty = qty;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public double getPrice() {return price;}

    public double getTotalPrice() {return totalPrice;}

    public String getFoodName() {return foodName;}

    public String getCanteenName() {return canteenName;}

    public String getStallName() {return stallName;}

    public String getStatus() {return status;}

    public int getQty() {return qty;}
}
