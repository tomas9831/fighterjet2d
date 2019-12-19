package com.example.semestralny_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button buttonSand, buttonCity, buttonScore, buttonSettings;

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
        buttonScore = findViewById(R.id.buttonScore);
        buttonScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScore();
            }
        });
        buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Constants.SCREEN_HEIGHT = height;
        Constants.SCREEN_WIDTH = width;
        Constants.mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.gunshot);
        Constants.preferences = getSharedPreferences("save", 0);
    }

    public void openSand() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", "sand");
        startActivity(intent);
    }

    public void openCity() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level", "city");
        startActivity(intent);
    }

    public void openScore() {
        Intent intent = new Intent(this, HighScore.class);
        startActivity(intent);
    }
    public void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
