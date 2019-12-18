package com.example.semestralny_projekt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Aircraft.db";
    public static final String TABLE_NAME = "high_score";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SCORE";

    public Database(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
