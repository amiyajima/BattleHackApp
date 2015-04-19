package com.example.annamiyajima.mapstest;

/**
 * Created by annamiyajima on 4/19/15.
 */
public class User {
    String myName;
    int myUserID;
    int myKarma;
    String myPhoneNumber;

    public User(){
        myName = "John Doe";
        myUserID = 00000;
        myKarma = 0;
        myPhoneNumber = "000-000-0000";
    }

    public User(String name, String phoneNumber){
        myName = name;
        myUserID = name.hashCode() + phoneNumber.hashCode();
        myKarma = 0;
        myPhoneNumber = phoneNumber;
    }

    public String getName(){
        return myName;
    }

    public int getID(){
        return myUserID;
    }

    public int getKarma(){
        return myKarma;
    }

    public String getPhoneNumber(){
        return myPhoneNumber;
    }

    public void setKarma(int karma){
        myKarma = karma;
    }
}
