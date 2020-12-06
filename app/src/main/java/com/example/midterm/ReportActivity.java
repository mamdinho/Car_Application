package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Car> arrayList = new ArrayList<Car>(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        listView = findViewById(R.id.reportListview);
        /* Get all arrayList extra */
        Intent intent = getIntent();
        arrayList = intent.getParcelableArrayListExtra("cars");

        CarAdapter adapter = new CarAdapter(this, arrayList);
        listView.setAdapter(adapter);
    }
}