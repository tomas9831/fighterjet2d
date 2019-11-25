package com.example.semestralny_projekt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Sprite {
    private Bitmap image;
    private Rect rectangle;
    Bitmap[] bmp;
    BitmapFactory bf;


    public Sprite(int left, int top, int right, int bottom, String item) {
        bmp = new Bitmap[2];

        bmp[0] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.palm);
        bmp[1] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.house);
        rectangle = new Rect(left,top,right,bottom);
        switch (item){
            case "palm":
                this.image=bmp[0];
                break;
            case "house":
                this.image=bmp[1];
                break;

        }


    }

    protected void draw(Canvas canvas) {
        canvas.drawBitmap(image, null, rectangle, null);
    }

    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;
    }

    public Rect getRectangle(){
        return rectangle;
    }
}
