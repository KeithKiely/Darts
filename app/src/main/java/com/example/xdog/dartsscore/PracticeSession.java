package com.example.xdog.dartsscore;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

public class PracticeSession extends AppCompatActivity {
    private TextView numberTypeTV, currentGoalTV;
    private EditText dartsThrownET;
    private HashMap<String, Integer> attempts;
    private int currentGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_session);

        attempts = new HashMap<>();
        String [] dartboard = getResources().getStringArray(R.array.dartboard);
        for (String aDartboard : dartboard) {
            attempts.put(aDartboard, 0);
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if (myToolbar != null) {
            setSupportActionBar(myToolbar);
            myToolbar.setTitle(getResources().getString(R.string.app_name));
        }

        dartsThrownET = (EditText) findViewById(R.id.dartsThrownET);
        numberTypeTV = (TextView) findViewById(R.id.numberTypeTV);
        currentGoalTV = (TextView) findViewById(R.id.currentGoalTV);

        generateGoal();
    }

    public void newGoal(View view) {
        String searchKey = currentGoal + numberTypeTV.getText().toString();
        int currentGoal = 0;
        if (!dartsThrownET.getText().toString().equals("")) {
            currentGoal = Integer.parseInt(dartsThrownET.getText().toString());
        }
        int currentAttempts = attempts.get(searchKey); //Number of darts previously used to hit number
        int temp = currentAttempts + currentGoal;
        attempts.put(searchKey, temp);
        dartsThrownET.setText("");
        generateGoal();
    }

    public String generateGoal() {
        Random r = new Random();
        String goal;
        int randNum = r.nextInt(21 - 1 + 1) + 1;
        currentGoal = randNum;
        if (randNum == 21) {
            int bullOr25 = r.nextInt(2 - 1 + 1) + 1;
            if (bullOr25 == 1) {
                bullOr25 = 25;
                currentGoal = bullOr25;
                goal = ""+bullOr25;
            } else {
                bullOr25 = 50;
                currentGoal = bullOr25;
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
        return goal+numberTypeTV.getText().toString().trim();
    }

    public void findHighestValues(View view) {
        TreeSet<Integer> tempMap = (TreeSet<Integer>) new TreeSet<>(attempts.values()).descendingSet();
        int index = 0;
        for (Integer temp: tempMap) {
            index++;

            for (Map.Entry<String, Integer> map: attempts.entrySet()) {
                if (map.getValue() == temp.intValue()) {
                    Log.i("PracticeSession: ", " Key: " + map.getKey());
                    Log.i("PracticeSession: ", " Value: " + map.getValue());
                }
            }
            if (index == 3) {
                break;
            }
        }
    }

    public void openStats(View view) {
        FragmentManager fm = getFragmentManager();
        ShotsMissedDialog shotsMissedDialog = new ShotsMissedDialog();
        shotsMissedDialog.show(fm, getResources().getString(R.string.how_to));
    }
}
