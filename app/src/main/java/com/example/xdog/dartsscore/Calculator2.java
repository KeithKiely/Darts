package com.example.xdog.dartsscore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Keith on 29/07/2016.
 * Calculates 3 dart score
 */
public class Calculator2 extends AppCompatActivity implements View.OnFocusChangeListener {
    private EditText dart1ET, dart2ET, dart3ET;
    private Button bOne, bTwo,bThree, bFour, bFive, bSix,
            bSeven, bEight, bNine, bZero;
    private boolean editText1Selected = true;
    private boolean editText2Selected = false;
    private boolean editText3Selected = false;
    private int d1Mem, d2Mem, d3Mem, dart1Num,dart2Num,dart3Num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if (myToolbar != null) {
            setSupportActionBar(myToolbar);
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        }
        ((EditText) findViewById(R.id.dart1ET)).setInputType(InputType.TYPE_NULL);
        ((EditText) findViewById(R.id.dart2ET)).setInputType(InputType.TYPE_NULL);
        ((EditText) findViewById(R.id.dart3ET)).setInputType(InputType.TYPE_NULL);
        dart1ET = (EditText) findViewById(R.id.dart1ET);
        dart2ET = (EditText) findViewById(R.id.dart2ET);
        dart3ET = (EditText) findViewById(R.id.dart3ET);
        dart1ET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editText1Selected = true;
                    editText2Selected = false;
                    editText3Selected = false;
                }
            }
        });
        dart2ET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editText1Selected = false;
                    editText2Selected = true;
                    editText3Selected = false;
                }
            }
        });
        dart3ET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editText1Selected = false;
                    editText2Selected = false;
                    editText3Selected = true;
                }
            }
        });
        bOne = (Button) findViewById(R.id.bOne);
        bTwo = (Button) findViewById(R.id.bTwo);
        bThree = (Button) findViewById(R.id.bThree);
        bFour = (Button) findViewById(R.id.bFour);
        bFive = (Button) findViewById(R.id.bFive);
        bSix = (Button) findViewById(R.id.bSix);
        bSeven = (Button) findViewById(R.id.bSeven);
        bEight = (Button) findViewById(R.id.bEight);
        bZero = (Button) findViewById(R.id.bZero);
        bNine = (Button) findViewById(R.id.bNine);
    }

    public void clear(View view) {
        if (editText1Selected) {
            dart1ET.setText("");
        }
        if (editText2Selected) {
            dart2ET.setText("");
        }
        if (editText3Selected) {
            dart3ET.setText("");
        }
    }

    public void  bOne(View view) {
        if (editText1Selected) {
            String temp = dart1ET.getText().toString().trim() + bOne.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart1ET.setText(temp);
            }
        }if (editText2Selected) {
            String temp = dart2ET.getText().toString().trim() + bOne.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart2ET.setText(temp);
            }
        }if (editText3Selected) {
            String temp = dart3ET.getText().toString().trim() + bOne.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart3ET.setText(temp);
            }
        }
    }
    public void  bTwo(View view) {
        if (editText1Selected) {
            String temp = dart1ET.getText().toString().trim() + bTwo.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart1ET.setText(temp);
            }
        }if (editText2Selected) {
            String temp = dart2ET.getText().toString().trim() + bTwo.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart2ET.setText(temp);
            }
        }if (editText3Selected) {
            String temp = dart3ET.getText().toString().trim() + bTwo.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart3ET.setText(temp);
            }
        }
    }
    public void  bThree(View view) {
        if (editText1Selected) {
            String temp = dart1ET.getText().toString().trim() + bThree.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart1ET.setText(temp);
            }
        }if (editText2Selected) {
            String temp = dart2ET.getText().toString().trim() + bThree.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart2ET.setText(temp);
            }
        }if (editText3Selected) {
            String temp = dart3ET.getText().toString().trim() + bThree.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart3ET.setText(temp);
            }
        }
    }
    public void  bFour(View view) {
        if (editText1Selected) {
            String temp = dart1ET.getText().toString().trim() + bFour.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart1ET.setText(temp);
            }
        }if (editText2Selected) {
            String temp = dart2ET.getText().toString().trim() + bFour.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart2ET.setText(temp);
            }
        }if (editText3Selected) {
            String temp = dart3ET.getText().toString().trim() + bFour.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart3ET.setText(temp);
            }
        }
    }
    public void  bFive(View view) {
        if (editText1Selected) {
            String temp = dart1ET.getText().toString().trim() + bFive.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart1ET.setText(temp);
            }
        }if (editText2Selected) {
            String temp = dart2ET.getText().toString().trim() + bFive.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart2ET.setText(temp);
            }
        }if (editText3Selected) {
            String temp = dart3ET.getText().toString().trim() + bFive.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart3ET.setText(temp);
            }
        }
    }
    public void  bSix(View view) {
        if (editText1Selected) {
            String temp = dart1ET.getText().toString().trim() + bSix.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart1ET.setText(temp);
            }
        }if (editText2Selected) {
            String temp = dart2ET.getText().toString().trim() + bSix.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart2ET.setText(temp);
            }
        }if (editText3Selected) {
            String temp = dart3ET.getText().toString().trim() + bSix.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart3ET.setText(temp);
            }
        }
    }
    public void  bSeven(View view) {
        if (editText1Selected) {
            String temp = dart1ET.getText().toString().trim() + bSeven.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart1ET.setText(temp);
            }
        }if (editText2Selected) {
            String temp = dart2ET.getText().toString().trim() + bSeven.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart2ET.setText(temp);
            }
        }if (editText3Selected) {
            String temp = dart3ET.getText().toString().trim() + bSeven.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart3ET.setText(temp);
            }
        }
    }
    public void  bEight(View view) {
        if (editText1Selected) {
            String temp = dart1ET.getText().toString().trim() + bEight.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart1ET.setText(temp);
            }
        }if (editText2Selected) {
            String temp = dart2ET.getText().toString().trim() + bEight.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart2ET.setText(temp);
            }
        }if (editText3Selected) {
            String temp = dart3ET.getText().toString().trim() + bEight.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart3ET.setText(temp);
            }
        }
    }
    public void  bNine(View view) {
        if (editText1Selected) {
            String temp = dart1ET.getText().toString().trim() + bNine.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart1ET.setText(temp);
            }
        }if (editText2Selected) {
            String temp = dart2ET.getText().toString().trim() + bNine.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart2ET.setText(temp);
            }
        }if (editText3Selected) {
            String temp = dart3ET.getText().toString().trim() + bNine.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart3ET.setText(temp);
            }
        }
    }
    public void  bZero(View view) {
        if (editText1Selected) {
            String temp = dart1ET.getText().toString().trim() + bZero.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart1ET.setText(temp);
            }
        }if (editText2Selected) {
            String temp = dart2ET.getText().toString().trim() + bZero.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart2ET.setText(temp);
            }
        }if (editText3Selected) {
            String temp = dart3ET.getText().toString().trim() + bZero.getText().toString();
            if (validInput(Integer.parseInt(temp))) {
                dart3ET.setText(temp);
            }
        }
    }

    public void mult1(View view){
        if (editText1Selected && !dart1ET.getText().toString().equals("")) {
            String temp = ""+d1Mem;
            dart1ET.setText(temp);
            dart1Num = d1Mem;
        }
        if (editText2Selected && !dart2ET.getText().toString().equals("")) {
            String temp = ""+d2Mem;
            dart2ET.setText(temp);
            dart2Num = d1Mem;
        }
        if (editText3Selected && !dart3ET.getText().toString().equals("")) {
            String temp = ""+d3Mem;
            dart3ET.setText(temp);
            dart3Num = d1Mem;
        }
    }

    public void mult2(View view){
        if (!dart1ET.getText().toString().equals("")) {
            d1Mem = Integer.parseInt(dart1ET.getText().toString().trim());
        }
        if (!dart2ET.getText().toString().equals("")) {
            d2Mem = Integer.parseInt(dart2ET.getText().toString().trim());
        }
        if (!dart3ET.getText().toString().equals("")) {
            d3Mem = Integer.parseInt(dart3ET.getText().toString().trim());
        }
        if (editText1Selected && !dart1ET.getText().toString().equals("")) {
            int dart1 =  Integer.parseInt(dart1ET.getText().toString().trim());
            dart1*=2;
            String temp = ""+dart1;
            dart1ET.setText(temp);
            dart1Num = dart1;
        }
        if (editText2Selected && !dart2ET.getText().toString().equals("")) {
            int dart2 =  Integer.parseInt(dart2ET.getText().toString().trim());
            dart2*=2;
            String temp = ""+dart2;
            dart2ET.setText(temp);
            dart2Num = dart2;
        }
        if (editText3Selected && !dart3ET.getText().toString().equals("")) {
            int dart3 =  Integer.parseInt(dart3ET.getText().toString().trim());
            dart3*=2;
            String temp = ""+dart3;
            dart3ET.setText(temp);
            dart3Num = dart3;
        }
    }

    public void mult3(View view){
        if (!dart1ET.getText().toString().equals("")) {
            d1Mem = Integer.parseInt(dart1ET.getText().toString().trim());
        }
        if (!dart2ET.getText().toString().equals("")) {
            d2Mem = Integer.parseInt(dart2ET.getText().toString().trim());
        }
        if (!dart3ET.getText().toString().equals("")) {
            d3Mem = Integer.parseInt(dart3ET.getText().toString().trim());
        }
        if (editText1Selected && !dart1ET.getText().toString().equals("")) {
            int dart1 = Integer.parseInt(dart1ET.getText().toString().trim());
            dart1 *=3;
            String temp = ""+ dart1;
            dart1ET.setText(temp);
            dart1Num = dart1;
        }
        if (editText2Selected && !dart2ET.getText().toString().equals("")) {
            int dart2 = Integer.parseInt(dart2ET.getText().toString().trim());
            dart2 *= 3;
            String temp = ""+dart2;
            dart2ET.setText(temp);
            dart2Num = dart2;
        }
        if (editText3Selected && !dart3ET.getText().toString().equals("")) {
            int dart3 = Integer.parseInt(dart3ET.getText().toString().trim());
            dart3*=3;
            String temp = dart3 + "";
            dart3ET.setText(temp);
            dart3Num = dart3;
        }
    }

    public boolean validInput(int dart) {
        Context context;
        int bull = 50;
        int _25 = 25;
        if ((dart >= 0 && dart <= 20 || dart == _25 || dart == bull)) {
            return true;
        } else {
            context = getApplicationContext();
            CharSequence message = getResources().getString(R.string.invalid_entry);
            //int duration = Toast.LENGTH_SHORT;
            final Toast toastBasic = Toast.makeText(context,message, Toast.LENGTH_SHORT);
            toastBasic.show();
            return false;
        }
    }

    public void nextET(View view) {
        if (editText1Selected) {
            dart2ET.requestFocus();
            editText1Selected = false;
            editText2Selected = true;
            editText3Selected = false;
        } else if (editText2Selected) {
            dart3ET.requestFocus();
            editText1Selected = false;
            editText2Selected = false;
            editText3Selected = true;
        } else if (editText3Selected) {
            dart1ET.requestFocus();
            editText1Selected = true;
            editText2Selected = false;
            editText3Selected = false;
        }
    }
    public void previousET(View view) {
        if (editText1Selected) {
            dart3ET.requestFocus();
            editText1Selected = false;
            editText2Selected = false;
            editText3Selected = true;
        } else if (editText2Selected) {
            dart1ET.requestFocus();
            editText1Selected = true;
            editText2Selected = false;
            editText3Selected = false;
        } else if (editText3Selected) {
            dart2ET.requestFocus();
            editText1Selected = false;
            editText2Selected = true;
            editText3Selected = false;
        }
    }

    public void done(View view ) {
        Context context, context1;
        if (dart1ET.getText().toString().trim().isEmpty() ||
                dart2ET.getText().toString().trim().isEmpty() ||
                dart3ET.getText().toString().trim().isEmpty()) {
            context = getApplicationContext();
            CharSequence message = getResources().getString(R.string.no_empty_fields);
            //int duration = Toast.LENGTH_SHORT;
            final Toast toastBasic = Toast.makeText(context,message, Toast.LENGTH_SHORT);
            toastBasic.show();
        } else {
            Intent intent = new Intent(this, Scoreboard.class);
            if (!dart1ET.getText().toString().equals("") && !dart2ET.getText().toString().equals("")
                    && !dart3ET.getText().toString().equals("")) {
                dart1Num = Integer.parseInt(dart1ET.getText().toString());
                dart2Num = Integer.parseInt(dart2ET.getText().toString());
                dart3Num = Integer.parseInt(dart3ET.getText().toString());
            }
            if (dart1Num + dart2Num + dart3Num != -1) {
                int totalValue = dart1Num + dart2Num + dart3Num;
                intent.putExtra("score", totalValue);
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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.i("Calc: ", " onFocusChange ");
        switch (v.getId()) {
            case R.id.dart1ET:
                editText1Selected = true;
                editText2Selected = false;
                editText3Selected = false;

                break;
            case R.id.dart2ET:
                Log.i("Calc2: ", "EditText two set");
                editText1Selected = false;
                editText2Selected = true;
                editText3Selected = false;
                break;
            case R.id.dart3ET:
                editText1Selected = true;
                editText2Selected = false;
                editText3Selected = true;
                break;

        }
    }
}
