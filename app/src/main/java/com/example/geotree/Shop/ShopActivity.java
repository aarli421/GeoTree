package com.example.geotree.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geotree.Map.MapActivity;
import com.example.geotree.R;

import static com.example.geotree.Map.MapActivity.user;

public class ShopActivity extends AppCompatActivity {
    private Button ten, twenty, fifty, hundred, goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        goBack = findViewById(R.id.back_to_map);
        ten = findViewById(R.id.buy_credit100);
        twenty = findViewById(R.id.buy_credit200);
        fifty = findViewById(R.id.buy_credit500);
        hundred = findViewById(R.id.buy_credit1000);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return;
            }
        });

        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.buy(100) ;
            }
        });

        twenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.buy(200) ;
            }
        });


        fifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.buy(500) ;
            }
        });


        hundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.buy(1000) ;
            }
        });
    }
}