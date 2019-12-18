package com.example.semestralny_projekt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private String levelData;
    private MainThread thread;
    private OrientationData orientationData;
    private Spitfire spitfire;
    private ArrayList<Enemy> blimps = new ArrayList<>();
    private Point playerPoint;
    private BackgroundManager backgroundManager;
    private final Bitmap spitSprite;
    private ArrayList<Bullet> gun = new ArrayList<>();
    int blimpCount = 0;
    int score = 0;

    public GamePanel(Context context, String levelData) {
        super(context);
        getHolder().addCallback(this);
        Constants.CURRENT_CONTEXT = context;
        this.levelData = levelData;
        backgroundManager = new BackgroundManager(levelData);
        selectLevel(levelData);
        thread = new MainThread(getHolder(), this);
        this.spitSprite = BitmapFactory.decodeResource(getResources(), R.drawable.spitfire);
        spitfire = new Spitfire(spitSprite);


        playerPoint = new Point(150, 150);
        setFocusable(true);

        gun.add(new Bullet(playerPoint, 50));//init
        blimps.add(new Enemy(20));//init
        orientationData = new OrientationData();
        orientationData.register();


    }

    public void selectLevel(String level) {
        this.levelData = level;
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
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                gun.add(new Bullet(playerPoint, 50));
                //gun.get(gun.size() - 1).update();
            case MotionEvent.ACTION_MOVE:
                playerPoint.set((int) event.getX(), (int) event.getY());

        }
        return true;
    }

    public void update() {
        int elapsedTime = (int) (System.currentTimeMillis() - System.currentTimeMillis());
        if (orientationData.getOrientation() != null && orientationData.getStartOrientation() != null) {
            float pitch = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1];
            float roll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];

            float xSpeed = 2 * roll * Constants.SCREEN_WIDTH / 10f;
            float ySpeed = pitch * Constants.SCREEN_HEIGHT / 10f;
            Log.d("orientationRollX", String.valueOf(xSpeed));
            Log.d("orientationRollY", String.valueOf(ySpeed));

            playerPoint.x += Math.abs(xSpeed * elapsedTime) > 5 ? xSpeed * elapsedTime : 0;
            //playerPoint.y -= Math.abs(ySpeed*elapsedTime) > 5 ? ySpeed*elapsedTime : 0;
            Log.d("orientation", String.valueOf(playerPoint.x));
        }


        for (Bullet bullet : gun) {
            bullet.update();

            if (bullet.getRectangle().top <= 100) {
                //bullet.getRectangle().set(playerPoint.x, playerPoint.y, playerPoint.x, playerPoint.y);
            }


        }
        while (blimpCount < 3) {
            blimps.add(new Enemy(20));
            blimpCount++;
        }


        for (Enemy enemy : blimps) {
            enemy.decrementX();
            if(enemy.getRectangle().right < 0){
                enemy.spawnBlimp();
            }
            for(int i = 0; i< gun.size();i++){
                Bullet bullet = gun.get(i);
                if(enemy.getRectangle().intersect(bullet.getRectangle())){
                    enemy.spawnBlimp();
                    score++;
                }
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

        for (Bullet bullet : gun) {
            bullet.draw(canvas);
        }
        for (Enemy enemy : blimps) {
            enemy.draw(canvas);
        }
    }
}
