package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.myapplication.MainActivity.favList;
import static com.example.myapplication.MainActivity.gson;
import static com.example.myapplication.MainActivity.playlistList;
import static com.example.myapplication.MainActivity.songCollection;
import static com.example.myapplication.MainActivity.sp;
import static com.example.myapplication.MainActivity.spPlaylist;

public class addAdapter extends RecyclerView.Adapter<favView> {
    Song[] songs;
    Playlist playlist;
    public addAdapter(Song[] songs, int playlist) {
        this.songs = songs;
        this.playlist = playlistList.get(playlist);
    }

    Context context;
    Song song;
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
        song = songs[position];
        TextView artist = holder.songArtist;
        TextView title = holder.songTitle;
        ImageButton image = holder.songImg;
        artist.setText(song.getArtiste());
        title.setText(song.getTitle());
        image.setImageResource(song.getDrawable());
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlaySongActivity.class);
                intent.putExtra("index", position);
                context.startActivity(intent);
            }
        });
        ImageButton btn = holder.favBtn;
        btn.setImageDrawable(context.getResources().getDrawable(R.drawable.add));
        btn.setOnClickListener(new View.OnClickListener () {
            public void onClick (View v) {
                playlist.add(songCollection.getCurrentSong(position));
                notifyDataSetChanged();
                String json = gson.toJson(playlistList);
                SharedPreferences.Editor editor = spPlaylist.edit();
                editor.putString("list", json);
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {return songs.length;}
}
