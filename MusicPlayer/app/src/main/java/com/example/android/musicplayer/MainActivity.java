package com.example.android.musicplayer;

import android.media.MediaPlayer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MiniPlayer mMiniPlayer;
    private List<Track> trackList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MiniPlayerView mp = (MiniPlayerView) findViewById(R.id.MiniMediaPlayer);
        addTrack();
        mMiniPlayer = new MiniPlayer(mp, trackList);
    }

    private void addTrack() {
        Track track = new Track();
        track.setTitle("Duniya Duniya Duniya Duniya Duniya Duniya");
        track.setSubtitle("Luka Chupi");
        track.setArtwork(R.drawable.duniya);
        track.setMediaItem(R.raw.duniya);
        trackList.add(track);
        track = new Track();
        track.setTitle("Dariya");
        track.setSubtitle("Bar Bar Dekho");
        track.setArtwork(R.drawable.dariya);
        track.setMediaItem(R.raw.dariya);
        trackList.add(track);
        track = new Track();
        track.setTitle("Lambherghini");
        track.setSubtitle("The Doorbean Song");
        track.setArtwork(R.drawable.lamberghini);
        track.setMediaItem(R.raw.lambherghini);
        trackList.add(track);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMiniPlayer.clear();
    }
}
