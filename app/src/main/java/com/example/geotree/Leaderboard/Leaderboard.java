package com.example.geotree.Leaderboard;

import android.widget.TextView;

import com.example.geotree.R;
import com.example.geotree.User.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard{

    private ArrayList<User> users;
    private static Leaderboard l = null;

    private Leaderboard() {
        users = new ArrayList<>();
    }

    public static Leaderboard getInstance() {
        if (l == null) {
            l = new Leaderboard();
            return l;
        } else {
            return l;
        }
    }

    public void getTop(int i) {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                System.out.println(u1.getPlanted().size() - u2.getPlanted().size());
                return u2.getPlanted().size() - u1.getPlanted().size();
            }
        });
        String str = "";
        String trees = "";
        int index = 0;
        for (User u : users) {
            if (index >= i)break;
            index++;
            trees += u.getPlanted().size() + "\n";
            str += index + ". " + u.getName() + "\n";
        }
        System.out.println(users);
        LeaderboardActivity.getTrees().setText(trees);
        LeaderboardActivity.getInfo().setText(str);
    }
    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User u) {
        users.add(u);
    }
}
