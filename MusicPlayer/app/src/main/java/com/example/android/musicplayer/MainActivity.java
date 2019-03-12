package com.example.android.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MiniPlayer mMiniPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.text);
        mMiniPlayer = new MiniPlayer(tv);
        mediaPlayer = MediaPlayer.create(this, R.raw.girls_like_you);
    }

    public void playPause(View view){
        Button button = (Button) findViewById(R.id.button);
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            button.setText("Play");
        }else{
            mediaPlayer.start();
            button.setText("Pause");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMiniPlayer.clear();
    }
}
