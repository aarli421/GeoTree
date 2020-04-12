package com.example.geotree.User;

import com.example.geotree.Map.Tree;

import java.util.ArrayList;

public class User {
    private String name, email, password;
    private ArrayList<Tree> requested, planted;
    private static ArrayList<Tree> toPlant = new ArrayList<>(), totalPlanted = new ArrayList<>();
    private double balance;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

        requested = new ArrayList<>();
        planted = new ArrayList<>();
    }

    public void addPlanted(Tree t) {
        planted.add(t);
        totalPlanted.add(t);
        requested.remove(t);
        toPlant.remove(t);
    }

    public void addRequested(Tree t) {
        requested.add(t);
        toPlant.add(t);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Tree> getPlanted() {
        return planted;
    }

    public ArrayList<Tree> getRequested() {
        return requested;
    }

    public static ArrayList<Tree> getToPlant() {
        return toPlant;
    }

    public static ArrayList<Tree> getTotalPlanted() {
        return totalPlanted;
    }

    public double getBalance() {
        return balance;
    }

    public boolean equals(String email, String password) {
        if (this.email.equals(email)) {
            return true;
        } else {
            return false;
        }
    }
}
