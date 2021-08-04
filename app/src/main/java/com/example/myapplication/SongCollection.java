package com.example.myapplication;

import android.util.Log;

public class SongCollection {

    private Song[] songs = new Song[15];
    public SongCollection() {
        Song theWayYouLookTonight = new Song("S1001", "The Way You Look Tonight", "Michael Buble",
                "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965", 4.66, R.drawable.michael_buble_collection);
        Song billyJean = new Song("S1002", "Billy Jean", "Michael Jackson",
                "https://p.scdn.co/mp3-preview/f504e6b8e037771318656394f532dede4f9bcaea?cid=2afe87a64b0042dabf51f37318616965", 4.9, R.drawable.billie_jean);
        Song one = new Song("S1003", "One", "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/daa8679253ba20620db6e1db9c88edfcf1f4069f?cid=2afe87a64b0042dabf51f37318616965", 4.21, R.drawable.photograph);
        Song NGGYU = new Song("S1004", "Never Gonna Give You Up", "Rick Astley",
                "https://p.scdn.co/mp3-preview/0b6da17f858f104337fac626c4bac972d3947564?cid=2afe87a64b0042dabf51f37318616965", 3.56, R.drawable.nggyu);
        Song BarsNoJutsu = new Song("S1005", "Bars No Jutsu", "Louis Capone",
                "https://p.scdn.co/mp3-preview/a303242bdd35e8efc9ae90474ff00a99c7a63c57?cid=2afe87a64b0042dabf51f37318616965", 2.48, R.drawable.bars_no_jutsu);
        Song TheCookoutCypher = new Song("S1006", "The Cookout Cypher", "Crypt",
                "https://p.scdn.co/mp3-preview/e01240aaaf9d19116ae22d5d9aaf832e3828ddb2?cid=2afe87a64b0042dabf51f37318616965", 	14.8, R.drawable.cookout_cypher);
        Song FourHorsemen = new Song("S1007", "Four Horsemen", "Crypt",
                "https://p.scdn.co/mp3-preview/c8935b94ed9d18ee6c48ed11cd6507632fc28121?cid=2afe87a64b0042dabf51f37318616965", 3.94, R.drawable.four_horsemen);
        Song PoppinCypher = new Song("S1008", "Poppin' Cypher", "Crypt",
                "https://p.scdn.co/mp3-preview/17a44872c5a5de855660d8ff6032737374f3580b?cid=2afe87a64b0042dabf51f37318616965", 	9.71, R.drawable.poppin);
        Song YouTubeCypherVol1 = new Song("S1009", "YouTube Cypher, Vol. 1", "Crypt",
                "https://p.scdn.co/mp3-preview/5a08fe45401781f4af89c601ff27469d33ab22fb?cid=2afe87a64b0042dabf51f37318616965", 9.3, R.drawable.youtube1);
        Song YouTubeCypherVol2 = new Song("S1010", "YouTube Cypher, Vol. 2", "Crypt",
                "https://p.scdn.co/mp3-preview/347429de44f7de56180d4cd39a7d4e3a5371cfa6?cid=2afe87a64b0042dabf51f37318616965", 12.32, R.drawable.youtube2);
        Song YouTubeCypherVol3 = new Song("S1011", "YouTube Cypher, Vol. 3", "Crypt",
                "https://p.scdn.co/mp3-preview/0b6da17f858f104337fac626c4bac972d3947564?cid=2afe87a64b0042dabf51f37318616965", 	12.68, R.drawable.youtube3);
        Song Red = new Song("S1012", "Red (Uchiha Rap)", "Rustage",
                "https://p.scdn.co/mp3-preview/7789b1beaae5f02f804143ef8bffdf0b471feef1?cid=2afe87a64b0042dabf51f37318616965", 3.03, R.drawable.red);
        Song Awake = new Song("S1013", "Awake (Boruto Rap)", "Rustage",
                "https://p.scdn.co/mp3-preview/39dfa0d3c73ed9d471dc5184c336e306e464c834?cid=2afe87a64b0042dabf51f37318616965", 	3.09, R.drawable.awake);
        Song GodLevelThreat = new Song("S1014", "God Level Threat", "GameboyJones",
                "https://p.scdn.co/mp3-preview/c8de95d402bc619e26032238b239f3cdc548c3fb?cid=2afe87a64b0042dabf51f37318616965", 	3.27, R.drawable.godlevel);
        Song MumbleRapperVsLyricist = new Song("S1015", "Mumble Rapper Vs. Lyricist", "Vin Jay",
                "https://p.scdn.co/mp3-preview/c538a90498437d866529b35fbda3c3404099f1e7?cid=2afe87a64b0042dabf51f37318616965", 3.31, R.drawable.mumble);
        songs[0] = theWayYouLookTonight;
        songs[1] = billyJean;
        songs[2] = one;
        songs[3] = NGGYU;
        songs[4] = BarsNoJutsu;
        songs[5] = TheCookoutCypher;
        songs[6] = FourHorsemen;
        songs[7] = PoppinCypher;
        songs[8] = YouTubeCypherVol1;
        songs[9] = YouTubeCypherVol2;
        songs[10] = YouTubeCypherVol3;
        songs[11] = Red;
        songs[12] = Awake;
        songs[13] = GodLevelThreat;
        songs[14] = MumbleRapperVsLyricist;
    }

    public int searchSongById (String id) {
        for (int i = 0; i < songs.length; i++) if (songs[i].getId().equals(id)) return i;
        return -1;
    }

    public int searchSongByTitle (String title) {
        for (int i = 0; i < songs.length; i++) if (songs[i].getTitle().equals(title)) return i;
        return -1;
    }

    public int getLength() {
        return songs.length;
    }

//    public int getNextSong(int currentSongIndex) {
//        if (currentSongIndex >= songs.length - 1) return currentSongIndex;
//        else return (currentSongIndex + 1);
//    }
//
//    public int getPrevSong(int currentSongIndex) {
//        if (currentSongIndex <= 0) return currentSongIndex;
//        else return (currentSongIndex - 1);
//    }

    public Song getCurrentSong (int currentSongId) {
        return songs[currentSongId];
    }

    public Song[] getSongs() {return songs;}

}