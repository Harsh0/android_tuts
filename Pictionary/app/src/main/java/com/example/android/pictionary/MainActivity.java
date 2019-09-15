package com.example.android.pictionary;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mStartGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartGameButton = (Button) findViewById(R.id.startGame);
        mStartGameButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Create a new intent to open the {@link GameAcitivity}
                Intent gameIntent = new Intent(MainActivity.this, GameAcitivity.class);
                // Start the new activity
                startActivity(gameIntent);
            }
        });
    }
}
