package com.example.xdog.dartsscore;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private ArrayList<Integer> p1scores;
    private ArrayList<Integer> p2scores;
    private ListView list1, list2;
    private ArrayAdapter listAdapter, listAdapter2;
    private int currentPlayer = 0;
    private int gameScore;
    private TextView p1Score, p2Score, playerName;
    private Player player,player1;
    static final String USER_ONE = "user1";
    static final String USER_TWO = "user2";
    static final String USER_ONE_Score = "user1Score";
    static final String USER_TWO_Score = "user2Score";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gameScore = 101;
        player = new Player("Tom", gameScore, 1);
        player1 = new Player("Barry", gameScore, 2);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        list1 = (ListView) findViewById(R.id.listView1);
        list2 = (ListView) findViewById(R.id.listView2);
        p1Score = (TextView) findViewById(R.id.player1Score);
        p2Score = (TextView) findViewById(R.id.player2Score);
        playerName = (TextView) findViewById((R.id.nameTV));
        String goal = "" + gameScore;
        p2Score.setText(goal);
        p1Score.setText(goal);
        String playerNameTxt = getString(R.string.current_player) + " " +player.getPlayerName();
        playerName.setText(playerNameTxt);

        p1scores = new ArrayList<>();
        p1scores.add(player.getScore());

        p2scores = new ArrayList<>();
        p2scores.add(player1.getScore());

        listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1scores);
        listAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p2scores);
        list1.setAdapter(listAdapter);
        list2.setAdapter(listAdapter2);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void openCalc(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 1);
        String playerNameTxt = getString(R.string.current_player) + " " +player1.getPlayerName();
        playerName.setText(playerNameTxt);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        p1scores = savedInstanceState.getIntegerArrayList(USER_ONE);
        p2scores = savedInstanceState.getIntegerArrayList(USER_TWO);
        player.setScore(savedInstanceState.getInt(USER_ONE_Score));
        player1.setScore(savedInstanceState.getInt(USER_TWO_Score));
        String p1ScoreTxt = ""+player.getScore();
        String p2ScoreTxt = ""+player1.getScore();
        p1Score.setText(p1ScoreTxt);
        p2Score.setText(p2ScoreTxt);


        listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p1scores);
        listAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,p2scores);
        list1.setAdapter(listAdapter);
        list2.setAdapter(listAdapter2);
        listAdapter.notifyDataSetChanged();
        listAdapter2.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(USER_ONE, p1scores);
        savedInstanceState.putSerializable(USER_TWO, p2scores);
        savedInstanceState.putInt(USER_ONE_Score,player.getScore());
        savedInstanceState.putInt(USER_TWO_Score,player1.getScore());
        //Always call the superclass so it can save the view hierarchy state

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                currentPlayer++;
                int result = data.getIntExtra("score", 0);
                if (currentPlayer == 1) {
                    p1scores.add(result);
                    listAdapter.notifyDataSetChanged();
                    player.subtractScore(result);
                    String p1ScoreTxt = ""+player.getScore();
                    p1Score.setText(p1ScoreTxt);
                    if (player.getScore() <= 0) {
                        //// TODO: 15/07/2016 End game when score is bellow 0 (Start next round or quit to menu)
                    }
                }
                if (currentPlayer == 2){
                    p2scores.add(result);
                    listAdapter2.notifyDataSetChanged();
                    player1.subtractScore(result);
                    String p2ScoreTxt = ""+player1.getScore();
                    p2Score.setText(p2ScoreTxt);
                    currentPlayer = 0;
                    String currentPlayer = getString(R.string.current_player) + " " +player.getPlayerName();
                    playerName.setText(currentPlayer);
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
