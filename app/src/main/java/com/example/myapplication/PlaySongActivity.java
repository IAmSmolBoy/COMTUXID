package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toast;

import java.io.IOException;

public class PlaySongActivity extends AppCompatActivity {
    private String title, artiste, fileLink = "";
    private int drawable, currentIndex = -1;
    private MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause = null;
    private SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
//        Log.d("message", "The index of the arrays is " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void playSong(String songUrl) {
        try {
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            gracefullyStop();
            btnPlayPause.setText("PAUSE");
            setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playNext(View view) {
        currentIndex = songCollection.getNextSong(currentIndex);
//        Log.d("message", "After playNext, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void playPrevious(View view) {
        currentIndex = songCollection.getPrevSong(currentIndex);
//        Log.d("message", "After playPrevious, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void repeat() {
        displaySongBasedOnIndex(songCollection.onRepeat(currentIndex));
        player.setLooping(!player.isLooping());
    }

    private void displaySongBasedOnIndex (int selectedIndex) {
        Song song = songCollection.getCurrentSong(selectedIndex);
        title = song.getTitle();
        artiste = song.getArtiste();
        fileLink = song.getFileLink();
        drawable = song.getDrawable();
        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);
        TextView txtArtist = findViewById(R.id.txtArtist);
        txtArtist.setText(artiste);
        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        iCoverArt.setImageResource(drawable);
//        Log.d("message", "The title of the song is " + fileLink);
    }

    private void gracefullyStop() {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btnPlayPause.setText("PLAY");
            }
        });

    }

    public void playOrPauseMusic(View view) {
        if (player.isPlaying()) {
            player.pause();
            btnPlayPause.setText("PLAY");
        }
        else {
            player.start();
            btnPlayPause.setText("PAUSE");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.release();
    }
}