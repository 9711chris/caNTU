package com.root.cz3002.cantu.model;

import com.root.cz3002.cantu.ToReceiveAdapter;

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

    public ToReceiveData(){}

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

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
