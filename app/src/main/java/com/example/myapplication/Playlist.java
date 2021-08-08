package com.example.myapplication;

import java.util.ArrayList;

public class Playlist {

    private String title;
    private int drawable;
    private ArrayList<Song> playlistSongs;

    public Playlist(String title, int drawable, ArrayList<Song> playlistSongs) {
        this.title = title;
        this.drawable = drawable;
        this.playlistSongs = playlistSongs;
    }

//    public int searchPlaylistByTitle (String title) {
//        for (int i = 0; i < playlistSongs.size(); i++) if (playlistSongs.get(i).getTitle().equals(title)) return i;
//        return -1;
//    }

    public String getPlaylistTitle() {
        return title;
    }

    public int getPlaylistDrawable() {
        return drawable;
    }

    public ArrayList<Song> songs() {return playlistSongs;}

    public void add(Song song) {playlistSongs.add(song);}

    public void remove(int position) {playlistSongs.remove(position);}

    public void clear() {playlistSongs.clear();}
}
