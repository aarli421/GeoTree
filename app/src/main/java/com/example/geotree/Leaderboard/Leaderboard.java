package com.example.geotree.Leaderboard;

import com.example.geotree.User.User;

import java.util.ArrayList;

public class Leaderboard {
    private ArrayList<User> users;
    private static Leaderboard l;

    private Leaderboard() {
        users = new ArrayList<>();
    }

    public static Leaderboard getInstance() {
        if (l == null) {
            return new Leaderboard();
        } else {
            return l;
        }
    }

    public void getTop(int i) {

    }

    public void addUser(User u) {

    }
}
