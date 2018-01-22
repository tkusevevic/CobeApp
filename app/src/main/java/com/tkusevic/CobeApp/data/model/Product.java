package com.tkusevic.CobeApp.data.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Product implements Serializable {

    private String name;
    private int price;
    private int id;
    private int image;



    public Product(int id, String name, int price,int image) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.image = image;
    }
    public Product(){

    }

    public void setId(int id) {this.id = id;}

    public int getId() {
        return id;
    }

    public int getImage() {return image;}

    public void setImage(int image) {this.image = image;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ( getName() + " = " + getPrice() + " HRK");
    }


}
