package com.tkusevic.CobeApp.data.model;

import java.util.ArrayList;
import java.util.List;

public class User extends Person {

    List<Bill> bills = new ArrayList<>();

    public List<Bill> getBills() {
        return bills;
    }

    public User(int id, String email, String name, String password) {
        super(id, name, email,password);
    }

    public User(){
        super();
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s \n" ,getId(),getName(), getEmail());
    }

    public void addReciepe(Bill reciepe){
        bills.add(reciepe);
    }
}
