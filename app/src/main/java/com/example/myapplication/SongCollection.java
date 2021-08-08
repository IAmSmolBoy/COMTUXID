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
                "https://cdn.discordapp.com/attachments/734375946295050363/873812442847805501/Rick_Astley_-_Never_Gonna_Give_You_Up_Official_Music_Video.mp3", 3.56, R.drawable.nggyu);
        Song BarsNoJutsu = new Song("S1005", "Bars No Jutsu", "Louis Capone",
                "https://cdn.discordapp.com/attachments/734375946295050363/873814963901333545/Bars_No_Jutsu.mp3", 2.48, R.drawable.bars_no_jutsu);
        Song TheCookoutCypher = new Song("S1006", "The Cookout Cypher", "Crypt",
                "https://cdn.discordapp.com/attachments/734375946295050363/873814980535926784/Crypt_-_Cookout_Cypher_ft._GAWNE_Futuristic_Vin_Jay_100Kufis_Samad_Savage_Lex_Bratcher__mo.mp3", 	14.8, R.drawable.cookout_cypher);
        Song FourHorsemen = new Song("S1007", "Four Horsemen", "Crypt",
                "https://cdn.discordapp.com/attachments/734375946295050363/873814960269037588/Crypt_x_Quadeca_x_Dax_x_Scru_-_Four_Horsemen_Official_Video.mp3", 3.94, R.drawable.four_horsemen);
        Song PoppinCypher = new Song("S1008", "Poppin' Cypher", "Crypt",
                "https://cdn.discordapp.com/attachments/734375946295050363/873814970759008336/Crypt_-_Poppin_Cypher_ft._KSI_Top_13_VI_Seconds_100Kufis_OfficiallyLeo_Samad_Savage__More.mp3", 	9.71, R.drawable.poppin);
        Song YouTubeCypherVol1 = new Song("S1009", "YouTube Cypher, Vol. 1", "Crypt",
                "https://p.scdn.co/mp3-preview/5a08fe45401781f4af89c601ff27469d33ab22fb?cid=2afe87a64b0042dabf51f37318616965", 9.3, R.drawable.youtube1);
        Song YouTubeCypherVol2 = new Song("S1010", "YouTube Cypher, Vol. 2", "Crypt",
                "https://p.scdn.co/mp3-preview/347429de44f7de56180d4cd39a7d4e3a5371cfa6?cid=2afe87a64b0042dabf51f37318616965", 12.32, R.drawable.youtube2);
        Song YouTubeCypherVol3 = new Song("S1011", "YouTube Cypher, Vol. 3", "Crypt",
                "https://p.scdn.co/mp3-preview/63c610acf46ddbd7c5285add04f057d3314eb4be?cid=2afe87a64b0042dabf51f37318616965", 	12.68, R.drawable.youtube3);
        Song Red = new Song("S1012", "Red (Uchiha Rap)", "Rustage",
                "https://cdn.discordapp.com/attachments/734375946295050363/873819701615132742/UCHIHA_RAPREDRUSTAGE_ft._Khantrast_Naruto.mp3", 3.03, R.drawable.red);
        Song Awake = new Song("S1013", "Awake (Boruto Rap)", "Rustage",
                "https://cdn.discordapp.com/attachments/734375946295050363/873819701766135808/BORUTO_RAPquotAwakequotRUSTAGE_ft._Postcard_Naruto.mp3", 	3.09, R.drawable.awake);
        Song GodLevelThreat = new Song("S1014", "God Level Threat", "GameboyJones",
                "https://cdn.discordapp.com/attachments/734375946295050363/873819699606069248/Saitama_Rap_God_Level_Threat_GameboyJones_One_Punch_Man_Rap.mp3", 	3.27, R.drawable.godlevel);
        Song MumbleRapperVsLyricist = new Song("S1015", "Mumble Rapper Vs. Lyricist", "Vin Jay",
                "https://cdn.discordapp.com/attachments/734375946295050363/873819693490786304/Vin_Jay_-_Mumble_Rapper_vs_Lyricist.mp3", 3.31, R.drawable.mumble);
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