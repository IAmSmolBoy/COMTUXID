package com.example.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.myapplication.MainActivity.favList;
import static com.example.myapplication.MainActivity.gson;
import static com.example.myapplication.MainActivity.sp;

public class favAdapter extends RecyclerView.Adapter<favView> {
    List<Song> songs;

    public favAdapter(List<Song> songs) {this.songs = songs;}
    Context context;

    @NonNull
    @Override
    public favView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.item_favourites, parent, false);
        favView viewHolder = new favView(songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull favView holder, int position) {
        Song song = songs.get(position);
        TextView artist = holder.songArtist;
        artist.setText(song.getArtiste());
        TextView title = holder.songTitle;
        title.setText(song.getTitle());
        ImageView image = holder.songImg;
        image.setImageResource(song.getDrawable());
        holder.favBtn.setOnClickListener(new View.OnClickListener () {
            public void onClick (View v) {
                favList.remove(position);
                notifyDataSetChanged();
                String json = gson.toJson(favList);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("list", json);
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
