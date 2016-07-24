package com.example.xdog.dartsscore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {
    private EditText dart1, dart2, dart3;
    private TextView player;
    private RadioGroup group1, group2, group3;
    private final int bull = 50;
    private final int _25 = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        dart1 = (EditText) findViewById(R.id.dartOneTF);
        dart2 = (EditText) findViewById(R.id.dartTwoTF);
        dart3 = (EditText) findViewById(R.id.dartThreeTF);

        player = (TextView) findViewById(R.id.playerNameTV);
        player.setText(getIntent().getStringExtra("name"));
        group1 = (RadioGroup) findViewById(R.id.radioGroup1);
        group2 = (RadioGroup) findViewById(R.id.radioGroup2);
        group3 = (RadioGroup) findViewById(R.id.radioGroup3);
    }


    /**
     * calculateScore()
     * Adds up the scores in editText fields
     * @return score, int
     */
    public int calculateScore() {
        boolean validInput = true;
        int dartOne = 0;
        int dartThree = 0;
        int dartTwo = 0;

        switch (group1.getCheckedRadioButtonId()) {
            case R.id.dart1_radio1:
                if(validScore(Integer.parseInt(dart1.getText().toString()))) {
                    dartOne = Integer.parseInt(dart1.getText().toString());
                } else {
                    validInput = false;
                }
                break;
            case R.id.dart1_radio2:
                if(validScore(Integer.parseInt(dart1.getText().toString()))) {
                    int temp = Integer.parseInt(dart1.getText().toString());
                    if ( temp != _25 && temp != bull) {
                        dartOne = temp * 2;
                    }
                    if (temp == _25) {
                        dartOne = _25;
                    }
                    if (temp == bull) {
                        dartOne = bull;
                    }
                } else {
                    validInput = false;
                }
                break;
            case R.id.dart1_radio3:
                if(validScore(Integer.parseInt(dart1.getText().toString()))) {
                    int temp = Integer.parseInt(dart1.getText().toString());
                    if (temp != _25 && temp != bull) {
                        dartOne = temp * 3;
                    }
                    if (temp == _25) {
                        dartOne = _25;
                    }
                    if (temp == bull) {
                        dartOne = bull;
                    }
                } else {
                    validInput = false;
                }
                break;
        }
        switch (group2.getCheckedRadioButtonId()) {
            case R.id.dart2_radio1:
                if (validScore(Integer.parseInt(dart2.getText().toString()))) {
                    dartTwo = Integer.parseInt(dart2.getText().toString());
                } else {
                    validInput = false;
                }
                break;
            case R.id.dart2_radio2:
                if (validScore(Integer.parseInt(dart2.getText().toString()))) {
                    int temp = Integer.parseInt(dart2.getText().toString());
                    if (temp != _25 && temp != bull) {
                        dartTwo = temp * 2;
                    }
                    if (temp == _25) {
                        dartTwo = _25;
                    }
                    if (temp == bull) {
                        dartTwo = bull;
                    }
                } else {
                    validInput = false;
                }
                break;
            case R.id.dart2_radio3:
                if (validScore(Integer.parseInt(dart2.getText().toString()))) {
                    int temp = Integer.parseInt(dart2.getText().toString());
                    if (temp != _25 && temp != bull) {
                        dartTwo = temp * 3;
                    }
                    if (temp == _25) {
                        dartTwo = _25;
                    }
                    if (temp == bull) {
                        dartTwo = bull;
                    }
                }
                else {
                    validInput = false;
                }
                break;
        }
        switch (group3.getCheckedRadioButtonId()) {
            case R.id.dart3_radio1:
                if(validScore(Integer.parseInt(dart3.getText().toString()))) {
                    dartThree = Integer.parseInt(dart3.getText().toString());
                } else {
                    validInput = false;
                }
                break;
            case R.id.dart3_radio2:
                if(validScore(Integer.parseInt(dart3.getText().toString()))) {
                    int temp = Integer.parseInt(dart3.getText().toString());
                    if (temp != _25 && temp != bull) {
                        dartThree = temp * 2;
                    }
                    if (temp == _25) {
                        dartThree = _25;
                    }
                    if (temp == bull) {
                        dartThree = bull;
                    }
                }
                else {
                    validInput = false;
                }
                break;
            case R.id.dart3_radio3:
                if(validScore(Integer.parseInt(dart3.getText().toString()))) {
                    int temp = Integer.parseInt(dart3.getText().toString());
                    if (temp != _25 && temp != bull) {
                        dartThree = temp * 3;
                    }
                    if (temp == _25) {
                        dartThree = _25;
                    }
                    if (temp == bull) {
                        dartThree = bull;
                    }
                } else {
                    validInput = false;
                }
                break;
        }
        if ( validInput) {
            return dartOne + dartTwo + dartThree;
        } else {
            return -1;
        }
    }

    /**
     * validScore() - Checks to see if the user has input a valid score
     * @param dart int
     * @return true; boolean
     */
    public boolean validScore(int dart) {
        if ((dart >= 0 && dart <= 20 || dart == _25 || dart == bull )) {
            return true;
        }
        return false;
    }

    public void done(View view) {
        Context context, context1;
        if (dart1.getText().toString().trim().isEmpty() ||
                dart2.getText().toString().trim().isEmpty() ||
                dart1.getText().toString().trim().isEmpty()) {
            context = getApplicationContext();
            CharSequence message = getResources().getString(R.string.no_empty_fields);
            //int duration = Toast.LENGTH_SHORT;
            final Toast toastBasic = Toast.makeText(context,message, Toast.LENGTH_SHORT);
            toastBasic.show();
        } else {
            Intent intent = new Intent(this, Scoreboard.class);
            if (calculateScore() != -1) {
                intent.putExtra("score", calculateScore());
                setResult(Activity.RESULT_OK, intent);
                finish();
            } else {
                context1 = getApplicationContext();
                CharSequence message = getResources().getString(R.string.invalid_entry);
                //int duration = Toast.LENGTH_SHORT;
                final Toast toastBasic = Toast.makeText(context1,message, Toast.LENGTH_SHORT);
                toastBasic.show();
            }
        }
    }
}
