package com.example.geotree.Leaderboard;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geotree.R;

public class LeaderboardActivity extends AppCompatActivity {
    private TextView mDisplayInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        mDisplayInfo = (TextView) findViewById(R.id.textView2);
        


    }
}
