package com.example.geotree.User;

import com.example.geotree.Map.Tree;

import java.util.ArrayList;

public class User {
    private String name, email, password;
    private ArrayList<Tree> planted;
    private static ArrayList<Tree> toPlant, totalPlanted;

    public User() {

    }

    public User(String name, String email, String password) {

    }

    public void addPlanted(Tree t) {
        planted.add(t);
        totalPlanted.add(t);
    }

    public static void addToPlant() {

    }

    public String getName() {
        return name;
    }

    public ArrayList<Tree> getPlanted() {
        return planted;
    }
}
