package com.tkusevic.CobeApp.data.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private List<Product> products = new ArrayList<>();
    private int sumPrice;
    private int userId;

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addDrinkToList(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return products.toString();
    }
}

