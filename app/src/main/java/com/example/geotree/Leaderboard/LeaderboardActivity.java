package com.example.geotree.Leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.MotionEvent;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;
import com.example.geotree.Map.MapActivity;
import com.example.geotree.R;
import com.example.geotree.User.RequestTreeActivity;
import com.example.geotree.User.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardActivity extends AppCompatActivity {
    private static TextView mDisplayName, plantedTrees;
    private static Button mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        mDisplayName = findViewById(R.id.leaderb);
        plantedTrees = findViewById(R.id.treesPlanted);
        mBack = findViewById(R.id.back);
        //Leaderboard.getInstance().addUser(new User("Larry Zhi", "larryzhi@gmail.com", "larryzhi"));
        Leaderboard.getInstance().getTop(5);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

    public static TextView getInfo(){
        return mDisplayName;
    }
    public static TextView getTrees(){
        return plantedTrees;
    }
}
