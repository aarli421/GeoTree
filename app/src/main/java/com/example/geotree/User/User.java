package com.example.geotree.User;

import com.example.geotree.Map.Tree;

import java.util.ArrayList;

public class User {
    private String name, email, password;
    private ArrayList<Tree> planted;
    private static ArrayList<Tree> toPlant, totalPlanted;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void addPlanted(Tree t) {
        planted.add(t);
        totalPlanted.add(t);
    }

    public static void addToPlant(Tree t) {
        toPlant.add(t);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Tree> getPlanted() {
        return planted;
    }

    public boolean equals(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
