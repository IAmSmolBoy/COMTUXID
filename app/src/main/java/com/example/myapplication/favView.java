package com.example.myapplication;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class favView extends RecyclerView.ViewHolder {

    public ImageButton songImg, favBtn;
    public TextView songTitle, songArtist;

    public favView(@NonNull View itemView) {
        super(itemView);
        songImg = itemView.findViewById(R.id.songImg);
        songTitle = itemView.findViewById(R.id.songTitle);
        songArtist = itemView.findViewById(R.id.songArtist);
        favBtn = itemView.findViewById(R.id.favBtn);
    }
}
