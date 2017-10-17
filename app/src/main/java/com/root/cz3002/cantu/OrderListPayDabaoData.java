package com.root.cz3002.cantu;

/**
 * Created by brigi on 15/10/2017.
 */

public class OrderListPayDabaoData {
    private String foodName;
    private String stallName;
    private String canteenName;
    private String deliveryTo;
    private int qty;
    private double price;
    private double totalPrice;

    OrderListPayDabaoData(String foodName, String stallName, String canteenName, int qty, double price, double totalPrice){
        this.foodName = foodName;
        this.stallName = stallName;
        this.canteenName = canteenName;
        this.qty = qty;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public void setDeliveryTo(String deliveryTo){
        this.deliveryTo = deliveryTo;
    }
    public String getDeliveryTo(){return deliveryTo;}
    public String getFoodName(){return foodName;}
    public String getStallName(){return stallName;}
    public String getCanteenName(){return canteenName;}
    public int getQty(){return qty;}
    public double getPrice(){return price;}
    public double getTotalPrice(){return totalPrice;}

}


