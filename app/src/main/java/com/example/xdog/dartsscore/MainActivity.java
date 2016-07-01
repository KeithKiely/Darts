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

        int dartOne = 0;
        int dartThree = 0;
        int dartTwo = 0;

        int selectedButtonG1 = group1.getCheckedRadioButtonId();
        if(selectedButtonG1 == 2131492949) {
            if(validScore(Integer.parseInt(dart1.getText().toString()))) {
                dartOne = Integer.parseInt(dart1.getText().toString());
            }
        } else if (selectedButtonG1 == 2131492950) {
            if(validScore(Integer.parseInt(dart1.getText().toString()))) {
                dartOne = Integer.parseInt(dart1.getText().toString()) *2;
            }
        } else {
            if(validScore(Integer.parseInt(dart1.getText().toString()))) {
                dartOne = Integer.parseInt(dart1.getText().toString()) * 3;
            }
        }

        switch (group2.getCheckedRadioButtonId()) {
            case R.id.dart2_radio1:
                if(validScore(Integer.parseInt(dart2.getText().toString()))) {
                    dartTwo = Integer.parseInt(dart2.getText().toString());
                }
                break;
            case R.id.dart2_radio2:
                if(validScore(Integer.parseInt(dart2.getText().toString()))) {
                    dartTwo = Integer.parseInt(dart2.getText().toString()) * 2;
                }
                break;
            case R.id.dart2_radio3:
                if(validScore(Integer.parseInt(dart2.getText().toString()))) {
                    dartTwo = Integer.parseInt(dart2.getText().toString()) * 3;
                }
                break;
        }
        switch (group3.getCheckedRadioButtonId()) {
            case R.id.dart3_radio1:
                if(validScore(Integer.parseInt(dart3.getText().toString()))) {
                    dartThree = Integer.parseInt(dart3.getText().toString());
                }
                break;
            case R.id.dart3_radio2:
                if(validScore(Integer.parseInt(dart3.getText().toString()))) {
                    dartThree = Integer.parseInt(dart3.getText().toString()) *2;
                }
                break;
            case R.id.dart3_radio3:
                if(validScore(Integer.parseInt(dart3.getText().toString()))) {
                    dartThree = Integer.parseInt(dart3.getText().toString()) * 3;
                }
                break;
        }
        if (validScore(dartOne)) {
            int total = dartOne + dartTwo + dartThree;
            Log.i("Score: " , " " + total);
        }

    }

    /**
     * validScore() - Checks to see if the user has input a valid score
     * @param dart1, dart2, dart3; int
     * @return true; boolean
     */
    public boolean validScore(int dart1) {
        boolean temp = false;
        if ((dart1 >= 0 && dart1 <= 20 || dart1 == 25 || dart1 == 50 )) {
            temp = true;
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
