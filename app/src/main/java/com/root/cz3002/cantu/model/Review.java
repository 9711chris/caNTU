package com.root.cz3002.cantu.model;

import java.sql.Time;
import java.util.Date;

/**
 * Created by shelinalusandro on 10/10/17.
 */

public class Review {

    private int id;
    private String stallName;
    private String userName;
    private String comment;
    private String dateTime;
    private float rating;

    public Review (int id, String stallName, String userName, String comment, String dateTime, float rating){
        this.id = id;
        this.stallName = stallName;
        this.userName = userName;
        this.comment = comment;
        this.dateTime = dateTime;
        this.rating = rating;
    }

    public int getId(){
        return id;
    }
    public String getStallName(){
        return stallName;
    }
    public String getUserName(){
        return userName;
    }
    public String getComment(){
        return comment;
    }
    public String getDateTime(){
        return dateTime;
    }
    public float getRating(){
        return rating;
    }

    public void setId(int id){ this.id = id;}
    public void setStallName(String stallName){ this.stallName = stallName;}
    public void setUserName(String userName){ this.userName = userName; }
    public void setComment(String comment){ this.comment = comment; }
    public void setDateTime(String dateTime){ this.dateTime = dateTime; }
    public void setRating(float rating){ this.rating = rating; }

}
