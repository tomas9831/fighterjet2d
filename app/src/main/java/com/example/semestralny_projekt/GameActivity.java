package com.example.semestralny_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity implements SensorEventListener {

    private Context context;
    private String levelData;
    private GamePanel gp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = getIntent();

        levelData = intent.getStringExtra("level");
        Log.d("activityData",levelData);
        gp = new GamePanel(this,levelData);
        setContentView(gp);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0]; //X

        if(x > 0){
            gp.motionLeft();
        }
        if(x < 0){
            gp.motionRight();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
