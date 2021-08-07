package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.MainActivity.favList;
import static com.example.myapplication.MainActivity.gson;
import static com.example.myapplication.MainActivity.playlistList;
import static com.example.myapplication.MainActivity.sp;
import static com.example.myapplication.MainActivity.spPlaylist;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistView> {
    ArrayList<Playlist> playlists;

    public PlaylistAdapter(ArrayList<Playlist> playlists) {this.playlists = playlists;}
    Context context;
    @NonNull
    @Override
    public PlaylistView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.item_playlist, parent, false);
        PlaylistView viewHolder = new PlaylistView(songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistView holder, int position) {
        Playlist playlist = playlists.get(position);
        TextView title = holder.playlistTitle;
        title.setText(playlist.getPlaylistTitle());
        ImageButton image = holder.playlistPic;
        image.setImageResource(playlist.getPlaylistDrawable());
        image.setTag(position);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaylistActivity.class);
                intent.putExtra("playlist", (int) v.getTag());
                context.startActivity(intent);
            }
        });
        ImageButton btn = holder.removeBtn;
        btn.setOnClickListener(new View.OnClickListener () {
            public void onClick (View v) {
                playlistList.remove(position);
                notifyDataSetChanged();
                String json = gson.toJson(playlistList);
                SharedPreferences.Editor editor = spPlaylist.edit();
                editor.putString("list", json);
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }
}
