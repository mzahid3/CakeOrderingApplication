package com.example.cakeorderingapp;
//This is the final Project and this
// is an APP which you use for ordering a Cake with frosting.
// Also this have login feature and account creation as well.
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FrostingActivity extends AppCompatActivity {

    String frosting = "";
    String cake_type = "";

    //elements
    Button btnLow;
    Button btnMedium;
    Button btnHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frosting_option);

        //instantiate element objects
        btnLow = findViewById(R.id.button_butter);
        btnMedium = findViewById(R.id.button_whip);
        btnHigh = findViewById(R.id.button_glaze);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cake_type = extras.getString("Key_Cake");
        }
        Log.i("Frosting_Options_page__Cake_type", cake_type);


        btnLow.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        frosting = "ButterCream";
                        Log.i("Button Pressed", frosting);
                        Intent intent = new Intent(FrostingActivity.this, OrderCheckActivity.class);
                        intent.putExtra("Key_Cake",cake_type);
                        intent.putExtra("Key_Frosting",frosting);
                        startActivity(intent);
                    }
                }
        );
        btnMedium.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        frosting = "WhippedCream";
                        Log.i("Button Pressed", frosting);
                        Intent intent = new Intent(FrostingActivity.this, OrderCheckActivity.class);
                        intent.putExtra("Key_Cake",cake_type);
                        intent.putExtra("Key_Frosting",frosting);
                        startActivity(intent);
                    }
                }
        );
        btnHigh.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        frosting = "Glaze";
                        Log.i("Button Pressed", frosting);
                        Intent intent = new Intent(FrostingActivity.this, OrderCheckActivity.class);
                        intent.putExtra("Key_Cake",cake_type);
                        intent.putExtra("Key_Frosting",frosting);
                        startActivity(intent);
                    }
                }
        );
    }
}