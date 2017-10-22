package com.root.cz3002.cantu.model;

import java.sql.Time;
import java.util.Date;

/**
 * Created by shelinalusandro on 10/10/17.
 */

public class Review {

    private String id;
    private String stallName;
    private String userName;
    private String comment;
    private String dateTime;
    private double rating;

    public Review()
    {}
    public Review (String id, String stallName, String userName, String comment, String dateTime, double rating){
        this.id = id;
        this.stallName = stallName;
        this.userName = userName;
        this.comment = comment;
        this.dateTime = dateTime;
        this.rating = rating;
    }

    public String getId(){
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
    public double getRating(){
        return rating;
    }

    public void setId(String id){ this.id = id;}
    public void setStallName(String stallName){ this.stallName = stallName;}
    public void setUserName(String userName){ this.userName = userName; }
    public void setComment(String comment){ this.comment = comment; }
    public void setDateTime(String dateTime){ this.dateTime = dateTime; }
    public void setRating(double rating){ this.rating = rating; }

}
