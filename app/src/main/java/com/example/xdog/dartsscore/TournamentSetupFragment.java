package com.example.xdog.dartsscore;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TournamentSetupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TournamentSetupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TournamentSetupFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText numPlayers;
    private Player player, tempPlayerHolder;
    private ArrayList<Player> players;
    //private ArrayList<Pair> opponentPairs;
    private int totalNumPlayers;
    private Pair pair;
    private boolean pToNextRound = false;
    private final Random rand = new Random();

    public TournamentSetupFragment() {
        // Required empty public constructor
    }

    public static TournamentSetupFragment newInstance(String param1, String param2) {
        TournamentSetupFragment fragment = new TournamentSetupFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        players = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tournament_setup, container, false);
        numPlayers = (EditText) view.findViewById(R.id.numPlayersET);
        numPlayers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!numPlayers.getText().toString().trim().equals("")) {
                    mListener.onTournamentSetupFragmentInteraction(numPlayers.getText().toString().trim());
                }
            }
        });
        return view;
    }

    public void initialSetup(){
        ArrayList<Player> tempArray = new ArrayList<>(players);
        for (Player temp: players) {
            temp.getPlayerName();
        }
        //ArrayList <Pair> opponentPairs = new ArrayList<>();
        int passedToNextRound; //A random player passes to next round if there is an uneven amount of players
        if ((totalNumPlayers %2) != 0) {
            passedToNextRound = rand.nextInt(totalNumPlayers);
            pToNextRound = true;
            Iterator<Player> itr = tempArray.iterator();
            while ( itr.hasNext() ) {
                Player tempPlayer = itr.next();
                if (tempPlayer.getPlayerNum() == passedToNextRound) {
                    tempPlayerHolder = tempPlayer;
                    itr.remove();
                }
            }
            playerMatchUp(tempArray);
        } else {
            playerMatchUp(players);
        }
    }

    public void roundManagement(ArrayList<Player> players) {
        int passedToNextRound;
        if (!pToNextRound) {
            players.add(tempPlayerHolder);
            pToNextRound = false;
        }
        if (players.size() > 1) {
            if ((players.size() % 2) != 0) {
                passedToNextRound = rand.nextInt(players.size() - 1);
                tempPlayerHolder = players.get(passedToNextRound);
                Iterator<Player> itr = players.iterator();
                while (itr.hasNext()) {
                    Player tempPlayer = itr.next();
                    if (tempPlayer.getPlayerNum() == passedToNextRound) {
                        tempPlayerHolder = tempPlayer;
                        itr.remove();
                    }
                }
                Collections.shuffle(players);
                playerMatchUp(players);
            } else {
                Collections.shuffle(players);
                playerMatchUp(players);
            }
        } else {
            //todo Declare winner
        }
    }

    public ArrayList<Pair> playerMatchUp(ArrayList<Player> players) {
        final Random rand = new Random();
        ArrayList<Pair> opponentPairs = new ArrayList<>();
        Collections.shuffle(players, rand);
        for (int i = 0; i < players.size() ; i+=2) {
            pair = new Pair(i, (i + 1));
            opponentPairs.add(pair);
        }
        return opponentPairs;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setPlayerNames(ArrayList<String> playerNames) {
        int i = 0;
        for (String temp: playerNames) {
            Player player = new Player(temp, 501, i);
            players.add(player);
            i++;
        }
    }

    public interface OnFragmentInteractionListener {
        void onTournamentSetupFragmentInteraction(String numPlayers);
    }
}
