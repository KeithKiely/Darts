package com.example.xdog.dartsscore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Jimena on 29/07/2016.
 */
public class Calculator2 extends AppCompatActivity {
    private EditText dart1ET, dart2ET, dart3ET;
    private Button bOne, bTwo,bThree, bFour, bFive, bSix,
            bSeven, bEight, bNine, bZero, clearButton, doneButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);
        dart1ET = (EditText) findViewById(R.id.dart1ET);
        dart2ET = (EditText) findViewById(R.id.dart2ET);
        dart3ET = (EditText) findViewById(R.id.dart3ET);
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
        clearButton = (Button) findViewById(R.id.clearButton);
        doneButton = (Button) findViewById(R.id.doneButton);
    }

    public void calcScore(View view ) {

    }
}
