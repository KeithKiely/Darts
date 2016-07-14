package com.example.xdog.dartsscore;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
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

        p2Score.setText(""+gameScore);
        p1Score.setText(""+gameScore);
        playerName.setText(player.getPlayerName());

        p1scores = new ArrayList<Integer>();
        p1scores.add(player.getScore());

        p2scores = new ArrayList<Integer>();
        p2scores.add(player1.getScore());

        listAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,p1scores);
        listAdapter2 = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,p2scores);
        list1.setAdapter(listAdapter);
        list2.setAdapter(listAdapter2);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void openCalc(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 1);
        playerName.setText(player1.getPlayerName());
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
                    Log.i("Player 1 score:",  " " +player.getScore());
                    player.setScore(result);
                    p1Score.setText(""+player.getScore());
                }
                if (currentPlayer == 2){
                    p2scores.add(result);
                    listAdapter2.notifyDataSetChanged();
                    player1.setScore(result);
                    p2Score.setText(""+player1.getScore());
                    currentPlayer = 0;
                    playerName.setText(player.getPlayerName());
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

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
