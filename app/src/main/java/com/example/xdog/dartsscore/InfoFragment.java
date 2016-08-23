package com.example.xdog.dartsscore;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
/**
 * Created by Keith Kiely on 22/08/2016.
 * DialogFragment: Displays round and player information
 */
public class InfoFragment extends DialogFragment {
    private String p1Name, p2Name, maxRounds;
    private int totalPlayers, p1RoundWins, p2RoundWins,currentRound;

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            totalPlayers = getArguments().getInt(Scoreboard.TOTAL_PLAYERS);
            p1Name = " " + getArguments().getString(Scoreboard.PLAYER_1_NAME);
            if (totalPlayers == 2)
            p2Name = " " + getArguments().getString(Scoreboard.PLAYER_2_NAME);
            Log.i("InfoFragment: ", "player one name (" + p1Name + ") player 2 (" + p2Name + ")");
            p1RoundWins = getArguments().getInt(Scoreboard.P1_ROUND_WINS);
            maxRounds = " " + getArguments().getInt(Scoreboard.NUM_ROUNDS);
            p2Name = " " + getArguments().getString(Scoreboard.PLAYER_2_NAME);
            p2RoundWins = getArguments().getInt(Scoreboard.P2_ROUND_WINS);
            currentRound = getArguments().getInt(Scoreboard.CURRENT_ROUND);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_fragment, container, false);
        TableRow player2Row = (TableRow) rootView.findViewById(R.id.player2Row);
        TextView player1NameTV = (TextView) rootView.findViewById(R.id.player1NameTV);
        TextView player1RoundsTV = (TextView) rootView.findViewById(R.id.player1RoundsTV);
        TextView p1WinsTV = (TextView) rootView.findViewById(R.id.playerWinsTV);
        TextView player1LostTV = (TextView) rootView.findViewById(R.id.player1LostTV);
        String p1wins = p1RoundWins + "";
        String p2Wins = p2RoundWins + "";
        String currentRoundSt = currentRound +" /" + maxRounds;
        player1NameTV.setText(p1Name);
        player1RoundsTV.setText(currentRoundSt);
        p1WinsTV.setText(p1wins);
        player1LostTV.setText(p2Wins);
        if (totalPlayers == 1) {
            player2Row.setVisibility(View.INVISIBLE);
        }
        if (totalPlayers == 2) {
            TextView player2NameTV = (TextView) rootView.findViewById(R.id.player2NameTV);
            TextView player2LostTV = (TextView) rootView.findViewById(R.id.player2LostTV);
            TextView player2RoundsTV = (TextView) rootView.findViewById(R.id.player2RoundsTV);
            TextView p2WinsTV = (TextView) rootView.findViewById(R.id.player2WinsTV);
            String tempP2Wins = p2RoundWins + "";
            player2NameTV.setText(p2Name);
            player2RoundsTV.setText(currentRoundSt);
            p2WinsTV.setText(tempP2Wins);
            player2LostTV.setText(p1wins);
        }

        // Inflate the layout for this fragment
        getDialog().setTitle(getResources().getString(R.string.current_score));
        return rootView;
    }
}
