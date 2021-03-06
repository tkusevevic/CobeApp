package com.tkusevic.CobeApp.data.database;

import com.tkusevic.CobeApp.data.model.Product;
import com.tkusevic.CobeApp.data.model.Bill;
import com.tkusevic.CobeApp.data.model.User;
import com.tkusevic.CobeApp.data.model.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkusevic on 12.01.2018..
 */

public class Data {

    private final List<User> users = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final List<Worker> workers = new ArrayList<>();
    private final List<Bill> bills = new ArrayList<>();

    public List<Bill> getBills() {
        return bills;
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUsers(List<User> data) {
        users.addAll(data);
    }

    public void removeUser(User user) {
        users.remove(user);
    }


    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    public void addWorkers(List<Worker> data) {
        workers.addAll(data);
    }

    public void removeWorker(Worker worker) {
        workers.remove(worker);
    }

    public List<Worker> getWorkers() {
        return workers;
    }


    public void addProduct(Product product) {
        products.add(product);
    }

    public void addProducts(List<Product> data) {
        products.addAll(data);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

}
