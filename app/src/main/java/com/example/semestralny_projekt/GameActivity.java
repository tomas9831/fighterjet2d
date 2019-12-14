package com.example.semestralny_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {

    private Context context;
    private String levelData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = getIntent();

        levelData = intent.getStringExtra("level");
        Log.d("activityData",levelData);
        //GamePanel gp = new GamePanel(getApplication().getBaseContext());
        setContentView(new GamePanel(this,levelData));

    }

}
