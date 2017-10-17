package com.root.cz3002.cantu.model;

import java.util.Map;

/**
 * Created by brigi on 10/10/2017.
 */

public class DabaoRequest {
    private int id;
    private String name;
    private String canteenName;
    private String stallName;
    private Map<String,Integer> food_qty;
    private String status;
    private String placeDeliver;

    public DabaoRequest(int id, String name, String canteenName, String stallName, Map<String,Integer> food_qty, String status,
                        String placeDeliver){
        this.id = id;
        this.name = name;
        this.canteenName = canteenName;
        this.stallName = stallName;
        this.food_qty = food_qty;
        this.status = status;
        this.placeDeliver = placeDeliver;
    }

    public int getId(){ return id;}
    public String getName() { return name;}
    public String getCanteenName(){ return canteenName;}
    public String getStallName(){ return stallName;}
    public Map<String,Integer> getFoodAndQty(){ return food_qty;}
    public String getStatus(){return status;}
    public String getPlaceDeliver(){return placeDeliver;}
}
