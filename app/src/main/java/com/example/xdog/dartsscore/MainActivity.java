package com.example.xdog.dartsscore;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText dart1, dart2, dart3;
    private RadioGroup group1, group2, group3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dart1 = (EditText) findViewById(R.id.dartOneTF);
        dart2 = (EditText) findViewById(R.id.dartTwoTF);
        dart3 = (EditText) findViewById(R.id.dartThreeTF);

        group1 = (RadioGroup) findViewById(R.id.radioGroup1);
        group2 = (RadioGroup) findViewById(R.id.radioGroup2);
        group3 = (RadioGroup) findViewById(R.id.radioGroup3);
    }


    /**
     * calculateScore()
     * Adds up the scores in text fields
     * @return total, int
     */
    public void calculateScrore() {
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
                    if ( temp != 25 && temp != 50) {
                        dartOne = temp * 2;
                    }
                    if (temp == 25) {
                        dartOne = 25;
                    }
                    if (temp == 50) {
                        dartOne = 50;
                    }
                } else {
                    validInput = false;
                }
                break;
            case R.id.dart1_radio3:
                if(validScore(Integer.parseInt(dart1.getText().toString()))) {
                    int temp = Integer.parseInt(dart1.getText().toString());
                    if (temp != 25 && temp != 50) {
                        dartOne = temp * 3;
                    }
                    if (temp == 25) {
                        dartOne = 25;
                    }
                    if (temp == 50) {
                        dartOne = 50;
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
                    if (temp != 25 && temp != 50) {
                        dartTwo = temp * 2;
                    }
                    if (temp == 25) {
                        dartTwo = 25;
                    }
                    if (temp == 50) {
                        dartTwo = 50;
                    }
                } else {
                    validInput = false;
                }
                break;
            case R.id.dart2_radio3:
                if (validScore(Integer.parseInt(dart2.getText().toString()))) {
                    int temp = Integer.parseInt(dart2.getText().toString());
                    if (temp != 25 && temp != 50) {
                        dartTwo = temp * 3;
                    }
                    if (temp == 25) {
                        dartTwo = 25;
                    }
                    if (temp == 50) {
                        dartTwo = 50;
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
                    if (temp != 25 && temp != 50) {
                        dartThree = temp * 2;
                    }
                    if (temp == 25) {
                        dartThree = 25;
                    }
                    if (temp == 50) {
                        dartThree = 50;
                    }
                }
                else {
                    validInput = false;
                }
                break;
            case R.id.dart3_radio3:
                if(validScore(Integer.parseInt(dart3.getText().toString()))) {
                    int temp = Integer.parseInt(dart3.getText().toString());
                    if (temp != 25 && temp != 50) {
                        dartThree = temp * 3;
                    }
                    if (temp == 25) {
                        dartThree = 25;
                    }
                    if (temp == 50) {
                        dartThree = 50;
                    }
                } else {
                    validInput = false;
                }
                break;
        }
        if ( validInput) {
            int total = dartOne + dartTwo + dartThree;
            Log.i("Score: " , " " + total);
        } else {
            Log.i("Warning", " Invlaid input");
        }

    }

    /**
     * validScore() - Checks to see if the user has input a valid score
     * @param dart int
     * @return true; boolean
     */
    public boolean validScore(int dart) {
        boolean temp = false;
        Log.i("validScore method"," " +dart);
        if ((dart >= 0 && dart <= 20 || dart == 25 || dart == 50 )) {
            return true;
        }
        return temp;
    }

    public void done(View view) {
        if (dart1.getText().toString().trim().isEmpty() ||
                dart2.getText().toString().trim().isEmpty() ||
                dart1.getText().toString().trim().isEmpty()) {
            Context context = getApplicationContext();
            CharSequence message = "Score Fields cannot be left empty";
            //int duration = Toast.LENGTH_SHORT;
            final Toast toastBasic = Toast.makeText(context,message, Toast.LENGTH_SHORT);
            toastBasic.show();
        } else {
            calculateScrore();
        }
    }
}
