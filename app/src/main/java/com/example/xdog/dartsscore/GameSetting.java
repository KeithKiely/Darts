package com.example.xdog.dartsscore;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class GameSetting extends AppCompatActivity implements OnSeekBarChangeListener{
    private RadioGroup matchScoreGr, numPlayersGr;
    private EditText name1, name2, name3, name4;
    private TextView leg;
    private RadioButton _501, _301, _101;
    private SeekBar numLegs;
    private String nameOne, nameTwo, nameThree, nameFour;
    private Button numPlayer1B, numPlayer2B, numPlayer3B, numPlayer4B;
    private int numPlayers = 0;
    private int gameScore, legs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);

        matchScoreGr = (RadioGroup) findViewById(R.id.gameScore3rd);
        numPlayersGr = (RadioGroup) findViewById(R.id.numPlayerGroup);

        _501 = (RadioButton) findViewById(R.id.radioB501);
        _301 = (RadioButton) findViewById(R.id.radioB301);
        _101 = (RadioButton) findViewById(R.id.radioB101);

        name1 = (EditText) findViewById(R.id.name1ET);
        name2 = (EditText) findViewById(R.id.name2ET);
        name3 = (EditText) findViewById(R.id.name3ET);
        name4 = (EditText) findViewById(R.id.name4ET);
        leg = (TextView) findViewById(R.id.legsTV);



        numLegs = (SeekBar) findViewById(R.id.seekBar);
        numLegs.setOnSeekBarChangeListener(this);

        numPlayer1B = (Button) findViewById(R.id.numPlayer1);
        numPlayer2B = (Button) findViewById(R.id.numPlayer2);
        numPlayer3B= (Button) findViewById(R.id.numPlayer3);
        numPlayer4B = (Button) findViewById(R.id.numPlayer4);
    }


    public void startGame(View view) {
        switch (matchScoreGr.getCheckedRadioButtonId()) {
            case R.id.radioB101:
                gameScore = 101;
                break;
            case R.id.radioB301:
                gameScore = 301;
                break;
            case R.id.radioB501:
                gameScore = 501;
                break;
        }

        nameOne = name1.getText().toString();
        nameTwo = name2.getText().toString();
        nameThree = name3.getText().toString();
        nameFour = name4.getText().toString();

    }

    public void set1Players (View view) {
        numPlayers = 1;
        numPlayer1B.getBackground().clearColorFilter();
        numPlayer2B.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        numPlayer3B.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        numPlayer4B.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        name3.setEnabled(false);
        name4.setEnabled(false);
        name2.setEnabled(false);
        name1.setEnabled(true);
    }


    public void set2Players (View view) {
        numPlayers = 2;
        numPlayer2B.getBackground().clearColorFilter();
        numPlayer1B.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        numPlayer3B.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        numPlayer4B.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        name3.setEnabled(false);
        name4.setEnabled(false);
        name2.setEnabled(true);
        name1.setEnabled(true);
    }


    public void set3Players (View view) {
        numPlayers = 3;
        numPlayer3B.getBackground().clearColorFilter();
        numPlayer2B.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        numPlayer1B.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        numPlayer4B.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        name3.setEnabled(true);
        name4.setEnabled(false);
        name2.setEnabled(true);
        name1.setEnabled(true);
    }


    public void set4Players (View view) {
        numPlayers = 4;
        numPlayer4B.getBackground().clearColorFilter();
        numPlayer2B.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        numPlayer3B.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        numPlayer1B.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        name3.setEnabled(true);
        name4.setEnabled(true);
        name2.setEnabled(true);
        name1.setEnabled(true);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        String temp = ""+progress;
        leg.setText(temp);
        legs = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
