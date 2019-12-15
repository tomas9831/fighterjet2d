package com.example.semestralny_projekt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private GameActivity gameActivity;
    private String levelData;
    private MainThread thread;
    private OrientationData orientationData;
    private Spitfire spitfire;
    private Point playerPoint;
    private BackgroundManager backgroundManager;
    private final Bitmap spitSprite;
    private ArrayList<Bullet> gun = new ArrayList<>();

    public GamePanel(Context context, String levelData) {
        super(context);
        getHolder().addCallback(this);
        Constants.CURRENT_CONTEXT = context;
        this.levelData = levelData;
        backgroundManager = new BackgroundManager(levelData);
        selectLevel(levelData);
        thread = new MainThread(getHolder(), this);
        this.spitSprite = BitmapFactory.decodeResource(getResources(),R.drawable.spitfire);
        spitfire = new Spitfire(spitSprite);

        playerPoint = new Point(150, 150);
        setFocusable(true);

        gun.add(new Bullet(playerPoint,50));//init
        orientationData = new OrientationData();
        orientationData.register();


    }

    public void selectLevel(String level){
        this.levelData=level;
        Log.d("levelData: ", levelData);
        //backgroundManager.setLevelType(levelData);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        Constants.INIT_TIME = System.currentTimeMillis();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                gun.add(new Bullet(playerPoint,50));
            case MotionEvent.ACTION_MOVE:
                playerPoint.set((int)event.getX(),(int)event.getY());

        }
        return true;
    }

    public void update() {
        int elapsedTime = (int)(System.currentTimeMillis() - System.currentTimeMillis());
        if(orientationData.getOrientation() != null && orientationData.getStartOrientation() != null) {
            float pitch = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1];
            float roll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];

            float xSpeed = 2 * roll * Constants.SCREEN_WIDTH/10f;
            float ySpeed = pitch * Constants.SCREEN_HEIGHT/10f;
            Log.d("orientationRollX", String.valueOf(xSpeed));
            Log.d("orientationRollY", String.valueOf(ySpeed));

            playerPoint.x += Math.abs(xSpeed*elapsedTime) > 5 ? xSpeed*elapsedTime : 0;
            //playerPoint.y -= Math.abs(ySpeed*elapsedTime) > 5 ? ySpeed*elapsedTime : 0;
            Log.d("orientation", String.valueOf(playerPoint.x));
        }


        for(Bullet bullet: gun){
            bullet.update();

            if(bullet.getRectangle().top<=100){
                gun.remove(bullet);
            }
        }

        backgroundManager.update();
        spitfire.update(playerPoint);


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        backgroundManager.draw(canvas);
        spitfire.draw(canvas);

        for(Bullet bullet: gun){
            bullet.draw(canvas);
        }
    }
}
