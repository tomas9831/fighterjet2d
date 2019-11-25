package com.example.semestralny_projekt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private Spitfire spitfire;
    private Point playerPoint;
    private BackgroundManager backgroundManager;
    private final Bitmap spitSprite;
    private ArrayList<Bullet> gun = new ArrayList<>();

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        Constants.CURRENT_CONTEXT = context;
        thread = new MainThread(getHolder(), this);
        this.spitSprite = BitmapFactory.decodeResource(getResources(),R.drawable.spitfire);
        spitfire = new Spitfire(spitSprite);

        playerPoint = new Point(150, 150);
        backgroundManager = new BackgroundManager();
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (true) {
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
        backgroundManager.update();
        spitfire.update(playerPoint);

        for(Bullet bullet: gun){
            bullet.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.YELLOW);
        backgroundManager.draw(canvas);
        spitfire.draw(canvas);

        for(Bullet bullet: gun){
            bullet.draw(canvas);
        }
    }
}
