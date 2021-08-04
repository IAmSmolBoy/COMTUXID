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

public class homeAdapter extends RecyclerView.Adapter<homeView> {
    List<Song> songs;

    public homeAdapter(List<Song> songs) {this.songs = songs;}
    Context context;

    @NonNull
    @Override
    public homeView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.item_favourites, parent, false);
        homeView viewHolder = new homeView(songView);
        return viewHolder;
    }
    public Drawable favourites, favouritesOn;

    @Override
    public void onBindViewHolder(@NonNull homeView holder, int position) {
        Song song = songs.get(position);
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
        holder.favBtn.setTag(song.getId());
        holder.favBtn.setOnClickListener(new View.OnClickListener () {
            public void onClick (View v) {
                favourites = v.getResources().getDrawable(R.drawable.favourite);
                ImageButton btn = (ImageButton) v;
                Song song  = songCollection.getCurrentSong(position);
                Log.d("msg", v.getTag().toString());
//                if (v.getContentDescription().equals("On")) {
//                    btn.setImageDrawable(favourites);
//                    v.setContentDescription("Off");
////                    String id = v.getResources().getResourceName(v.getId());
////                    Log.d("msg", "" + songCollection.searchSongById(id));
////            Song targetSong = songCollection.getCurrentSong(songCollection.searchSongById(id));
////            favList.remove(targetSong);
//                    String json = gson.toJson(favList);
//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.putString("list", json);
//                    editor.apply();
//                }
//                else {
//                    btn.setImageDrawable(favouritesOn);
//                    v.setContentDescription("On");
//                    favList.add(song);
//                    String json = gson.toJson(favList);
//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.putString("list", json);
//                    editor.apply();
//                }
            }
        });
    }

    @Override
    public int getItemCount() {return songs.size();}
}
