package com.tkusevic.CobeApp.data.model;


public class Product {

    private String name;
    private int price;
    private int stockStatus;
    private int id;
    private int image;



    public Product(int id, String name, int price, int stockStatus,int image) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.stockStatus = stockStatus;
        this.image = image;
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

    public int getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(int stockStatus) {
        this.stockStatus = stockStatus;
    }

    @Override
    public String toString() {
        return ( getName() + " = " + getPrice() + " HRK");
    }
}
