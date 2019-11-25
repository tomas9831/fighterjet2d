package com.example.semestralny_projekt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;


public class Spitfire{

    private int x;
    private int y;
    private int speed;
    private final Bitmap image;
    private Rect rectangle;

    public Spitfire(Bitmap image) {
        this.image=image;
        this.rectangle = new Rect(0,0,300,300);
    }


    protected void draw(Canvas canvas) {
        canvas.drawBitmap(image,null,rectangle,null);
    }

    public void update(Point point) {
        //ltrb
        rectangle.set(point.x - rectangle.width() / 2,
                point.y - rectangle.height()/2,
                point.x + rectangle.width() / 2,
                point.y + rectangle.height() / 2);
    }
}