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
        bmp = new Bitmap[5];

        bmp[0] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.palm);
        bmp[1] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.house);
        bmp[2] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.building1);
        bmp[3] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.building2);
        bmp[4] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.building3);

        rectangle = new Rect(left,top,right,bottom);
        switch (item){
            case "palm":
                this.image=bmp[0];
                break;
            case "house":
                this.image=bmp[1];
                break;
            case "building1":
                this.image=bmp[2];
                break;
            case "building2":
                this.image=bmp[3];
                break;
            case "building3":
                this.image=bmp[4];
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
