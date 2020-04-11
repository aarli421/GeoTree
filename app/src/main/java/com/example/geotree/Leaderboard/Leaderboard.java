package com.example.geotree.Leaderboard;

import com.example.geotree.User.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard extends LeaderboardActivity{
    private ArrayList<User> users;
    private static Leaderboard l;

    protected Leaderboard() {
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
        users.add(u);
    }
}
