package com.root.cz3002.cantu.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by brigi on 10/10/2017.
 */

public class DabaoRequest {
    private String id;
    private String name;
    private String canteenName;
    private String stallName;
    private ArrayList<DRChildData> food_qty;
    private String status;
    private String placeDeliver;
    private String key;
    private int childCount; //number of food

    public DabaoRequest()
    {}

    public DabaoRequest(String id, String name, String canteenName, String stallName, ArrayList<DRChildData> food_qty, String status,
                        String placeDeliver, String key, int childCount){
        this.id = id;
        this.name = name;
        this.canteenName = canteenName;
        this.stallName = stallName;
        this.food_qty = food_qty;
        this.status = status;
        this.placeDeliver = placeDeliver;
        this.key = key;
        this.childCount= childCount;

    }

    public String getId(){ return id;}
    public String getName() { return name;}
    public String getCanteenName(){ return canteenName;}
    public String getStallName(){ return stallName;}
    public ArrayList<DRChildData> getFood_qty() { return food_qty;}
    public String getStatus(){return status;}
    public String getPlaceDeliver(){return placeDeliver;}
    public String getKey(){return key;}
    public int getChildCount(){return childCount;}


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public void setFood_qty(ArrayList<DRChildData> food_qty){
        this.food_qty = food_qty;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPlaceDeliver(String placeDeliver) {
        this.placeDeliver = placeDeliver;
    }

    public void setKey(String key){this.key = key;}

    public void setChildCount(int childCount) {this.childCount = childCount;}
}
