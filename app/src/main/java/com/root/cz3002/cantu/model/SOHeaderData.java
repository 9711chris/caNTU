package com.root.cz3002.cantu.model;

/**
 * Created by Christantia on 10/20/2017.
 */

public class SOHeaderData {
    private String orderSeq;
    private double totalPrice;

    public SOHeaderData(String orderSeq, double totalPrice){
        this.orderSeq = orderSeq;
        this.totalPrice = totalPrice;
    }

    public SOHeaderData(){}

    public void setOrderSeq(String orderSeq){ this.orderSeq = orderSeq; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public String getOrderSeq(){
        return orderSeq;
    }

    public double getTotalPrice(){
        return totalPrice;
    }
}
