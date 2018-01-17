package com.tkusevic.CobeApp.data.model;

public class Worker extends Person {

    private double salary;
    private String type;
    private String lastName;

    public Worker(int id, double salary, String name, String lastName, String type,String email,String password) {
        super(id, name,email,password);
        this.salary = salary;
        this.type = type;
        this.lastName=lastName;
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
