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
    protected TextView mDisplayInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        mDisplayInfo = (TextView) findViewById(R.id.leaderb);
        int i = 1;
        Leaderboard obj = new Leaderboard();
        User obj1 = new User("Larry Zhi" , "larryzhi@gmail.com", "random");
        obj.addUser(obj1);
        obj.getTop(i);


      // mDisplayInfo.setText("asfd");


    }


}
