package com.example.geotree.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geotree.Map.MapActivity;
import com.example.geotree.Map.Tree;
import com.example.geotree.Map.Tree.*;
import com.example.geotree.R;
import com.google.android.gms.maps.model.LatLng;
import com.example.geotree.Map.MapActivity;

import static com.example.geotree.Map.MapActivity.user;

public class PlantTreeActivity extends AppCompatActivity {

    private Button goBack;
    private Button submit;

    // Select a point and call plantTree()
    //my assignment (Yung$ushi)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_tree);
        goBack = findViewById(R.id.back_to_map);
        submit = findViewById(R.id.submit_request);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return;
            }
        });;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // plantTree(); //need to know what tree is at certain position.
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return;
            }
        });;


    }

    public void plantTree(Tree a) {
        a.setPlanter(user);
        a.setIsPlantedTrue();
    }
}
