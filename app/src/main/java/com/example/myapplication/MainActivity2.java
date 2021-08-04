package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    SongCollection songCollection = new SongCollection();
    static ArrayList<Song> favList = new ArrayList<Song>();
    static SharedPreferences sp;
    static Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sp = getSharedPreferences("playlist", MODE_PRIVATE);
        String playlist = sp.getString("list", "");
        gson = new Gson();
        if (!playlist.equals("")) {
            TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>() {};
            Gson gson = new Gson();
            favList = gson.fromJson(playlist, token.getType());
        }
    }

    public void add(View view) {
        String songID = view.getContentDescription().toString();
        Song song  = songCollection.getCurrentSong(songCollection.searchSongById(songID));
        favList.add(song);
        String json = gson.toJson(favList);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("list", json);
        editor.apply();
    }

    public void favActivity(View view) {
        Intent intent = new Intent(this, FavActivity.class);
        startActivity(intent);
    }
}