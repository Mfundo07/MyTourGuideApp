package com.example.android.mytourguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button viewData;
    private  Button tourGuide;
    private Button eatData;
    private Button drinkData;
    private Button stayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewData = (Button) findViewById(R.id.view_data);
        tourGuide = (Button) findViewById(R.id.tour_guide);
        eatData = (Button) findViewById(R.id.eat_data);
        drinkData = (Button) findViewById(R.id.drink_data);
        stayData = (Button) findViewById(R.id.stay_data);
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewsTourDataActivity.class);
                startActivity(intent);
            }
        });

        tourGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TourGuideActivity.class);
                startActivity(intent);
            }
        });

        eatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EatTourDataActivity.class);
                startActivity(intent);
            }
        });

        drinkData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this,DrinkTourDataActivity.class);
                startActivity(intent);
            }
        });
        stayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,StayTourDataActivity.class);
                startActivity(intent);
            }
        });


    }
}
