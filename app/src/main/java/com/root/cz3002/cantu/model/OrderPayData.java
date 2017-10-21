package com.root.cz3002.cantu.model;

/**
 * Created by brigi on 14/10/2017.
 */

public class OrderPayData {
    private boolean isChecked;
    private String id;
    private double price;
    private String foodName;
    private String stallName;
    private String canteenName;
    private String username;
    private int qty;
    private double totalPrice;
    private String deliverTo; // asked when

    public OrderPayData(boolean isChecked, String id, String username, double price, String foodName, String stallName, String canteenName,  int qty){
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
    public int getQty(){return qty;}
    public double getTotalPrice(){return totalPrice;}
    public String getDeliverTo(){return deliverTo;}

    public void setDeliverTo(String deliverTo){
        this.deliverTo = deliverTo;
    }

    public void computeTotalPrice(){
        totalPrice = price * qty;
    }
}
