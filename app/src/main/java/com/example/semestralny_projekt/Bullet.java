package com.example.semestralny_projekt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class Bullet {
    private Rect rectangle;
    private int speed;
    private Bitmap image;
    BitmapFactory bf;

    public Bullet(Point point, int speed) {
        this.speed = speed;
        this.image =  bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.shell);
        rectangle = new Rect(0,0,00,0);
        rectangle.set(point.x - 10,
                point.y - 10,
                point.x +20,
                point.y +20);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,null,rectangle,null);
    }


    public void update() {
        rectangle.top -= speed;
        rectangle.bottom -= speed;
    }

    public Rect getRectangle() {
        return this.rectangle;
    }
}
