package com.example.annamiyajima.mapstest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by annamiyajima on 4/19/15.
 */
public class Restaurant {
    String myName;
    String myDescription;
    List<Rating> myRatings;
    int myRestaurantID;

    public Restaurant() {}

    public Restaurant(String name, String description) {
        myName = name;
        myDescription = description;
        myRatings = new ArrayList<Rating>();

        //hashing description for more guaranteed uniqueness in hash
        myRestaurantID = description.hashCode();
    }

    public String getDescription(){
        return myDescription;
    }

    public List<Rating> getRatings(){
        return myRatings;
    }

    public int getRestaurantID(){
        return myRestaurantID;

    }
}
