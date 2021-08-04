package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import static com.example.myapplication.MainActivity2.favList;
import static com.example.myapplication.MainActivity2.gson;
import static com.example.myapplication.MainActivity2.sp;

public class FavActivity extends AppCompatActivity {
    RecyclerView favList;
    SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        favList = findViewById(R.id.recyclerView);
        songAdapter = new SongAdapter(MainActivity2.favList);
        favList.setAdapter(songAdapter);
        favList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void removeAll(View view) {
        MainActivity2.favList.clear();
        songAdapter.notifyDataSetChanged();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("list", "");
        editor.apply();
    }
}