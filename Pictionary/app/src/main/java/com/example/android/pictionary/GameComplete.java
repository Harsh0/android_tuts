package com.example.android.pictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_complete);
        boolean gameOver = (boolean) getIntent().getExtras().get("gameOver");
        if (gameOver) {
            findViewById(R.id.gameOver).setVisibility(View.VISIBLE);
        }
    }
}
