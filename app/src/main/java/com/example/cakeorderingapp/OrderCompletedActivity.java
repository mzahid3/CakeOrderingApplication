package com.example.cakeorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderCompletedActivity extends AppCompatActivity {

    //elements
    String cake_type = "";

    TextView readyTime;
    private Button maps_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_completed);

        readyTime = findViewById(R.id.textView_time);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cake_type = extras.getString("Key_Cake");

            Log.i("Confirm_Cake", cake_type);

        }

        // if/else to show estimated time
        if (cake_type.equals("Layer Cake")) {
            readyTime.setText("30 min");
        }
        else if (cake_type.equals("Chiffon Cake")) {
            readyTime.setText("30 min");
        }
        else if (cake_type.equals("Red Velvet Cake")) {
            readyTime.setText("25 min");
        }
        else if (cake_type.equals("Carrot Cake")) {
            readyTime.setText("20 min");
        }

        maps_btn = findViewById(R.id.maps_btn);
        maps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderCompletedActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }
}