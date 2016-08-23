package com.example.xdog.dartsscore;

import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

public class PracticeSession extends AppCompatActivity {
    private TextView numberTypeTV, currentGoalTV;
    private EditText dartsThrownET;
    private HashMap<String, Integer> attempts;
    private int currentGoal;
    public static final String HASH_MAP = "map";
    public static Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_session);
        dartsThrownET = (EditText) findViewById(R.id.dartsThrownET);
        if (dartsThrownET != null) {
            dartsThrownET.setInputType(InputType.TYPE_NULL);
        }
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

    public void clear(View view) {
        dartsThrownET.setText("");
    }
    public void done(View view) {
        String searchKey;
        if (!dartsThrownET.getText().toString().equals("")) {
            if (currentGoal == 50 || currentGoal == 25) {
                searchKey = currentGoal + " Single";
            } else {
                searchKey = currentGoal + " " + numberTypeTV.getText().toString();
            }
            int currentGoal = 0;
            if (!dartsThrownET.getText().toString().equals("")) {
                currentGoal = Integer.parseInt(dartsThrownET.getText().toString());
            }
            int currentAttempts = attempts.get(searchKey); //Number of darts previously used to hit number
            int temp = currentAttempts + currentGoal;
            attempts.put(searchKey, temp);
            dartsThrownET.setText("");
            generateGoal();
        } else {
            Context context1 = getApplicationContext();
            CharSequence message = getResources().getString(R.string.no_empty_fields);
            final Toast toastBasic = Toast.makeText(context1,message, Toast.LENGTH_SHORT);
            toastBasic.show();
        }
    }

    public void bOne(View view) {
        String num = dartsThrownET.getText().toString() + "" + 1;
        Log.i("PracticeSession: ", " " + num);
        dartsThrownET.setText(num);
    }
    public void bTwo(View view) {
        String num = dartsThrownET.getText().toString() + "" + 2;
        dartsThrownET.setText(num);
    }
    public void bThree(View view) {
        String num = dartsThrownET.getText().toString() + ""+ 3;
        dartsThrownET.setText(num);
    }
    public void bFour(View view) {
        String num = dartsThrownET.getText().toString() + "" + 4;
        dartsThrownET.setText(num);
    }
    public void bFive(View view) {
        String num = dartsThrownET.getText().toString() + "" + 5;
        dartsThrownET.setText(num);
    }
    public void bSix(View view) {
        String num = dartsThrownET.getText().toString() + "" + 6;
        dartsThrownET.setText(num);
    }
    public void bSeven(View view) {
        String num = dartsThrownET.getText().toString() + "" + 7;
        dartsThrownET.setText(num);
    }
    public void bEight(View view) {
        String num = dartsThrownET.getText().toString() + "" + 8;
        dartsThrownET.setText(num);
    }
    public void bNine(View view) {
        String num = dartsThrownET.getText().toString() + "" + 9;
        dartsThrownET.setText(num);
    }
    public void bZero(View view) {
        String num = dartsThrownET.getText().toString() + "" + 0;
        dartsThrownET.setText(num);
    }

    public void openInfo(View view) {
        FragmentManager fm = getFragmentManager();
        UsageFragment usageFragment = new UsageFragment();
        usageFragment.show(fm, getResources().getString(R.string.how_to));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getResources().getString(R.string.close_game))
                .setMessage(getResources().getString(R.string.are_you_ready_to_close_game))
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TreeSet<Integer> tempThree = (TreeSet<Integer>) new TreeSet<>(attempts.values()).descendingSet();
                        HashMap<String, Integer> tempMap = new HashMap<>();
                        int index = 0;
                        for (Integer temp: tempThree) {
                            index++;

                            for (Map.Entry<String, Integer> map: attempts.entrySet()) {
                                if (map.getValue() == temp.intValue()) {
                                    //Log.i("PracticeSession: ", " Key: " + map.getKey());
                                    //Log.i("PracticeSession: ", " Value: " + map.getValue());
                                    tempMap.put(map.getKey(),map.getValue());
                                }
                            }
                            if (index == 3) {
                                break;
                            }
                        }
                        bundle = new Bundle();
                        bundle.putSerializable(HASH_MAP, tempMap);

                        FragmentManager fm = getFragmentManager();
                        ShotsMissedDialog dialogFragment = new ShotsMissedDialog();
                        dialogFragment.setArguments(bundle);
                        dialogFragment.show(fm, "Stats");
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
