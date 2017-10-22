package com.root.cz3002.cantu.model;

/**
 * Created by brigi on 21/10/2017.
 */

public class DRChildData {
    private String foodName;
    private long qty;

    public DRChildData(String foodName, long qty){
        this.foodName = foodName;
        this.qty = qty;
    }

    public String getFoodName(){return foodName;}
    public long getQty(){return qty;}

    public void setFoodName(String foodName){
        this.foodName = foodName;
    }

    public void setQty(long qty){
        this.qty = qty;
    }
}
