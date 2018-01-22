package com.tkusevic.CobeApp.data.model;

import java.io.Serializable;

public class Worker extends Person implements Serializable {

    private double salary;
    private String type;
    private String lastName;

    public Worker(int id, double salary, String name, String lastName, String type,String email,String password) {
        super(id, name,email,password);
        this.salary = salary;
        this.type = type;
        this.lastName=lastName;
    }


    public Worker(){

    }
    public double getSalary() {
        return salary;
    }

    public String getType() {
        return type;
    }

    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s - %s = %s HRK", getId(),getName(),lastName, type, String.valueOf(salary));
    }
}
