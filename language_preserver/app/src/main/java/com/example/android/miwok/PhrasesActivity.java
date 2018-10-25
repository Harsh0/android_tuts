/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        //Create a list of word
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.raw.number_one));
        words.add(new Word("two", "lutti", R.raw.number_one));
        words.add(new Word("three", "lutti", R.raw.number_one));
        words.add(new Word("four", "lutti", R.raw.number_one));
        words.add(new Word("five", "lutti", R.raw.number_one));
        words.add(new Word("six", "lutti", R.raw.number_one));
        words.add(new Word("seven", "lutti", R.raw.number_one));
        words.add(new Word("eight", "lutti", R.raw.number_one));
        words.add(new Word("nine", "lutti", R.raw.number_one));
        words.add(new Word("ten", "na'aacha", R.raw.number_one));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long l) {
            Word word = words.get(position);

            Log.v("PhrasesActivity", "Current word: " + word);

            MediaPlayer.create(PhrasesActivity.this, word.getAudioTranslation()).start();
            }
        });
    }
}
