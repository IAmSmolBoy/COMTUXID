package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Cisum");
    }

    public void sendDataToActivity (int index) {
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void handleSelection(View view) {
        int buttonId = view.getId();
        String id = getResources().getResourceEntryName(buttonId);
        int currentArrayIndex = songCollection.searchSongById(id);
        Log.d("message", "The array index is " + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);
    }
}