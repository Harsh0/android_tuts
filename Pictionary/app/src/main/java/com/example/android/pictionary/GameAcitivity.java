package com.example.android.pictionary;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameAcitivity extends AppCompatActivity {
    private static String TAG = GameAcitivity.class.getSimpleName();

    Context mContext;

    Map<Integer, List<Questions>> mQuestionsList = new HashMap<>();

    ImageView mImageView;
    EditText mAnswer;
    Button mSubmitButton;
    TextView mTimer;

    private final int totalRound = 5;
    private final int initialDifficulty = 3;

    private int currentRound;
    private int currentDifficulty;
    List<Questions> mUserAskedQuestions = new ArrayList<>();
    private Questions mCurrentRoundQuestion;
    private GameCountDownTimer mGameCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.activity_game_acitivity);
        readJson();
        mImageView = (ImageView) findViewById(R.id.questionImage);
        mAnswer = (EditText) findViewById(R.id.answer);
        mSubmitButton = (Button) findViewById(R.id.submit);
        mTimer = (TextView) findViewById(R.id.timer);

        mSubmitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                // set user answer to question
                mCurrentRoundQuestion.setUserAnswer(mAnswer.getText().toString());
                mUserAskedQuestions.add(mCurrentRoundQuestion);
                // check question correctness
                currentRound++;
                if (compareAnswer(mCurrentRoundQuestion.getAnswer(), mCurrentRoundQuestion.getUserAnswer())) {
                    mCurrentRoundQuestion.setUserAnswerCorrect(true);
                    currentDifficulty++;
                    if (currentDifficulty > 5) {
                        currentDifficulty = 5; // normalize difficulty
                    }
                    nextRound();
                } else {
                    mCurrentRoundQuestion.setUserAnswerCorrect(false);
                    currentDifficulty--;
                    if (currentDifficulty < 1) {
                        gameOver();
                    } else {
                        nextRound();
                    }
                }
            }
        });
        startGame();
    }

    public void gameOver() {
        mGameCountDownTimer.cancel();
        Toast.makeText(mContext, "Game Over", Toast.LENGTH_LONG).show();
        // Create a new intent to open the {@link GameAcitivity}
        Intent gameIntent = new Intent(GameAcitivity.this, GameComplete.class);
        gameIntent.putExtra("gameOver", true);
        // Start the new activity
        startActivity(gameIntent);
    }

    public void completeGame() {
        Toast.makeText(mContext, "Game End with "+getUserCorrectAnswer() + " out of 5 question correct", Toast.LENGTH_LONG).show();

       Intent gameIntent = new Intent(GameAcitivity.this, GameComplete.class);
        gameIntent.putExtra("gameOver", false);
        // Start the new activity
        startActivity(gameIntent);
    }

    public int getUserCorrectAnswer() {
        int total = 0;
        for (int i=0;i<5;i++) {
            if (mUserAskedQuestions.get(i).isUserAnswerCorrect()) {
                total++;
            }
        }
        return total;
    }

    public void nextRound() {
        if (currentRound > 5) {
            completeGame();
            return;
        }
        //set current question
        mCurrentRoundQuestion = pickRandomQuestion(currentDifficulty);
        renderCurrentQuestion();
        // start the timer
        if (mGameCountDownTimer != null) {
            mGameCountDownTimer.cancel();
        } else {
            mGameCountDownTimer = new GameCountDownTimer(30000, 1000);
        }
        mGameCountDownTimer.start();
    }

    public void startGame() {
        // show first question
        currentDifficulty = initialDifficulty;
        currentRound = 1;
        nextRound();
    }

    public void renderCurrentQuestion() {
        setTitle(String.format("Round %s/%s", currentRound, totalRound));
        Picasso.get().load(mCurrentRoundQuestion.getImageUrl())
            .into(mImageView);
        mAnswer.setText("");
    }


    public void readJson() {
        try {
            InputStream is = mContext.getResources().openRawResource(R.raw.pictionary);
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }

            String jsonLocation = writer.toString();
            JSONArray formArray = new JSONArray(jsonLocation);
            for (int i = 0; i < formArray.length(); i++) {
                JSONObject questionObject = formArray.getJSONObject(i);
                Questions questions = new Questions();
                questions.setQuestionId(questionObject.getInt("id"));
                questions.setImageUrl(questionObject.getString("imageUrl"));
                questions.setDifficulty(questionObject.getInt("difficulty"));
                questions.setAnswer(questionObject.getString("answer"));
                if (mQuestionsList.get(questions.getDifficulty()) != null) {
                    mQuestionsList.get(questions.getDifficulty()).add(questions);
                } else {
                    List<Questions> questionsList = new ArrayList<>(5);
                    questionsList.add(questions);
                    mQuestionsList.put(questions.getDifficulty(), questionsList);
                }
            }
        } catch (IOException e) {
            Log.v(TAG, "ioexception" + e);
        } catch (JSONException e) {
            Log.v(TAG, "jsonException" + e);
        }
    }

    public Questions pickRandomQuestion(int difficulty) {
        double random = Math.random();
        double randomInt = Math.floor(random * mQuestionsList.get(difficulty).size());
        return mQuestionsList.get(difficulty).remove((int) randomInt);
    }

    public boolean compareAnswer(final String correct, String userAnswer) {
        userAnswer = userAnswer.trim();
        if (userAnswer.length() > 0 && userAnswer.substring(userAnswer.length()-1, userAnswer.length()).equals(".")) {
            userAnswer = userAnswer.substring(0, userAnswer.length()-1);
        }
        return correct.toLowerCase().equals(userAnswer.toLowerCase());
    }

    public class GameCountDownTimer extends CountDownTimer {

        public GameCountDownTimer(final long millisInFuture, final long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(final long l) {
            mTimer.setText(String.format("00:%s", l/1000));
        }

        @Override
        public void onFinish() {
            currentDifficulty--;
            currentRound++;
            if (currentDifficulty < 1) {
                gameOver();
            } else {
                nextRound();
            }
        }
    }
}