package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.myapplication.MainActivity2.favList;
import static com.example.myapplication.MainActivity2.gson;
import static com.example.myapplication.MainActivity2.sp;

public class MainActivity extends AppCompatActivity {

    static SongCollection songCollection = new SongCollection();
    static ArrayList<Song> favList = new ArrayList<Song>();
    static SharedPreferences sp;
    static Gson gson;
    public Drawable favourites, favouritesOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("playlist", MODE_PRIVATE);
        String playlist = sp.getString("list", "");
        gson = new Gson();
        if (!playlist.equals("")) {
            TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>() {};
            Gson gson = new Gson();
            favList = gson.fromJson(playlist, token.getType());
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        favourites = getResources().getDrawable(R.drawable.favourite);
        favouritesOn = getResources().getDrawable(R.drawable.favourite_on);
    }

    public void sendDataToActivity (int index) {
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

//    public void addFav(View view) {
//        ImageButton btn = (ImageButton) view;
//        String songID = getResources().getResourceEntryName(view.getId());
//        Song song  = songCollection.getCurrentSong(songCollection.searchSongById(songID));
//        if (view.getContentDescription().equals("On")) {
//            btn.setImageDrawable(favourites);
//            view.setContentDescription("Off");
//            String id = getResources().getResourceName(view.getId());
//            Log.d("msg", "" + songCollection.searchSongById(id));
////            Song targetSong = songCollection.getCurrentSong(songCollection.searchSongById(id));
////            favList.remove(targetSong);
//            String json = gson.toJson(favList);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putString("list", json);
//            editor.apply();
//        }
//        else {
//            btn.setImageDrawable(favouritesOn);
//            view.setContentDescription("On");
//            favList.add(song);
//            String json = gson.toJson(favList);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putString("list", json);
//            editor.apply();
//        }
//    }

    public void favActivity(View view) {
        Intent intent = new Intent(this, FavActivity.class);
        startActivity(intent);
    }

    public void handleSelection(View view) {
        String buttonId = view.getContentDescription().toString();
        int currentArrayIndex = songCollection.searchSongById(buttonId);
        sendDataToActivity(currentArrayIndex);
    }
}