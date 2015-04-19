package com.example.annamiyajima.mapstest;

/**
 * Created by annamiyajima on 4/19/15.
 */
public class Rating {
    int myRestaurantID;
    int myWaitTime;
//    TimeStamp myTimeStamp;
    int myRaterID;

    public Rating(){}

    public Rating(int restID, int waitTime, int raterID){
        myRestaurantID = restID;
        myWaitTime = waitTime;
        myRaterID = raterID;
    }

    public int getWaitTime(){
        return myWaitTime;
    }

}
