package com.example.android.mytourguideapp;

/**
 * Created by Admin on 7/27/2017.
 */

public class Tours {
    String head;
    String description;
    String photoUrl;
    String address;
    String hours;
    String phone;



    public Tours(String head, String description, String photoUrl, String address, String hours, String phone) {

        this.head = head;
        this.description = description;
        this.photoUrl = photoUrl;
        this.address = address;
        this.hours = hours;
        this.phone = phone;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }




    public String getHead() {
        return head;
    }

    public String getAddress() {
        return address;
    }

    public String getHours() {
        return hours;
    }

    public String getPhone() {
        return phone;
    }

    public Tours() {
    }

    public String getDescription() {
        return description;
    }

}
