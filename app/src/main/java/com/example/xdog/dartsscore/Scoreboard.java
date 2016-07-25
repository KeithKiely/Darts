package com.example.xdog.dartsscore;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
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
            p2RoundWins,p3RoundWins, p4RoundWins;
    private TextView p1ScoreTV, p2ScoreTV, playerName, player2Name;
    private boolean roundOver = false;

    /*static final String USER_ONE = "user1";
    static final String USER_TWO = "user2";
    static final String CURRENT_PLAYER = "currentPlayer";
    static final String TOTAL_PLAYERS = "totalPlayers";
    static final String ROUND_SCORE = "user1Score";*/
    static final String P1_ROUMD_WINS = "user1RWins";
    static final String P2_ROUND_WINS = "user2RWins";
    static final String NUM_ROUNDS = "numberOfRounds";
    static final String PLAYER_NAME = "winnersName";

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

        p1RoundWins =0;
        p2RoundWins =0;
        p3RoundWins =0;
        p4RoundWins =0;

        newPlayers = getIntent().getParcelableArrayListExtra("Players");
        totalLegs= getIntent().getIntExtra(GameSetting.NUMBER_OF_LEGS,1);
        numLegs = getIntent().getIntExtra(GameSetting.NUMBER_OF_LEGS,1);
        totalPlayers = newPlayers.size();

        list1 = (ListView) findViewById(R.id.listView1);
        list2 = (ListView) findViewById(R.id.listView2);
        p1ScoreTV = (TextView) findViewById(R.id.player1ScoreTV);
        p2ScoreTV = (TextView) findViewById(R.id.player2ScoreTV);

        playerName = (TextView) findViewById((R.id.nameTV));
        playerName.setText(newPlayers.get(0).getPlayerName());

        if (totalPlayers == 2) {
            player2Name = (TextView) findViewById(R.id.p2NameTV);
            player2Name.setText(newPlayers.get(1).getPlayerName());
        }
        gameScore = newPlayers.get(0).getScore();
        String tempScore = gameScore+"";

        p2ScoreTV.setText(tempScore);
        p1ScoreTV.setText(tempScore);

        if (totalPlayers == 1) {
            p1Scores = new ArrayList<>();
            p1Scores.add(newPlayers.get(0).getScore());
            listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1Scores);
            list1.setAdapter(listAdapter);
        }
        if (totalPlayers == 2) {
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
        if (newPlayers.size() == 3) {
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
        Intent intent = new Intent(this, Calculator.class);
        intent.putExtra("name", newPlayers.get(currentPlayer -1).getPlayerName());
        intent.putExtra("currentPlayer", currentPlayer);
        startActivityForResult(intent, 1);
    }
    /*
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        currentPlayer = savedInstanceState.getInt(CURRENT_PLAYER);
        totalPlayers = savedInstanceState.getInt(TOTAL_PLAYERS);
        numLegs = savedInstanceState.getInt(NUM_ROUNDS);
        if (totalPlayers == 1) {
            p1Scores = savedInstanceState.getIntegerArrayList(USER_ONE);
            listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1Scores);
            list1.setAdapter(listAdapter);
            listAdapter.notifyDataSetChanged();
        }
        if (totalPlayers == 2) {
            p1Scores = savedInstanceState.getIntegerArrayList(USER_ONE);
            listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1Scores);
            list1.setAdapter(listAdapter);
            listAdapter.notifyDataSetChanged();
            p2Scores = savedInstanceState.getIntegerArrayList(USER_TWO);
            listAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p2Scores);
            list2.setAdapter(listAdapter2);
            listAdapter2.notifyDataSetChanged();
        }
        gameScore = savedInstanceState.getInt(ROUND_SCORE);
        String p1ScoreTVTxt = ""+newPlayers.get(0).getScore();
        String p2ScoreTVTxt = ""+newPlayers.get(1).getScore();
        p1ScoreTV.setText(p1ScoreTVTxt);
        p2ScoreTV.setText(p2ScoreTVTxt);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(ROUND_SCORE,gameScore);
        savedInstanceState.putInt(NUM_ROUNDS, numLegs);
        savedInstanceState.putInt(CURRENT_PLAYER, currentPlayer);
        savedInstanceState.putInt(TOTAL_PLAYERS, totalPlayers);
        if (totalPlayers == 1) {
            savedInstanceState.putSerializable(USER_ONE, p1Scores);
        }
        if (totalPlayers == 2) {
            savedInstanceState.putSerializable(USER_TWO, p2Scores);
        }

        //Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            int result = 0;
            if (resultCode == Activity.RESULT_OK) {
                result = data.getIntExtra("score", 0);
                if (currentPlayer == 1) {
                    p1Scores.add(result);
                    listAdapter.notifyDataSetChanged();
                    newPlayers.get(0).subtractScore(result);
                    String p1Score = ""+newPlayers.get(0).getScore();
                    p1ScoreTV.setText(p1Score);
                }//End of player 1
            } if (resultCode == Activity.RESULT_CANCELED) {
            }
            if (currentPlayer == 2){
                p2Scores.add(result);
                listAdapter2.notifyDataSetChanged();
                newPlayers.get(1).subtractScore(result);
                String score = ""+newPlayers.get(1).getScore();
                p2ScoreTV.setText(score);
                String currentPlayer = " " +newPlayers.get(0).getPlayerName();
                playerName.setText(currentPlayer);
            }//End of player 2
            for (int i = 0; i < newPlayers.size(); i++) {
                ////////////////////////////////////////////
                // Round Over
                if (newPlayers.get(i).getScore() < 1){
                    newPlayers.get(i).setScore(gameScore);
                    numLegs--;
                    roundOver = true;
                    if (i == 0) {
                        p1RoundWins++;
                    }if (i == 1) {
                        p2RoundWins++;
                    }
                    String temp = gameScore+"";
                    p1ScoreTV.setText(temp);
                    p1Scores.clear();
                    if (newPlayers.size() == 2) {
                        p2ScoreTV.setText(temp);
                        p2Scores.clear();
                        listAdapter2.notifyDataSetChanged();
                    }
                    listAdapter.notifyDataSetChanged();
                    Context context = getApplicationContext();
                    CharSequence message = getResources().getString(R.string.round_over);
                    //int duration = Toast.LENGTH_SHORT;
                    final Toast toastBasic = Toast.makeText(context,message, Toast.LENGTH_LONG);
                    toastBasic.show();
                }
                //////////////////////////////////////////////
                // If game over
                if ( numLegs <= 0) {
                    bundle = new Bundle();
                    bundle.putInt(P1_ROUMD_WINS, p1RoundWins);
                    bundle.putInt(P2_ROUND_WINS, p2RoundWins);
                    bundle.putInt(NUM_ROUNDS, totalLegs);
                    bundle.putString(PLAYER_NAME, newPlayers.get(currentPlayer -1).getPlayerName());

                    FragmentManager fm = getFragmentManager();
                    GamerOverDialog dialogFragment = new GamerOverDialog ();
                    dialogFragment.setArguments(bundle);
                    dialogFragment.show(fm, "Game Over");
                }
            }
            if(currentPlayer == totalPlayers && !roundOver) {
                currentPlayer = 1;
            } else {
                currentPlayer++;
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
}
