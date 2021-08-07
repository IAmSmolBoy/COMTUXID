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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static SongCollection songCollection = new SongCollection();
    static ArrayList<Song> favList = new ArrayList<Song>();
    static ArrayList<Playlist> playlistList = new ArrayList<Playlist>();
    static SharedPreferences sp, spPlaylist;
    static Gson gson;
    public Drawable favourites, favouritesOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("playlist", MODE_PRIVATE);
        spPlaylist = getSharedPreferences("playlistList", MODE_PRIVATE);
        String playlist = sp.getString("list", "");
        String playlists = spPlaylist.getString("list", "");

        gson = new Gson();
        if (!playlist.equals("")) {
            TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>() {};
            favList = gson.fromJson(playlist, token.getType());
        }
        if (!playlists.equals("")) {
            TypeToken<ArrayList<Playlist>> token = new TypeToken<ArrayList<Playlist>>() {};
            playlistList = gson.fromJson(playlists, token.getType());
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        favourites = getResources().getDrawable(R.drawable.favourite);
        favouritesOn = getResources().getDrawable(R.drawable.favourite_on);

    }
}