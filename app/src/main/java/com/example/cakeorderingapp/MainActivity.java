package com.example.cakeorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
//This is the final Project and this
// is an APP which you use for ordering a Cake with frosting.
// Also this have login feature and account creation as well.
public class MainActivity extends AppCompatActivity  {


    String cake_type = "";

    //elements
    Button btnLay;
    Button btnChif;
    Button btnRed;
    Button btnMCarr;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate element objects
        btnLay = findViewById(R.id.button_lay);
        btnChif = findViewById(R.id.button_chif);
        btnRed = findViewById(R.id.button_red);
        btnMCarr = findViewById(R.id.button_car);

        btnLay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cake_type = "Layer Cake";
                        Log.i("Button Pressed", cake_type);
                        Intent i = new Intent(MainActivity.this, FrostingActivity.class);
                        i.putExtra("Key_Cake", cake_type);
                        startActivity(i);
                    }
                }
        );
        btnChif.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cake_type = "Chiffon Cake";
                        Log.i("Button Pressed", cake_type);
                        Intent i = new Intent(MainActivity.this, FrostingActivity.class);
                        i.putExtra("Key_Cake", cake_type);
                        startActivity(i);
                    }
                }
        );
        btnRed.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cake_type = "Red Velvet Cake";
                        Log.i("Button Pressed", cake_type);
                        Intent i = new Intent(MainActivity.this, FrostingActivity.class);
                        i.putExtra("Key_Cake", cake_type);
                        startActivity(i);
                    }
                }
        );
        btnMCarr.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cake_type = "Carrot Cake";
                        Log.i("Button Pressed", cake_type);
                        Intent i = new Intent(MainActivity.this, FrostingActivity.class);
                        i.putExtra("Key_Cake", cake_type);
                        startActivity(i);
                    }
                }
        );
    }
}