package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.example.myapplication.MainActivity.gson;
import static com.example.myapplication.MainActivity.sp;
import static com.example.myapplication.MainActivity.spPlaylist;
import static com.example.myapplication.MainActivity.playlistList;

public class PlaylistActivity extends AppCompatActivity {

    public RecyclerView recycler;
    public PlaylistActivityAdapter playlistActivityAdapter;
    int currentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("playlist");
        spPlaylist = getSharedPreferences("playlistList", MODE_PRIVATE);
        String playlists = spPlaylist.getString("list", "");
        if (!playlists.equals("")) {
            TypeToken<ArrayList<Playlist>> token = new TypeToken<ArrayList<Playlist>>() {};
            playlistList = gson.fromJson(playlists, token.getType());
        }
        playlistActivityAdapter = new PlaylistActivityAdapter(MainActivity.playlistList.get(currentIndex).songs(), currentIndex);
        playlistActivityAdapter.notifyDataSetChanged();
        recycler = findViewById(R.id.PlaylistActivityRecycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(playlistActivityAdapter);
        TextView title = findViewById(R.id.PlaylistTitle);
        title.setText(playlistList.get(currentIndex).getPlaylistTitle());
    }

    public void adding(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("playlistIndex", currentIndex);
        startActivity(intent);
    }


    public void back(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void clear(View view) {
        playlistList.get(currentIndex).clear();
        playlistActivityAdapter.notifyDataSetChanged();
        SharedPreferences.Editor editor = spPlaylist.edit();
        editor.putString("list", "");
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}