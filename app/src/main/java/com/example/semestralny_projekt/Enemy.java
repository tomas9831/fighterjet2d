package com.example.semestralny_projekt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

public class Enemy {

    private  Bitmap image;
    private Rect rectangle;
    BitmapFactory bf;

    public Enemy(Bitmap image) {
        this.image = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.blimp);
        this.rectangle = new Rect(0, 0, 300, 300);
    }


    protected void draw(Canvas canvas) {
        canvas.drawBitmap(image, null, rectangle, null);
    }

    public void decrementX(float x) {
        rectangle.top -= x;
        rectangle.bottom -= x;
    }

    public Rect getRectangle(){
        return rectangle;
    }

}
