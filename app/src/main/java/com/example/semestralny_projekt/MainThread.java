package com.example.semestralny_projekt;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    private double AVERAGE_FPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public void setRunning(boolean running){
        this.running=running;
    }

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        long startTime;
        long timeMilis = 1000 / MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000 / MAX_FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized ( surfaceHolder ){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                timeMilis = (System.nanoTime() - startTime)/1000000;
                waitTime = targetTime - timeMilis;
                try{
                    if(waitTime>0){
                        this.sleep(waitTime);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                totalTime +=System.nanoTime() - startTime;
                frameCount++;
                if(frameCount == MAX_FPS ){
                    AVERAGE_FPS = 1000/((totalTime/frameCount)/1000000);
                    frameCount = 0;
                    totalTime = 0;
                }
            }
        }
    }

}
