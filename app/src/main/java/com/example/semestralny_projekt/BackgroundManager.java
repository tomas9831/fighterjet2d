package com.example.semestralny_projekt;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import java.util.Random;


public class BackgroundManager {
    private ArrayList<Sprite> houseS;
    private ArrayList<Sprite> palmS;
    ArrayList<Sprite>[] al;
    String[] itemArr;
    int[] limitVal;

    private int obstacleGap, obstacleHeight;
    int palmLimit = 0;
    int limit = 0;
    private long startTime;
    Random ran = new Random();

    public BackgroundManager(int obstacleGap, int obstacleHeight) {
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;

        startTime = System.currentTimeMillis();

        /*houseS = new ArrayList<>();
        palmS = new ArrayList<>();*/
        initList();
        populateObstacles();
    }
    private void initList(){
        Constants.ITEM_SIZE = 2;
        al = new ArrayList[ Constants.ITEM_SIZE];
        for (int i = 0; i <  Constants.ITEM_SIZE; i++) {
            al[i] = new ArrayList<>();
        }
        itemArr = new String[ Constants.ITEM_SIZE];
        limitVal = new int[ Constants.ITEM_SIZE];
        itemArr[0] = "house";
        itemArr[1] = "palm";
        limitVal[0] = 3;
        limitVal[1] = 10;
    }
    private void populateObstacles() {

        al[0].add(new Sprite(0, 0, 0, 0, "house"));
        al[1].add(new Sprite(0, 0, 0, 0, "palm"));

        /*int currY = -5 * Constants.SCREEN_HEIGHT / 4;
        while (currY < 0) {
            Log.d("currY", String.valueOf(currY));
            Log.d("abcs", "podmienka");
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - obstacleGap));
            palmS.add(new Sprite(obstacleHeight, xStart, currY, obstacleGap,"palm"));
            currY += obstacleHeight + obstacleGap;
        }*/
    }

    public void update() {

         generate(al, 10, itemArr);

    }

    public void draw(Canvas canvas) {

        for (Sprite palm : al[0]) {
            palm.draw(canvas);
        }

        for (Sprite h : al[1]) {
            h.draw(canvas);
        }
    }

    public void generate(ArrayList<Sprite>[] arrList, int limitVal, String[] item) {

        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT / 4000.0f;
        int index = 0;
        for(ArrayList<Sprite> arr: arrList){
            for (int i = 0; i < arr.size(); i++) {
                arr.get(i).incrementY(speed * elapsedTime);
            }
            for (Sprite s : arr) {
                s.incrementY(speed * elapsedTime);
            }
            if (arr.get(arr.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT / 2) {
                int xStart = ran.nextInt(1080);
                int yRand = ran.nextInt(500);
                if (limit < limitVal) {
                    arr.add(0, new Sprite(xStart, yRand + 150, xStart + 150, yRand + 300, item[index]));
                    limit++;
                }

                if (arr.get(arr.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
                    arr.remove(arr.size() - 1);
                    if (limit > 0) {
                        limit -= 1;
                    }
                }
            }
            index++;
        }

    }
}
