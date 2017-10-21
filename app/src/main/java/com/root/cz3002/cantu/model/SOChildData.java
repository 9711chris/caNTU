package com.root.cz3002.cantu.model;

/**
 * Created by Christantia on 10/20/2017.
 */

public class SOChildData {
    private String foodName;
    private double qty;

    public SOChildData(String foodName, double qty){
        this.foodName = foodName;
        this.qty = qty;
    }
    public SOChildData(){

    }
    public void setFoodName(String foodName) {this.foodName = foodName;}
    public void setQty(double qty) {this.qty = qty;}
    public String getFoodName(){
        return foodName;
    }

    public double getQty(){
        return qty;
    }
}
