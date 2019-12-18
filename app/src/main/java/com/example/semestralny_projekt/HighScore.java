package com.example.semestralny_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HighScore extends AppCompatActivity {

    private ListView listView;
    private Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        db = new Database(this);
        listView = (ListView) findViewById(R.id.listview);

        ArrayList<String> arrayList = new ArrayList<>();
        Cursor data = db.getListContests();

        if (data.getCount() == 0) {
            Toast.makeText(this, "Database is Empty", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                arrayList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(listAdapter);
            }
        }

    }

    public void resetScore(View view) {
        this.db.reset();
        this.recreate();
    }
}
