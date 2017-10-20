package com.root.cz3002.cantu.model;

/**
 * Created by shelinalusandro on 8/10/17.
 */

public class MenuItem {
    private long id;
    private String name;
    private String stall;
    private String canteen;

    public String getCanteen() {
        return canteen;
    }

    public void setCanteen(String canteen) {
        this.canteen = canteen;
    }

    private double price;

    public MenuItem (long id, String name, String stall, double price,String canteen){
        this.id = id;
        this.name = name;
        this.canteen=canteen;
        this.stall = stall;
        this.price = price;
    }

    public MenuItem()
    {

    }

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getStall(){
        return stall;
    }
    public double getPrice(){
        return price;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setStall(String stall){
        this.stall = stall;
    }

    public void setPrice(double price){
        this.price = price;
    }
}
