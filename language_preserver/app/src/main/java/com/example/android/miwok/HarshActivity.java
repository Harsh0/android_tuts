package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HarshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("het 1");
        textView.setTextSize(56);
        Log.i("harsh","came to harsh activity");
        setContentView(textView);
//        setContentView(R.layout.activity_harsh);
    }
}
