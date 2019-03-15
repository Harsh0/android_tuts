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

    private MiniPlayer mMiniPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MiniPlayerView mp = (MiniPlayerView) findViewById(R.id.MiniMediaPlayer);
        mMiniPlayer = new MiniPlayer(mp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMiniPlayer.clear();
    }
}
