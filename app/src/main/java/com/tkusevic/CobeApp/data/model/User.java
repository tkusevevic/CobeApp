package com.tkusevic.CobeApp.data.model;

import java.util.ArrayList;
import java.util.List;

public class User extends Person {

    List<Recipe> recipes = new ArrayList<>();

    public List<Recipe> getRecipes() {
        return recipes;
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

    public void addReciepe(Recipe reciepe){
        recipes.add(reciepe);
    }
}
