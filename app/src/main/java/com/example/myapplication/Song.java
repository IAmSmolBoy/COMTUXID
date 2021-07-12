package com.example.myapplication;

public class Song {

    private String id, title, artiste, fileLink;
    private double songLength;
    private int drawable;

    public Song(String id, String title, String artiste, String fileLink, double songLength, int drawable) {
        this.id = id;
        this.title = title;
        this.artiste = artiste;
        this.fileLink = fileLink;
        this.songLength = songLength;
        this.drawable = drawable;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtiste() {
        return artiste;
    }

    public String getFileLink() {
        return fileLink;
    }

    public double getSongLength() {
        return songLength;
    }

    public int getDrawable() {
        return drawable;
    }
}
