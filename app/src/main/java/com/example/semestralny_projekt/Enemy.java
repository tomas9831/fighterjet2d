package com.example.semestralny_projekt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Enemy {

    private Bitmap image;
    private Rect rectangle;
    BitmapFactory bf;
    int speed;
    int size = 200;
    Random ran = new Random();

    public Enemy(int speed) {
        this.speed = speed;
        this.image = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blimp);
        rectangle = new Rect(0, 0, 0, 0);
        spawnBlimp();

    }

    public void spawnBlimp() {
        int yStart = ran.nextInt(Constants.SCREEN_HEIGHT / 3);
        int xStart = Constants.SCREEN_WIDTH + (int) (Math.random() * ((Constants.SCREEN_WIDTH * 4 - Constants.SCREEN_WIDTH) + 1));
        rectangle.set(xStart - size,
                yStart - size,
                xStart + size,
                yStart + size);

    }

    protected void draw(Canvas canvas) {
        canvas.drawBitmap(image, null, rectangle, null);
    }

    public void decrementX() {
        rectangle.left -= speed;
        rectangle.right -= speed;
        Log.d("blimp", String.valueOf(rectangle.left));
    }

    public void update() {
        //decrementX();

    }

    public Rect getRectangle() {
        return rectangle;
    }

}
