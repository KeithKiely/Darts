package com.example.xdog.dartsscore;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class Scoreboard extends AppCompatActivity {
    private ArrayList<Integer> p1Scores;
    private ArrayList<Integer> p2Scores;
    private ArrayList<Player> newPlayers;
    private ListView list1, list2;
    private ArrayAdapter listAdapter, listAdapter2;
    private int currentPlayer = 1;
    private int gameScore, totalPlayers, numLegs,totalLegs, p1RoundWins,
            p2RoundWins, currentRound, calcResult;
    private TextView playerName, player2Name, currentScoreP1, currentScoreP2;
    private EditText scoreET;
    private boolean roundOver = false;
    private float x1, y1, x2, y2;
    private ColorStateList oldColors;
    static final String TOTAL_PLAYERS = "totalPlayers";
    static final String P1_ROUND_WINS = "user1RWins";
    static final String P2_ROUND_WINS = "user2RWins";
    static final String NUM_ROUNDS = "numberOfRounds";
    static final String PLAYER_NAME = "winnersName";
    static final String PLAYER_1_NAME = "player1Name";
    static final String PLAYER_2_NAME = "player2Name";
    static final String CURRENT_ROUND = "currentRound";

    static Bundle bundle;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if (myToolbar != null) {
            setSupportActionBar(myToolbar);
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        }
        currentRound = 1;
        p1RoundWins =0;
        p2RoundWins =0;

        newPlayers = getIntent().getParcelableArrayListExtra("Players");
        totalLegs= getIntent().getIntExtra(GameSetting.NUMBER_OF_LEGS,1);
        numLegs = getIntent().getIntExtra(GameSetting.NUMBER_OF_LEGS,1);
        totalPlayers = newPlayers.size();
        gameScore = newPlayers.get(0).getScore();
        list1 = (ListView) findViewById(R.id.listView1);
        list2 = (ListView) findViewById(R.id.listView2);

        playerName = (TextView) findViewById((R.id.nameTV));
        if (playerName != null) {
            oldColors = playerName.getTextColors();
        }
        currentScoreP1 = (TextView) findViewById((R.id.currentScoreP1));
        currentScoreP2 = (TextView) findViewById((R.id.currentScoreP2));

        playerName.setText(newPlayers.get(0).getPlayerName());
        String temp = ""+gameScore;
        currentScoreP1.setText(temp);
        playerName.setTextColor(Color.parseColor("#308070"));

        scoreET = (EditText) findViewById(R.id.scoreET);
        scoreET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String tempScore = scoreET.getText().toString().trim();
                    if (!tempScore.equals("")) {
                        int tempPlayerScore = Integer.parseInt(tempScore);
                        if (tempPlayerScore <=180 && tempPlayerScore >= 0) {
                            scoreManagement(false);
                            roundManagement();
                            scoreET.setText("");
                        } else {
                            Context context = getApplicationContext();
                            CharSequence message = getResources().getString(R.string.invalid_entry);
                            //int duration = Toast.LENGTH_SHORT;
                            final Toast toastBasic = Toast.makeText(context, message, Toast.LENGTH_LONG);
                            toastBasic.show();
                            scoreET.setText("");
                        }
                    }
                }
                return false;
            }
        });

        if (totalPlayers == 1) {
            currentScoreP2.setVisibility(View.INVISIBLE);
            p1Scores = new ArrayList<>();
            p1Scores.add(newPlayers.get(0).getScore());
            listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1Scores);
            list1.setAdapter(listAdapter);
        }
        if (totalPlayers == 2) {
            String temp1 = ""+gameScore;
            currentScoreP2.setText(temp1);
            player2Name = (TextView) findViewById(R.id.p2NameTV);
            player2Name.setText(newPlayers.get(1).getPlayerName());
            //player one
            p1Scores = new ArrayList<>();
            p1Scores.add(newPlayers.get(0).getScore());
            //player two
            p2Scores = new ArrayList<>();
            p2Scores.add(newPlayers.get(1).getScore());

            listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1Scores);
            listAdapter2 = new ArrayAdapter<>(this,R.layout.list_text_layout,p2Scores);
            list1.setAdapter(listAdapter);
            list2.setAdapter(listAdapter2);
        }
        if (newPlayers.size() == 3) {
            //player one
            p1Scores = new ArrayList<>();
            p1Scores.add(newPlayers.get(0).getScore());
            //player two
            p2Scores = new ArrayList<>();
            p2Scores.add(newPlayers.get(1).getScore());

            listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1Scores);
            listAdapter2 = new ArrayAdapter<>(this,R.layout.list_text_layout,p2Scores);
            list1.setAdapter(listAdapter);
            list2.setAdapter(listAdapter2);
        }
        if (newPlayers.size() == 4) {
            //player one
            p1Scores = new ArrayList<>();
            p1Scores.add(newPlayers.get(0).getScore());
            //player two
            p2Scores = new ArrayList<>();
            p2Scores.add(newPlayers.get(1).getScore());

            listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1Scores);
            listAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p2Scores);
            list1.setAdapter(listAdapter);
            list2.setAdapter(listAdapter2);
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void openCalc(View view) {
        String tempScore = scoreET.getText().toString().trim();
        if (tempScore.equals("")) {
            Intent intent = new Intent(this, Calculator2.class);
            intent.putExtra("name", newPlayers.get(currentPlayer - 1).getPlayerName());
            intent.putExtra("currentPlayer", currentPlayer);
            startActivityForResult(intent, 1);
        } else {
            int tempPlayerScore = Integer.parseInt(tempScore);
            if (tempPlayerScore <= 180 && tempPlayerScore >= 0) {
                scoreManagement(false);
                roundManagement();
            }
        }
    }

    public void scoreManagement(boolean fromCalc){
        // do your stuff here
        int result;
        if (fromCalc == true) {
            result = calcResult;
        } else {
            result = Integer.parseInt(scoreET.getText().toString().trim());
        }
        if (currentPlayer == 1) {
            playerName.setTextColor(oldColors);
            if (totalPlayers == 2) {
                player2Name.setTextColor(Color.parseColor("#308070"));
            } if (totalPlayers == 1){
                playerName.setTextColor(Color.parseColor("#308070"));
            }
            p1Scores.add(result);
            int currentScore = 0;
            for (int i = 1; i < p1Scores.size(); i++) {
                //Log.i("Scoreboard: ", " "+score);
                currentScore += p1Scores.get(i);
            }
            currentScore = gameScore - currentScore;
            String temp = currentScore+"";
            currentScoreP1.setText(temp);
            listAdapter.notifyDataSetChanged();
            list1.setSelection(listAdapter.getCount() - 1);
            newPlayers.get(0).subtractScore(result);
        }//End of player 1
        if (currentPlayer == 2) {
            playerName.setTextColor(Color.parseColor("#308070"));
            player2Name.setTextColor(oldColors);
            p2Scores.add(result);
            int currentScore = 0;
            for (int i = 1; i < p2Scores.size(); i++) {
                //Log.i("Scoreboard: ", " "+score);
                currentScore += p2Scores.get(i);
            }
            currentScore = gameScore - currentScore;
            String temp = currentScore+"";
            currentScoreP2.setText(temp);
            listAdapter2.notifyDataSetChanged();
            list2.setSelection(listAdapter2.getCount() - 1);
            newPlayers.get(1).subtractScore(result);
            String currentPlayer = " " + newPlayers.get(0).getPlayerName();
            playerName.setText(currentPlayer);
        }//End of player 2
    }

    public void roundManagement(){
        for (int i = 0; i < newPlayers.size(); i++) {
            ////////////////////////////////////////////
            // Round Over
            if (newPlayers.get(i).getScore() < 1) {
                currentRound++;
                String temp = ""+gameScore;
                currentScoreP1.setText(temp);
                currentScoreP2.setText(temp);
                newPlayers.get(i).setScore(gameScore);
                numLegs--;
                roundOver = true;
                currentPlayer = 1;
                if (i == 0) {
                    p1RoundWins++;
                }
                if (i == 1) {
                    p2RoundWins++;
                }
                p1Scores.clear();
                p1Scores.add(gameScore);
                if (newPlayers.size() == 2) {
                    p2Scores.clear();
                    p2Scores.add(gameScore);
                    listAdapter2.notifyDataSetChanged();
                }
                player2Name.setTextColor(oldColors);
                playerName.setTextColor(Color.parseColor("#308070"));

                listAdapter.notifyDataSetChanged();
                Context context = getApplicationContext();
                CharSequence message = getResources().getString(R.string.round_over);
                //int duration = Toast.LENGTH_SHORT;
                final Toast toastBasic = Toast.makeText(context, message, Toast.LENGTH_LONG);
                toastBasic.show();
            }
            //////////////////////////////////////////////
            // If game over
            if (numLegs <= 0) {
                bundle = new Bundle();
                bundle.putInt(P1_ROUND_WINS, p1RoundWins);
                bundle.putInt(P2_ROUND_WINS, p2RoundWins);
                bundle.putInt(NUM_ROUNDS, totalLegs);
                Log.i("Scoreboard ", " current player" + currentPlayer);
                if (currentPlayer == 1)
                    bundle.putString(PLAYER_NAME, newPlayers.get(0).getPlayerName());
                if (currentPlayer == 2)
                    bundle.putString(PLAYER_NAME, newPlayers.get(1).getPlayerName());

                FragmentManager fm = getFragmentManager();
                GamerOverDialog dialogFragment = new GamerOverDialog();
                dialogFragment.setArguments(bundle);
                dialogFragment.show(fm, "Game Over");
            }
        }
        if (roundOver) {
            currentPlayer = totalPlayers;
            roundOver = false;
        }
        if (currentPlayer == totalPlayers) {
            currentPlayer = 1;
        } else {
            currentPlayer++;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            boolean calcCancelled = false;
            if (resultCode == Activity.RESULT_OK) {
                calcResult = data.getIntExtra("score", 0);

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                calcCancelled = true;
            }
            if (!calcCancelled) {
                scoreManagement(true);
                roundManagement();
            }
        }
    }//onActivityResult

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
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void openInfo(View view) {
        /*bundle = new Bundle();
        bundle.putInt(P1_ROUND_WINS, p1RoundWins);
        bundle.putInt(P2_ROUND_WINS, p2RoundWins);
        bundle.putInt(NUM_ROUNDS, totalLegs);
        bundle.putInt(TOTAL_PLAYERS, totalPlayers);
        bundle.putInt(CURRENT_ROUND, currentRound);
        bundle.putString(PLAYER_1_NAME, newPlayers.get(0).getPlayerName());
        if (totalPlayers == 2)
        bundle.putString(PLAYER_2_NAME, newPlayers.get(1).getPlayerName());*/

        FragmentManager fm = getFragmentManager();
        UsageFragment usageFragment = new UsageFragment();
        //usageFragment.setArguments(bundle);
        usageFragment.show(fm, getResources().getString(R.string.how_to));
    }

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        int action = MotionEventCompat.getActionMasked(touchevent);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case (MotionEvent.ACTION_MOVE):
                return true;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();

                //if left to right sweep event on screen
                if (x1 < x2) {
                    Intent intent = new Intent(this, Calculator2.class);
                    intent.putExtra("name", newPlayers.get(currentPlayer -1).getPlayerName());
                    intent.putExtra("currentPlayer", currentPlayer);
                    startActivityForResult(intent, 1);
                }

                // if right to left sweep event on screen
                if (x1 > x2) {
                    bundle = new Bundle();
                    bundle.putInt(P1_ROUND_WINS, p1RoundWins);
                    bundle.putInt(P2_ROUND_WINS, p2RoundWins);
                    bundle.putInt(NUM_ROUNDS, totalLegs);
                    bundle.putInt(TOTAL_PLAYERS, totalPlayers);
                    bundle.putInt(CURRENT_ROUND, currentRound);
                    bundle.putString(PLAYER_1_NAME, newPlayers.get(0).getPlayerName());
                    if (totalPlayers == 2)
                        bundle.putString(PLAYER_2_NAME, newPlayers.get(1).getPlayerName());

                    FragmentManager fm = getFragmentManager();
                    InfoFragment infoFragment = new InfoFragment ();
                    infoFragment.setArguments(bundle);
                    infoFragment.show(fm, getResources().getString(R.string.game_over));
                }
                break;

            case (MotionEvent.ACTION_CANCEL):
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                return true;
            default:
                return super.onTouchEvent(touchevent);
        }
        return false;
    }
}
