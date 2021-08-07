package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import static com.example.myapplication.MainActivity.playlistList;
import static com.example.myapplication.MainActivity.songCollection;


public class AddActivity extends AppCompatActivity {

    public RecyclerView recycler;
    public addAdapter addAdapter;
    public ArrayList<Song> songs = new ArrayList<Song>();
    public int playlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Bundle songData = this.getIntent().getExtras();
        playlist = songData.getInt("playlistIndex");
        addAdapter = new addAdapter(songCollection.getSongs(), playlist);
        recycler = findViewById(R.id.addRecycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(addAdapter);
    }

    public void back(View v) {
        startActivity(new Intent(this, PlaylistActivity.class).putExtra("playlist", playlist));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back(null);
    }
}