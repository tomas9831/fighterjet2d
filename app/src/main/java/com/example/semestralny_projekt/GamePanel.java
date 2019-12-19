package com.example.semestralny_projekt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Spitfire spitfire;
    private ArrayList<Enemy> blimps = new ArrayList<>();
    private Point playerPoint;
    private BackgroundManager backgroundManager;

    private Database db;

    private ArrayList<Bullet> gun = new ArrayList<>();
    int blimpCount = 0;
    int score = 0;
    int crossedBlips = 0;

    public GamePanel(Context context, String levelData) {
        super(context);
        getHolder().addCallback(this);
        Constants.CURRENT_CONTEXT = context;
        backgroundManager = new BackgroundManager(levelData);
        thread = new MainThread(getHolder(), this);
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - (Constants.SCREEN_HEIGHT / 5));
        spitfire = new Spitfire(playerPoint);
        setFocusable(true);
        gun.add(new Bullet(playerPoint, 50));//init
        blimps.add(new Enemy(20));//init
        db = new Database(context);
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
                gun.add(new Bullet(playerPoint, 80));
                //gunSound.makeSound();
                //gun.get(gun.size() - 1).update();
            case MotionEvent.ACTION_MOVE:
                playerPoint.set((int) event.getX(), (int) event.getY());

        }
        return true;
    }

    public void motionLeft() {
        this.spitfire.moveLeft();
    }

    public void motionRight() {
        this.spitfire.moveRight();
    }

    public void update() {

        for (Bullet bullet : gun) {
            bullet.update();
        }
        while (blimpCount < 3) {
            blimps.add(new Enemy(20));
            blimpCount++;
        }
        for (Enemy enemy : blimps) {
            enemy.decrementX();
            if (enemy.getRectangle().right < 0) {
                enemy.spawnBlimp();
                crossedBlips++;
            }
            for (int i = 0; i < gun.size(); i++) {
                Bullet bullet = gun.get(i);
                if (enemy.getRectangle().intersect(bullet.getRectangle())) {
                    enemy.spawnBlimp();
                    score++;
                }
            }

        }
        //END GAME
        if (crossedBlips > 3) {
            this.endGame();
        }

        backgroundManager.update();
        spitfire.update(playerPoint);

    }

    public void endGame() {
        this.db.addScore(score);
        this.thread.setRunning(false);
        Intent i = new Intent(Constants.CURRENT_CONTEXT, MenuActivity.class);
        Constants.CURRENT_CONTEXT.startActivity(i);

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
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(64);
        canvas.drawText("Score: " + String.valueOf(score), 0, 60, paint);
        canvas.drawText("Crossed : " + String.valueOf(crossedBlips), 0, 120, paint);

    }
}
