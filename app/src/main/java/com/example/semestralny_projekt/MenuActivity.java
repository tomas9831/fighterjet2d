package com.example.semestralny_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    }

    public void openSand(){
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("level","sand");
        startActivity(intent);
    }
}
