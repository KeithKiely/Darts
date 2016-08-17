package com.example.xdog.dartsscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class PracticeSession extends AppCompatActivity {
    private EditText dartsThrownET;
    private TextView numberTypeTV, currentGoalTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_session);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if (myToolbar != null) {
            setSupportActionBar(myToolbar);
            myToolbar.setTitle(getResources().getString(R.string.app_name));
        }

        dartsThrownET = (EditText) findViewById(R.id.dartsThrownET);
        numberTypeTV = (TextView) findViewById(R.id.numberTypeTV);
        currentGoalTV = (TextView) findViewById(R.id.currentGoalTV);
    }

    public void generateNewGoal(View view) {
        Random r = new Random();
        String goal = "";
        int randNum = r.nextInt(21 - 1 + 1) + 1;
        if (randNum == 21) {
            int bullOr25 = r.nextInt(2 - 1 + 1) + 1;
            if (bullOr25 == 1) {
                bullOr25 = 25;
                goal = ""+bullOr25;
            } else {
                bullOr25 = 50;
                goal = ""+bullOr25;
            }
            numberTypeTV.setText("");
        } else {
            goal = randNum + "";
        }
        currentGoalTV.setText(goal);
        int secondRandNum = r.nextInt(3 - 1 + 1) + 1;
        if (secondRandNum == 1 && randNum != 21) {
            numberTypeTV.setText(getResources().getString(R.string.single));
        }
        if (secondRandNum == 2  && randNum != 21) {
            numberTypeTV.setText(getResources().getString(R.string._double));
        }
        if(secondRandNum == 3  && randNum != 21){
            numberTypeTV.setText(getResources().getString(R.string.triple));
        }
    }
}
