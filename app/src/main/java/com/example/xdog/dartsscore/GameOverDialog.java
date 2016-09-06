package com.example.xdog.dartsscore;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by Keith Kiely on 22/08/2016.
 * DialogFragment: Displays information related to the winning player
 */
public class GameOverDialog extends DialogFragment {
    private int p1RoundWins, p2RoundWins;
    private String roundWins;
    private String numLegs;
    private String roundLose;
    private String winnersName;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        p1RoundWins = Scoreboard.bundle.getInt(Constant.P1_ROUND_WINS);
        p2RoundWins = Scoreboard.bundle.getInt(Constant.P2_ROUND_WINS);
        roundWins = ""+ p1RoundWins;
        roundLose = ""+ p2RoundWins;
        numLegs = ""+ Scoreboard.bundle.getInt(Constant.NUM_ROUNDS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.game_over_dialog, container, false);
        TextView rWins = (TextView) rootView.findViewById(R.id.numWinTV);
        TextView rLose = (TextView) rootView.findViewById(R.id.numLoseTV);
        TextView rounds = (TextView) rootView.findViewById(R.id.numRoundsTV);
        TextView name = (TextView) rootView.findViewById(R.id.playerNameHeadingTV);
        getDialog().setTitle(getResources().getString(R.string.game_over));
        if (p1RoundWins != p2RoundWins)
            winnersName = "" + getArguments().getString(Constant.PLAYER_NAME);
        else {
            winnersName = getResources().getString(R.string.draw);
            TextView wins = (TextView) rootView.findViewById(R.id.winTV);
            wins.setVisibility(View.INVISIBLE);
        }
        Button doneB = (Button) rootView.findViewById(R.id.doneButton);
        doneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        rWins.setText(roundWins);
        rLose.setText(roundLose);
        rounds.setText(numLegs);
        name.setText(winnersName);
        setCancelable(false);
        return rootView;
    }
}
