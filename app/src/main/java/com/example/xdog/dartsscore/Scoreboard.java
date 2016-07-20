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
    private ArrayList<Integer> p1ScoreTVs;
    private ArrayList<Integer> p2ScoreTVs;
    private ArrayList<Player> newPlayers;
    private ListView list1, list2;
    private ArrayAdapter listAdapter, listAdapter2;
    private int currentPlayer = 1;
    private int gameScore, totalPlayers, numLegs;
    private TextView p1ScoreTV, p2ScoreTV, playerName, player2Name;
    static final String USER_ONE = "user1";
    static final String USER_TWO = "user2";
    static final String ROUND_SCORE = "user1Score";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        newPlayers = getIntent().getParcelableArrayListExtra("Players");
        numLegs = getIntent().getIntExtra(GameSetting.NUMBER_OF_LEGS,1);
        Log.i("Num legs: ", ""+numLegs);
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
        player2Name.setText(newPlayers.get(1).getPlayerName());


        gameScore = newPlayers.get(0).getScore();
        String tempScore = gameScore+"";

        p2ScoreTV.setText(tempScore);
        p1ScoreTV.setText(tempScore);

        if (newPlayers.size() == 1) {
            p1ScoreTVs = new ArrayList<>();
            p1ScoreTVs.add(newPlayers.get(0).getScore());
            listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1ScoreTVs);
            list1.setAdapter(listAdapter);
        }
        if (newPlayers.size() == 2) {
            //player one
            p1ScoreTVs = new ArrayList<>();
            p1ScoreTVs.add(newPlayers.get(0).getScore());
            //player two
            p2ScoreTVs = new ArrayList<>();
            p2ScoreTVs.add(newPlayers.get(1).getScore());

            listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1ScoreTVs);
            listAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p2ScoreTVs);
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
        p1ScoreTVs = savedInstanceState.getIntegerArrayList(USER_ONE);
        p2ScoreTVs = savedInstanceState.getIntegerArrayList(USER_TWO);
        gameScore = savedInstanceState.getInt(ROUND_SCORE);
        String p1ScoreTVTxt = ""+newPlayers.get(0).getScore();
        String p2ScoreTVTxt = ""+newPlayers.get(1).getScore();
        p1ScoreTV.setText(p1ScoreTVTxt);
        p2ScoreTV.setText(p2ScoreTVTxt);
        currentPlayer = savedInstanceState.getInt("currentPlayer");

        listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1ScoreTVs);
        listAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p2ScoreTVs);
        list1.setAdapter(listAdapter);
        list2.setAdapter(listAdapter2);
        listAdapter.notifyDataSetChanged();
        listAdapter2.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(USER_ONE, p1ScoreTVs);
        savedInstanceState.putSerializable(USER_TWO, p2ScoreTVs);
        savedInstanceState.putInt(ROUND_SCORE,gameScore);
        //Always call the superclass so it can save the view hierarchy state

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                int result = data.getIntExtra("score", 0);
                if (currentPlayer == 1) {
                    p1ScoreTVs.add(result);
                    listAdapter.notifyDataSetChanged();
                    newPlayers.get(0).subtractScore(result);
                    String p1ScoreTVTxt = ""+newPlayers.get(0).getScore();
                    p1ScoreTV.setText(p1ScoreTVTxt);

                    for (int i = 0; i < newPlayers.size(); i++) {
                        if (newPlayers.get(i).getScore() <= 0 && numLegs == 0) {
                            //// TODO: 15/07/2016 End game when score is bellow 0 (Start next round or quit to menu)
                            FragmentManager fm = getFragmentManager();
                            GamerOverDialog dialogFragment = new GamerOverDialog ();
                            dialogFragment.show(fm, "Game Over");
                        }
                        if (newPlayers.get(i).getScore() <= 0 && numLegs > 1) {
                            Log.i("New game score: ", gameScore + "");
                            String temp = gameScore+"";
                            p1ScoreTV.setText(temp);
                            p2ScoreTV.setText(temp);
                            p1ScoreTVs.clear();
                            p2ScoreTVs.clear();
                            listAdapter.notifyDataSetChanged();
                            listAdapter2.notifyDataSetChanged();
                            numLegs--;
                            newPlayers.get(i).setRoundWins(1);
                        }
                    }
                }
                if (currentPlayer == 2){
                    p2ScoreTVs.add(result);
                    listAdapter2.notifyDataSetChanged();
                    newPlayers.get(1).subtractScore(result);
                    String p2ScoreTVTxt = ""+newPlayers.get(1).getScore();
                    p2ScoreTV.setText(p2ScoreTVTxt);
                    String currentPlayer = " " +newPlayers.get(0).getPlayerName();
                    playerName.setText(currentPlayer);
                }
                if(currentPlayer == totalPlayers) {
                    currentPlayer = 1;
                } else {
                    currentPlayer++;
                }
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
