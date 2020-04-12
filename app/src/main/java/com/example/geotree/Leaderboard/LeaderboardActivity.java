package com.example.geotree.Leaderboard;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geotree.R;
import com.example.geotree.User.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardActivity extends AppCompatActivity {
    private static TextView mDisplayName;
    private static TextView plantedTrees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        mDisplayName = (TextView) findViewById(R.id.leaderb);
        plantedTrees = (TextView) findViewById(R.id.treesPlanted);
        Leaderboard.getInstance().addUser(new User("Larry Zhi", "larryzhi@gmail.com", "larryzhi"));
        Leaderboard.getInstance().getTop(1);
    }
    public static TextView getInfo(){
        return mDisplayName;
    }
    public static TextView getTrees(){
        return plantedTrees;
    }

}
