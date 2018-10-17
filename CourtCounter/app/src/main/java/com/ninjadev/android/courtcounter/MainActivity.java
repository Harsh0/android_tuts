package com.ninjadev.android.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ninjadev.android.courtcounter.R;

public class MainActivity extends AppCompatActivity {

    int teamAScore = 0;
    int teamBScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayScore();
    }

    /**
     * This method add 3 points to perticular team for which the button has pressed
     * @param view
     */
    public void plus3Points(View view){
        if(view.getId()==R.id.plus3_team_a){
            teamAScore += 3;
        }else if(view.getId()==R.id.plus3_team_b){
            teamBScore += 3;
        }
        displayScore();
    }

    /**
     * This method add 2 points to perticular team for which the +2 POINTS button has been pressed
     * @param view
     */
    public void plus2Points(View view){
        if(view.getId()==R.id.plus2_team_a){
            teamAScore += 2;
        }else if(view.getId()==R.id.plus2_team_b){
            teamBScore += 2;
        }
        displayScore();
    }

    /**
     * This method add 1 points for perticular team for which the FREE THROW button has pressed
     * @param view
     */
    public void freeThrow(View view){
        if(view.getId()==R.id.freethrow_team_a){
            teamAScore++;
        }else if(view.getId()==R.id.freethrow_team_b){
            teamBScore++;
        }
        displayScore();
    }

    /**
     * This method reset all teams score
     * @param view
     */
    public void resetScore(View view){
        teamAScore = 0;
        teamBScore = 0;
        displayScore();
    }

    /**
     * This method display the respective score of teams
     */
    private void displayScore(){
        TextView teamAScoreView = (TextView) findViewById(R.id.score_team_a);
        teamAScoreView.setText(teamAScore+"");
        TextView teamBScoreView = (TextView) findViewById(R.id.score_team_b);
        teamBScoreView.setText(teamBScore+"");
    }
}
