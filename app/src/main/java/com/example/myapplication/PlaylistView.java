package com.example.myapplication;

import android.media.Image;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlaylistView extends RecyclerView.ViewHolder {

    public ImageButton playlistPic, removeBtn;
    public TextView playlistTitle;

    public PlaylistView(@NonNull View itemView) {
        super(itemView);
        playlistPic = itemView.findViewById(R.id.playlistPic);
        playlistTitle = itemView.findViewById(R.id.playlistTitle);
        removeBtn =  itemView.findViewById(R.id.remove);
    }
}
