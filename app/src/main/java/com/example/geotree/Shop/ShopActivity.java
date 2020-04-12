package com.example.geotree.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geotree.Map.MapActivity;
import com.example.geotree.R;

public class ShopActivity extends AppCompatActivity {
    private Button ten;
    private Button twenty;
    private Button fifty;
    private Button hundred;
    private Button goBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        goBack = findViewById(R.id.back_to_map);
        ten = findViewById(R.id.buy_credit10);
        twenty = findViewById(R.id.buy_credit20);
        fifty = findViewById(R.id.buy_credit50);
        hundred = findViewById(R.id.buy_credit100);




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

    }
}
