package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyView extends RecyclerView.ViewHolder{

    public TextView titletxt, artistTxt;
    public ImageView image;
    public Button removeBtn;
    public MyView(@NonNull View itemView) {
        super(itemView);
        titletxt = itemView.findViewById(R.id.titletxt);
        artistTxt = itemView.findViewById(R.id.ArtistTxt);
        image = itemView.findViewById(R.id.image);
        removeBtn = itemView.findViewById(R.id.removeBtn);
    }
}
