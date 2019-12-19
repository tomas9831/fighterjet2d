package com.example.semestralny_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final ImageButton skin1 = (ImageButton) findViewById(R.id.imageButton);
        final ImageButton skin2 = (ImageButton) findViewById(R.id.imageButton2);

        skin1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setAircraft(1);
            }
        });

        skin2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setAircraft(2);
            }
        });

    }
    public void setAircraft(int arg){
        SharedPreferences.Editor editor = Constants.preferences.edit();
        editor.putInt("skin",arg);
        editor.apply();

        if(arg == 1){
            Toast.makeText(this,"You've chosen Spitfire", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"You've chosen Messerschmitt", Toast.LENGTH_SHORT).show();
        }
    }
}
