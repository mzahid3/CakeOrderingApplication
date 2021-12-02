package com.example.cakeorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderCheckActivity extends AppCompatActivity {

    //elements
    TextView viewfrosting;
    TextView viewCake;
    Button btnYes;
    Button btnNo;
    String cake_type = "";
    String frosting = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_check);

        //instantiate element objects
        viewCake = findViewById(R.id.textView_cake);
        viewfrosting = findViewById(R.id.textView_frostview);
        btnYes = findViewById(R.id.button_yes);
        btnNo = findViewById(R.id.button_no);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cake_type = extras.getString("Key_Cake");
            frosting = extras.getString("Key_Frosting");

            Log.i("Reivew_Cake", cake_type);
            viewCake.setText(cake_type);
            Log.i("Review_Frosting", frosting);
            viewfrosting.setText(frosting);
        }

        btnYes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("Button Yes", "Pressed");
                        Intent i = new Intent(OrderCheckActivity.this, OrderCompletedActivity.class);
                        i.putExtra("Key_Cake", cake_type);
                        startActivity(i);
                    }
                }
        );
        btnNo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("Button No", "Pressed");
                        Intent i = new Intent(OrderCheckActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                }
        );
    }
}