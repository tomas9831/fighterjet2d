package com.example.semestralny_projekt;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;

import java.util.Random;


public class BackgroundManager {
    ArrayList<Sprite>[] al;
    String[] itemArr;
    int[] limitVal;
    String levelType;
    String item1, item2;
    int sizeMultiplier,color, itemNo;

    int limit = 0;
    private long startTime;
    Random ran = new Random();

    public BackgroundManager(String levelType) {

        startTime = System.currentTimeMillis();
        this.levelType = levelType;
        chooseLevel(levelType);
        initList();
        populateObstacles();
    }

    private void chooseLevel(String levelType) {
        switch (levelType) {
            case "sand":
                this.item1 = "house";
                this.item2 = "palm";
                this.sizeMultiplier = 1;
                this.itemNo = 6;
                this.color = Color.YELLOW;
                break;
            case "city":
                this.item1 = "building1";
                this.item2 = "building2";
                this.sizeMultiplier = 3;
                this.itemNo = 20;
                this.color = Color.rgb(0,0,40);//midnight blue
                break;
        }

    }

    private void initList() {
        Constants.ITEM_SIZE = 2;
        al = new ArrayList[Constants.ITEM_SIZE];
        for (int i = 0; i < Constants.ITEM_SIZE; i++) {
            al[i] = new ArrayList<>();
        }
        itemArr = new String[Constants.ITEM_SIZE];
        limitVal = new int[Constants.ITEM_SIZE];
        itemArr[0] = this.item1;
        itemArr[1] = this.item2;
        limitVal[0] = 3;
        limitVal[1] = 10;
    }

    private void populateObstacles() {

        al[0].add(new Sprite(0, 0, 0, 0, this.item1));
        al[1].add(new Sprite(0, 0, 0, 0, this.item2));

    }

    public void update() {

        generate(al, itemNo, itemArr, this.sizeMultiplier);

    }

    public void draw(Canvas canvas) {

        canvas.drawColor(this.color);

        for (Sprite i1 : al[0]) {
            i1.draw(canvas);
        }

        for (Sprite i2 : al[1]) {
            i2.draw(canvas);
        }
    }

    public void generate(ArrayList<Sprite>[] arrList, int limitVal, String[] item, int size) {

        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT / 5000.0f;
        int index = 0;
        for (ArrayList<Sprite> arr : arrList) {
            for (int i = 0; i < arr.size(); i++) {
                arr.get(i).incrementY(speed * elapsedTime);
            }
            for (Sprite s : arr) {
                s.incrementY(speed * elapsedTime);
            }
            if (arr.get(arr.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT / 2) {
                int xStart = ran.nextInt(Constants.SCREEN_WIDTH - (size *100));
                int yRand = ran.nextInt(500);
                if (limit < limitVal) {
                    arr.add(0, new Sprite(xStart, -3 * size * yRand + 150 * size, xStart + 150 * size, -3 * size * yRand + 300 * size, item[index]));
                    limit++;
                }

                if (arr.get(arr.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT + 50) {
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