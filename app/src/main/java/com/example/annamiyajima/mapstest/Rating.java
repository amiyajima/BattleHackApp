package com.example.annamiyajima.mapstest;

/**
 * Created by annamiyajima on 4/19/15.
 */
public class Rating {
    int myRestaurantID;
    int myWaitTime;
//    TimeStamp myTimeStamp;
    int myRaterID;
    int myPartySize;

    public Rating(){}

    public Rating(int restID, int waitTime, int raterID, int partySize){
        myRestaurantID = restID;
        myWaitTime = waitTime;
        myRaterID = raterID;
        myPartySize = partySize;
    }

    public int getWaitTime(){
        return myWaitTime;
    }

    public int getPartySize(){return myPartySize; }

}
