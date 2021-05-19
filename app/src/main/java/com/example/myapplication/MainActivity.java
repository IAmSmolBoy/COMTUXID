package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleSelection(View view) {

        int buttonId = view.getId();
        String id = getResources().getResourceEntryName(buttonId);
        Log.d("message", "The id of the imageButton is " + id);
    }
}