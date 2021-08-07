package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.myapplication.MainActivity.favList;
import static com.example.myapplication.MainActivity.gson;
import static com.example.myapplication.MainActivity.playlistList;
import static com.example.myapplication.MainActivity.songCollection;
import static com.example.myapplication.MainActivity.sp;
import static com.example.myapplication.MainActivity.spPlaylist;

public class PlaylistActivityAdapter extends RecyclerView.Adapter<favView> {
    List<Song> songs;
    int playlist;
    public PlaylistActivityAdapter(List<Song> songs, int playlist) {this.songs = songs;this.playlist = playlist;}
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
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaySongActivity.class);
                intent.putExtra("index", -1);
                intent.putExtra("playlist", playlist);
                intent.putExtra("playlistIndex", position);
                context.startActivity(intent);
            }
        });
        ImageButton btn = holder.favBtn;
        btn.setImageDrawable(context.getResources().getDrawable(R.drawable.remove));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playlistList.get(playlist).remove(position);
                notifyDataSetChanged();
                String json = gson.toJson(playlistList);
                SharedPreferences.Editor editor = spPlaylist.edit();
                editor.putString("list", json);
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {return songs.size();}
}
