package com.root.cz3002.cantu.model;

/**
 * Created by brigi on 12/10/2017.
 */

public class CreditItem {
    private String userName;
    private double credit;

    public CreditItem(double credit){
        this.credit = credit;
    }

    public double getCredit(){
        return credit;
    }
}
