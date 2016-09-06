package com.example.xdog.dartsscore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class TournamentActivity extends AppCompatActivity implements TournamentSetupFragment.OnFragmentInteractionListener,
        EnterUsername.OnFragmentInteractionListener, GameSetUpFragment.OnFragmentInteractionListener {
    private ArrayList<Player> players;
    private ArrayList<Player> winners;
    private Pair pair;
    private int numLegs, gameScore;
    private boolean pToNextRound = false;
    private boolean roundOver = false;
    private boolean tournamentStarted = false;
    private Player tempPlayerHolder;

    public TournamentActivity() {
    }

    public void roundManagement(ArrayList<Player> people) {
        Random random = new Random();
        int passedToNextRound;
        if (pToNextRound) {
            people.add(tempPlayerHolder);
            pToNextRound = false;
        }
        if (people.size() > 1) {
            if ((people.size() % 2) != 0) { // Checks to see if there's an even number of people, if not moves one forward to next round
                pToNextRound = true;
                passedToNextRound = random.nextInt(people.size());
                tempPlayerHolder = people.get(passedToNextRound);
                Iterator<Player> itr = people.iterator();
                while (itr.hasNext()) {
                    Player tempPlayer = itr.next();
                    if (tempPlayer.getPlayerNum() == (passedToNextRound )) {
                        itr.remove();
                    }
                }
            }
            playerMatchUp(people);
        } else {
            //todo Declare winner
        }
    }

    //
    public void playerMatchUp(ArrayList<Player> people) {
        tournamentStarted = true;
        Collections.shuffle(people);
        for (int i = 0; i < (people.size()-1) ; i+=2) {
            ArrayList<Player > opponents = new ArrayList<>();
            opponents.add(people.get(i));
            opponents.add(people.get((i + 1)));

            Intent intent = new Intent(this, Scoreboard.class);
            intent.putParcelableArrayListExtra(Constant.PLAYERS, opponents);
            intent.putExtra(Constant.NUMBER_OF_LEGS, numLegs);
            intent.putExtra(Constant.IS_TOURNAMENT, true);
            startActivityForResult(intent, 1);
            Log.i("TourActivity: ", " current index: " + i + " people Size: " + (people.size() -1));
            if (i == (people.size() / 2)) {
                roundOver = true;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);
        players = new ArrayList<>();
        winners = new ArrayList<>();
        if (findViewById(R.id.fragmentContainer) != null) {
            if (savedInstanceState != null) {
                return;
            }
            // Create a new Fragment to be placed in the activity layout
            TournamentSetupFragment firstFragment = new TournamentSetupFragment();
            GameSetUpFragment gameSetUpFragment = new GameSetUpFragment();
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());
            gameSetUpFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, firstFragment, "tSetUp").commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, gameSetUpFragment, "gSetUp").commit();
        }
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override //GameSetup interface
    public void onTournamentSetupFragmentInteraction(String numPlayers) {
        GameSetUpFragment gameSetUpFragment = (GameSetUpFragment) getSupportFragmentManager().findFragmentByTag("gSetUp");
        gameSetUpFragment.setNumPlayers(numPlayers);
    }

    @Override
    public void sendPlayerNames(Bundle bundle) {
        players = bundle.getParcelableArrayList(EnterUsername.PLAYER_ARRAY);
        numLegs = bundle.getInt(EnterUsername.NUMBER_OF_LEGS);
        //Sets participants and calls method to set up tournament
        roundManagement(players);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (tournamentStarted) {
            this.players.clear();
        }
    }

    @Override //GameSetup Fragment
    public void onGameSetupFragmentInteraction(int score) {
        gameScore = score;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 ) {
            if (resultCode == RESULT_OK) {
                String winnersName = data.getStringExtra(Constant.PLAYER_NAME);
                for (Player temp: players) {
                    if (temp.getPlayerName().equals(winnersName)) {
                        winners.add(temp);
                    }
                }
                Log.i("TourActivity:", " winner size " +winners.size() + " roundOver: " + roundOver);
                if (winners.size() > 1 && roundOver) {
                    roundOver = false;
                    players = new ArrayList<>(winners);
                    Log.i("TourActivity: ", " plaeys size" + players.size());
                    winners.clear();
                    roundManagement(players);
                }
            }
        }
    }
}
