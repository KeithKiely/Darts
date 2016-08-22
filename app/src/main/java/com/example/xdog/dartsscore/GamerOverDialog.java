package com.example.xdog.dartsscore;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GamerOverDialog extends DialogFragment {
    private int p1RoundWins, p2RoundWins;
    private String roundWins;
    private String numLegs;
    private String roundLose;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        p1RoundWins = Scoreboard.bundle.getInt(Scoreboard.P1_ROUND_WINS);
        p2RoundWins = Scoreboard.bundle.getInt(Scoreboard.P2_ROUND_WINS);
        roundWins = ""+ p1RoundWins;
        roundLose = ""+ p2RoundWins;
        numLegs = ""+ Scoreboard.bundle.getInt(Scoreboard.NUM_ROUNDS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.game_over_dialog, container, false);
        TextView rWins = (TextView) rootView.findViewById(R.id.numWinTV);
        TextView rLose = (TextView) rootView.findViewById(R.id.numLoseTV);
        TextView rounds = (TextView) rootView.findViewById(R.id.numRoundsTV);
        TextView name = (TextView) rootView.findViewById(R.id.playerNameHeadingTV);
        Button doneB = (Button) rootView.findViewById(R.id.doneButton);
        doneB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        getDialog().setTitle(getResources().getString(R.string.game_over));

        String winnersName;
        if (p1RoundWins != p2RoundWins)
            winnersName = "" + getArguments().getString(Scoreboard.PLAYER_NAME);
        else {
            winnersName = getResources().getString(R.string.draw);
            TextView wins = (TextView) rootView.findViewById(R.id.winTV);
            wins.setVisibility(View.INVISIBLE);
        }

        rWins.setText(roundWins);
        rLose.setText(roundLose);
        rounds.setText(numLegs);
        name.setText(winnersName);

        return rootView;
    }
}
