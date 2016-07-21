package com.example.xdog.dartsscore;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
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
    private int gameScore, totalPlayers, numLegs, p1RoundWins, p2RoundWins,p3RoundWins, p4RoundWins;
    private TextView p1ScoreTV, p2ScoreTV, playerName, player2Name;

    static final String USER_ONE = "user1";
    static final String USER_TWO = "user2";
    static final String ROUND_SCORE = "user1Score";
    static final String P1_ROUDN_WINS = "user1RWins";
    static final String P2_ROUDN_WINS = "user1RWins";
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
        p1RoundWins =0;
        p2RoundWins =0;
        p3RoundWins =0;
        p4RoundWins =0;

        newPlayers = getIntent().getParcelableArrayListExtra("Players");
        numLegs = getIntent().getIntExtra(GameSetting.NUMBER_OF_LEGS,1);
        totalPlayers = newPlayers.size();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        list1 = (ListView) findViewById(R.id.listView1);
        list2 = (ListView) findViewById(R.id.listView2);
        p1ScoreTV = (TextView) findViewById(R.id.player1ScoreTV);
        p2ScoreTV = (TextView) findViewById(R.id.player2ScoreTV);
        playerName = (TextView) findViewById((R.id.nameTV));
        player2Name = (TextView) findViewById(R.id.p2NameTV);

        playerName.setText(newPlayers.get(0).getPlayerName());
        if (newPlayers.size() == 2) {
            player2Name.setText(newPlayers.get(1).getPlayerName());
        }
        gameScore = newPlayers.get(0).getScore();
        String tempScore = gameScore+"";

        p2ScoreTV.setText(tempScore);
        p1ScoreTV.setText(tempScore);

        if (newPlayers.size() == 1) {
            p1Scores = new ArrayList<>();
            p1Scores.add(newPlayers.get(0).getScore());
            listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1Scores);
            list1.setAdapter(listAdapter);
        }
        if (newPlayers.size() == 2) {
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
        if (newPlayers.size() == 2) {

        }
        if (newPlayers.size() == 3) {

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

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        p1Scores = savedInstanceState.getIntegerArrayList(USER_ONE);
        p2Scores = savedInstanceState.getIntegerArrayList(USER_TWO);
        gameScore = savedInstanceState.getInt(ROUND_SCORE);
        String p1ScoreTVTxt = ""+newPlayers.get(0).getScore();
        String p2ScoreTVTxt = ""+newPlayers.get(1).getScore();
        p1ScoreTV.setText(p1ScoreTVTxt);
        p2ScoreTV.setText(p2ScoreTVTxt);
        currentPlayer = savedInstanceState.getInt("currentPlayer");
        numLegs = savedInstanceState.getInt(NUM_ROUNDS);

        listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1Scores);
        listAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p2Scores);
        list1.setAdapter(listAdapter);
        list2.setAdapter(listAdapter2);
        listAdapter.notifyDataSetChanged();
        listAdapter2.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(USER_ONE, p1Scores);
        savedInstanceState.putSerializable(USER_TWO, p2Scores);
        savedInstanceState.putInt(ROUND_SCORE,gameScore);
        savedInstanceState.putInt(NUM_ROUNDS, numLegs);
        //Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            int result = data.getIntExtra("score", 0);
            if (resultCode == Activity.RESULT_OK) {
                if (currentPlayer == 1) {
                    p1Scores.add(result);
                    listAdapter.notifyDataSetChanged();
                    newPlayers.get(0).subtractScore(result);
                    String p1Score = ""+newPlayers.get(0).getScore();
                    p1ScoreTV.setText(p1Score);
                }//End of player 1
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
                //Round Over
                if (newPlayers.get(i).getScore() < 1){
                    newPlayers.get(i).setScore(gameScore);
                    numLegs--;
                    currentPlayer = 1; //ToDo Check this later (make sure that player resets to player 1 at start of round)
                    if (i == 0) {
                        p1RoundWins++;
                        Log.i("Scoreboard: "," P1 has won " +p1RoundWins + " rounds");
                    }if (i == 1) {
                        p2RoundWins++;
                        Log.i("Scoreboard: "," P2 has won " +p2RoundWins + " rounds");
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
                }
                //If game over
                if (newPlayers.get(i).getScore() <= 0 && numLegs <= 0) {
                    p1RoundWins ++;
                    bundle = new Bundle();
                    bundle.putInt(P1_ROUDN_WINS, p1RoundWins);
                    bundle.putInt(P2_ROUDN_WINS, p2RoundWins);
                    bundle.putInt(NUM_ROUNDS, numLegs);
                    bundle.putString(PLAYER_NAME, newPlayers.get(currentPlayer -1).getPlayerName());

                    FragmentManager fm = getFragmentManager();
                    GamerOverDialog dialogFragment = new GamerOverDialog ();
                    dialogFragment.setArguments(bundle);
                    dialogFragment.show(fm, "Game Over");
                }
            }
            if(currentPlayer == totalPlayers) {
                currentPlayer = 1;
            } else {
                currentPlayer++;
            }
        }
    }//onActivityResult

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Scoreboard Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.xdog.dartsscore/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Scoreboard Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.xdog.dartsscore/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
