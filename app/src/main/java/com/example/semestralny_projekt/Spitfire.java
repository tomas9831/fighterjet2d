package com.example.semestralny_projekt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;


public class Spitfire {

    private final Bitmap image;
    private Rect rectangle;
    private MediaPlayer mediaPlayer;
    private Point playerPoint;
    BitmapFactory bf;
    private int chosenSkin;

    public Spitfire(Point playerPoint) {
        this.chosenSkin = Constants.preferences.getInt("skin",1);
        if (chosenSkin==1){
            this.image = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.spitfire);
        }
        else {
            this.image = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.messerschmitt);
        }

        this.rectangle = new Rect(0, 0, 300, 300);
        this.playerPoint = playerPoint;
        this.mediaPlayer = MediaPlayer.create(Constants.CURRENT_CONTEXT,R.raw.aircraft);
    }


    protected void draw(Canvas canvas) {
        canvas.drawBitmap(image, null, rectangle, null);
    }

    public void update(Point point) {
        this.mediaPlayer.start();
        //ltrb
        rectangle.set(point.x - rectangle.width() / 2,
                point.y - rectangle.height() / 2,
                point.x + rectangle.width() / 2,
                point.y + rectangle.height() / 2);
    }
    public void moveLeft(){
        this.playerPoint.x += 20;
    }
    public void moveRight(){
        this.playerPoint.x -= 20;
    }
}