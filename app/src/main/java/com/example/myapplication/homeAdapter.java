package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.myapplication.MainActivity.favList;
import static com.example.myapplication.MainActivity.gson;
import static com.example.myapplication.MainActivity.songCollection;
import static com.example.myapplication.MainActivity.sp;

public class homeAdapter extends RecyclerView.Adapter<favView> {
    Song[] songs;

    public homeAdapter(Song[] songs) {this.songs = songs;}
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
    public Drawable favourites, favouritesOn;

    @Override
    public void onBindViewHolder(@NonNull favView holder, int position) {
        song = songs[position];
//        Log.d("msg", song.getTitle() + position);
        TextView artist = holder.songArtist;
        TextView title = holder.songTitle;
        ImageButton image = holder.songImg;
        artist.setText(song.getArtiste());
        title.setText(song.getTitle());
        image.setImageResource(song.getDrawable());
        image.setTag(position);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlaySongActivity.class);
                intent.putExtra("index", (int) v.getTag());
                context.startActivity(intent);
            }
        });
        ImageButton btn = holder.favBtn;
        btn.setImageDrawable(context.getResources().getDrawable(R.drawable.favourite));
        btn.setContentDescription("Off");
        btn.setTag(song.getId());
        for (Song i:favList) {
            if (i.getTitle().equals(song.getTitle())) {
                btn.setContentDescription("On");
                btn.setImageDrawable(context.getResources().getDrawable(R.drawable.favourite_on));
            }
        }
        btn.setOnClickListener(new View.OnClickListener () {
            public void onClick (View v) {
                favourites = v.getResources().getDrawable(R.drawable.favourite);
                favouritesOn = v.getResources().getDrawable(R.drawable.favourite_on);
//                Log.d("msg", (String) v.getTag());
                if (v.getContentDescription().equals("On")) {
                    btn.setImageDrawable(favourites);
                    v.setContentDescription("Off");
                    favList.remove(songCollection.getCurrentSong(songCollection.searchSongById((String) v.getTag())));
                    String json = gson.toJson(favList);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("list", json);
                    editor.apply();
                }
                else {
                    btn.setImageDrawable(favouritesOn);
                    v.setContentDescription("On");
                    favList.add(songCollection.getCurrentSong(songCollection.searchSongById((String) v.getTag())));
                    String json = gson.toJson(favList);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("list", json);
                    editor.apply();
                }
            }
        });
    }

    @Override
    public int getItemCount() {return songs.length;}
}
