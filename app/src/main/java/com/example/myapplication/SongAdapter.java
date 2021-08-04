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

import com.google.gson.Gson;

import java.util.List;

import static com.example.myapplication.MainActivity2.favList;
import static com.example.myapplication.MainActivity2.gson;
import static com.example.myapplication.MainActivity2.sp;

public class SongAdapter extends RecyclerView.Adapter<MyView> {
    List<Song> songs;

    public SongAdapter(List<Song> songs) {
        this.songs = songs;
    }
    Context context;
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.item_song, parent, false);
        MyView viewHolder = new MyView(songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        Song song = songs.get(position);
        TextView artist = holder.artistTxt;
        artist.setText(song.getArtiste());
        TextView title = holder.titletxt;
        title.setText(song.getTitle());
        ImageView image = holder.image;
        image.setImageResource(song.getDrawable());
        holder.removeBtn.setOnClickListener(new View.OnClickListener () {
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
