package com.root.cz3002.cantu.model;

/**
 * Created by Christantia on 10/20/2017.
 */

public class SOHeaderData {
    private String id;
    private String orderSeq;
    private double totalPrice;

    public SOHeaderData(String orderSeq, double totalPrice, String id){
        this.orderSeq = orderSeq;
        this.totalPrice = totalPrice;
        this.id = id;
    }

    public SOHeaderData(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderSeq(String orderSeq){ this.orderSeq = orderSeq; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public String getOrderSeq(){
        return orderSeq;
    }

    public double getTotalPrice(){
        return totalPrice;
    }
}
