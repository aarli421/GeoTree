package com.example.geotree.Leaderboard;

import android.widget.TextView;

import com.example.geotree.R;
import com.example.geotree.User.User;

import java.util.ArrayList;
import java.util.Arrays;
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


        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                System.out.println(u1.getPlanted().size() - u2.getPlanted().size());
                return u1.getPlanted().size() - u2.getPlanted().size();

            }
        });

        final Leaderboard obj = new Leaderboard();
        final ArrayList<User> users = obj.getUsers();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(int j = 0; j < 1; j++){
                    mDisplayInfo.setText("asdf");
                }
            }
        });

    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User u) {
        users.add(u);
    }
}
