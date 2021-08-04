package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
//import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaySongActivity extends AppCompatActivity {
    private String title, artiste, fileLink;
    private int drawable, currentIndex, shuffleIndex = 0;
    private MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause;
    private ImageButton repeatBtn, shuffleBtn;
    private SongCollection songCollection = new SongCollection();
    private Boolean repeatBool = false, shuffleBool = false;
    private Drawable play, pause;
    private TextView totalTime, timeElapsed, titleView;
    SeekBar seekbar;
    Handler handler = new Handler();
    ArrayList<Integer> shuffledOrder = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        titleView = findViewById(R.id.txtSongTitle);
        totalTime = findViewById(R.id.totalTime);
        timeElapsed = findViewById(R.id.timeElapsed);
        repeatBtn = findViewById(R.id.repeat);
        shuffleBtn = findViewById(R.id.shuffleBtn);
        seekbar = findViewById(R.id.seekBar);
        play = getResources().getDrawable(R.drawable.play_black);
        pause = getResources().getDrawable(R.drawable.pause);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        seekbar.setMax(player.getDuration());
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());
                int elapsed = player.getCurrentPosition();
                int duration = player.getDuration();
                timeElapsed.setText(elapsed / 1000 / 60 + ":" + elapsed / 1000 % 60);
                totalTime.setText(duration / 1000 / 60 + ":" + duration / 1000 % 60);
            }
        });
    }

    Runnable pBar = new Runnable() {
        @Override
        public void run() {
            if (player != null && player.isPlaying()) {
                handler.postDelayed(this, 15);
                seekbar.setProgress(player.getCurrentPosition());
            }
        }
    };

    Runnable getTime = new Runnable() {
        @Override
        public void run() {
            int elapsed = player.getCurrentPosition();
            int duration = player.getDuration();
            timeElapsed.setText(elapsed / 1000 / 60 + ":" + elapsed / 1000 % 60);
            totalTime.setText(duration / 1000 / 60 + ":" + duration / 1000 % 60);
            handler.postDelayed(this, 1000);
        }
    };

    public void playSong(String songUrl) {
        try {
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            pBar.run();
            getTime.run();
            gracefullyStop();
            btnPlayPause.setBackground(pause);
            setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playNext(View view) {
        if (shuffleBool) {
            shuffleIndex++;
            displaySongBasedOnIndex(shuffledOrder.get(shuffleIndex));
            playSong(fileLink);
            if (shuffleIndex >= shuffledOrder.size() - 5) {
                int ranNum = (int) Math.round(Math.random() * songCollection.getLength() - 1);
                shuffledOrder.add(ranNum);
            }
        }
        else {
            if (currentIndex < songCollection.getLength() - 1) currentIndex++;
//        currentIndex = songCollection.getNextSong(currentIndex);
//            Log.d("msg", "" + currentIndex);
            displaySongBasedOnIndex(currentIndex);
            playSong(fileLink);
            repeatBtn.setBackgroundResource(R.drawable.repeat);
            repeatBool = false;
        }
    }

    public void playPrevious(View view) {
        if (player.getCurrentPosition() >= 1500) {
            player.seekTo(0);
        }
        else {
            if (shuffleBool) {
                if (shuffleIndex != 0) shuffleIndex--;
                displaySongBasedOnIndex(shuffledOrder.get(shuffleIndex));
                playSong(fileLink);
            }
            else {
                if (currentIndex != 0) currentIndex--;
                displaySongBasedOnIndex(currentIndex);
                playSong(fileLink);
                shuffleBtn.setImageDrawable(getResources().getDrawable(R.drawable.shuffle));
                repeatBtn.setBackgroundResource(R.drawable.repeat);
                repeatBool = false;
            }
        }
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
    }

    private void gracefullyStop() {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (repeatBool) {
                    playOrPauseMusic(null);
                }
                else {
                    btnPlayPause.setBackground(play);
                }
            }
        });
    }

    public void repeat(View v) {
        if (repeatBool) {
//            repeatBtn.setImageDrawable(getResources().getDrawable(R.drawable.repeat));
            repeatBtn.setBackgroundResource(R.drawable.repeat);
        }
        else {
//            repeatBtn.setImageDrawable(getResources().getDrawable(R.drawable.repeat_on));
            repeatBtn.setBackgroundResource(R.drawable.repeat_on);
        }
        repeatBool = !repeatBool;
    }

    public void shuffle(View v) {
    if (shuffleBool) {
        shuffleBtn.setImageDrawable(getResources().getDrawable(R.drawable.shuffle));
//        shuffleBtn.setBackgroundResource(R.drawable.shuffle);
        shuffledOrder.clear();
        currentIndex = songCollection.searchSongByTitle(title);
        shuffleIndex = 0;

    }
    else {
        shuffleBtn.setImageDrawable(getResources().getDrawable(R.drawable.shuffle_on));
        shuffledOrder.add(currentIndex);
//        shuffleBtn.setBackgroundResource(R.drawable.shuffle_on);
        for (int i = 0; i < 5; i++) {
            int ranNum = (int) Math.round(Math.random() * (songCollection.getLength() - 1));
            shuffledOrder.add(ranNum);
        }
    }
        shuffleBool = !shuffleBool;
}

    public void playOrPauseMusic(View view) {
        handler.removeCallbacks(pBar);
        seekbar.setProgress(player.getCurrentPosition());
        handler.removeCallbacks(getTime);
        int elapsed = player.getCurrentPosition();
        int duration = player.getDuration();
        timeElapsed.setText(elapsed / 1000 / 60 + ":" + elapsed / 1000 % 60);
        totalTime.setText(duration / 1000 / 60 + ":" + duration / 1000 % 60);
        if (player.isPlaying()) {
            player.pause();
            btnPlayPause.setBackground(play);
        }
        else {
            player.start();
            pBar.run();
            getTime.run();
            btnPlayPause.setBackground(pause);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(pBar);
        player.release();
    }
}