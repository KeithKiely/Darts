package com.example.xdog.dartsscore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * Created by Keith Kiely on 22/08/2016.
 * GameSetting class: Initial setup for darts game
 */
public class GameSetting extends AppCompatActivity implements OnSeekBarChangeListener{
    private RadioGroup matchScoreGr;
    private EditText name1, name2, name3, name4;
    private TextView leg;
    private Button numPlayer1B, numPlayer2B, numPlayer3B, numPlayer4B;

    private boolean name1Set, name2Set, name3Set, name4Set;
    private int gameScore;
    private int legs;

    private ArrayList<Player> players;

    public static final String NUMBER_OF_LEGS = "numLegs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        players = new ArrayList<>();
        name1Set = true;
        name2Set = false;
        name3Set = false;
        name4Set = false;

        matchScoreGr = (RadioGroup) findViewById(R.id.gameType);

        name1 = (EditText) findViewById(R.id.name1ET);
        name2 = (EditText) findViewById(R.id.name2ET);
        name3 = (EditText) findViewById(R.id.name3ET);
        name4 = (EditText) findViewById(R.id.name4ET);

        name2.setEnabled(false);
        name3.setEnabled(false);
        name4.setEnabled(false);
        name3.setVisibility(View.INVISIBLE);
        name4.setVisibility(View.INVISIBLE);

        leg = (TextView) findViewById(R.id.legsTV);

        SeekBar numLegs = (SeekBar) findViewById(R.id.seekBar);
        if (numLegs != null) {
            numLegs.setOnSeekBarChangeListener(this);
        }

        numPlayer1B = (Button) findViewById(R.id.numPlayer1);
        numPlayer2B = (Button) findViewById(R.id.numPlayer2);
        numPlayer3B= (Button) findViewById(R.id.numPlayer3);
        numPlayer4B = (Button) findViewById(R.id.numPlayer4);
        numPlayer1B.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        numPlayer3B.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        numPlayer4B.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        numPlayer3B.setEnabled(false);
        numPlayer4B.setEnabled(false);
        numPlayer4B.setVisibility(View.INVISIBLE);
        numPlayer3B.setVisibility(View.INVISIBLE);
    }

    public void startGame(View view) {
        players.clear();
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
        Player player;
        if (name1Set) {
            String nameOne = name1.getText().toString().trim();
            if (nameOne.equals("")) {
                nameOne = "Player 1";
            }
            player = new Player(nameOne, gameScore, 1);
            players.add(player);
        }
        if (name2Set){
            String nameOne = name1.getText().toString().trim();
            String nameTwo = name2.getText().toString().trim();
            if (nameOne.equals("")) {
                nameOne = "Player 1";
            }if (nameTwo.equals("")) {
                nameTwo = "Player 2";
            }
            player = new Player(nameOne, gameScore, 1);
            Player player1 = new Player(nameTwo, gameScore, 2);
            players.add(player);
            players.add(player1);
        }
        if (name3Set) {
            String nameThree = name3.getText().toString().trim();
            player = new Player(nameThree, gameScore, 1);
            players.add(player);
        }
        if (name4Set) {
            String nameFour = name4.getText().toString().trim();
            player = new Player(nameFour, gameScore, 1);
            players.add(player);
        }
        if (legs <= 0) {
            Context context1 = getApplicationContext();
            CharSequence message = getResources().getString(R.string.number_legs_not_set);
            final Toast toastBasic = Toast.makeText(context1,message, Toast.LENGTH_SHORT);
            toastBasic.show();
        } else {
            Intent intent = new Intent(this, Scoreboard.class);
            intent.putParcelableArrayListExtra("Players", players);
            intent.putExtra(NUMBER_OF_LEGS, legs);
            startActivity(intent);
        }
    }

    public void set1Players (View view) {
        name1Set = true;
        name2Set = false;
        name3Set = false;
        name4Set = false;
        numPlayer1B.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        numPlayer2B.getBackground().clearColorFilter();
        numPlayer3B.getBackground().clearColorFilter();
        numPlayer4B.getBackground().clearColorFilter();
        name3.setEnabled(false);
        name4.setEnabled(false);
        name2.setEnabled(false);
        name1.setEnabled(true);
    }


    public void set2Players (View view) {
        name2Set = true;
        name1Set = false;
        name3Set = false;
        name4Set = false;
        numPlayer2B.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        numPlayer1B.getBackground().clearColorFilter();
        numPlayer3B.getBackground().clearColorFilter();
        numPlayer4B.getBackground().clearColorFilter();
        name3.setEnabled(false);
        name4.setEnabled(false);
        name2.setEnabled(true);
        name1.setEnabled(true);
    }


    public void set3Players (View view) {
        name3Set = true;
        name2Set = false;
        name1Set = false;
        name4Set = false;
        numPlayer3B.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        numPlayer2B.getBackground().clearColorFilter();
        numPlayer1B.getBackground().clearColorFilter();
        numPlayer4B.getBackground().clearColorFilter();
        name3.setEnabled(true);
        name4.setEnabled(false);
        name2.setEnabled(true);
        name1.setEnabled(true);
    }


    public void set4Players (View view) {
        name2Set = false;
        name3Set = false;
        name1Set = false;
        name4Set = true;
        numPlayer4B.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        numPlayer2B.getBackground().clearColorFilter();
        numPlayer3B.getBackground().clearColorFilter();
        numPlayer1B.getBackground().clearColorFilter();
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
