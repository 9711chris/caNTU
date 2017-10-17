package com.root.cz3002.cantu.model;

/**
 * Created by christantia on 8/10/17.
 */

public class Order {
    private int id;
    private String menuName;
    private int quantity;
    private double priceOne;
    private double totalPrice;

    public Order(int id, String menuName, int quantity, double priceOne, double totalPrice){
        this.id = id;
        this.menuName = menuName;
        this.quantity = quantity;
        this.priceOne = priceOne;
        this.totalPrice = totalPrice;
    }

    public int getId(){
        return id;
    }
    public String getMenuName(){
        return menuName;
    }
    public int getQuantity(){
        return quantity;
    }
    public double getPriceOne(){
        return priceOne;
    }
    public double getTotalPrice(){
        return totalPrice;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setMenuName(String menuName){
        this.menuName = menuName;
    }

    public void setpriceOne(double priceOne){
        this.priceOne = priceOne;
    }

    public void setTotalPrice(double totalPrice){
        this.totalPrice = totalPrice;
    }
}
