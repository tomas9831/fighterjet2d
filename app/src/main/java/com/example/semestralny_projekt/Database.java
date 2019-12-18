package com.example.semestralny_projekt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Aircraft.db";
    public static final String TABLE_NAME = "high_score";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SCORE";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(NAME INTEGER PRIMARY KEY,SCORE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addScore(Integer score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, score);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else return true;

    }

    public Cursor getListContests() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.query(TABLE_NAME, null, null, null, null, null, COL_2 + " DESC", "5");
        return data;
    }

    public void reset() {
        SQLiteDatabase db = this.getWritableDatabase();

        String clearDBQuery = "DELETE FROM " + TABLE_NAME;
        db.execSQL(clearDBQuery);
    }
}