package com.example.semestralny_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button buttonSand, buttonCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonSand = findViewById(R.id.buttonSand);
        buttonSand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSand();
            }
        });
        buttonCity = findViewById(R.id.buttonCity);
        buttonCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCity();
            }
        });
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Constants.SCREEN_HEIGHT = height;
        Constants.SCREEN_WIDTH = width;
    }

    public void openSand(){
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("level","sand");
        startActivity(intent);
    }
    public void openCity(){
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("level","city");
        startActivity(intent);
    }
}
