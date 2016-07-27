package com.example.xdog.dartsscore;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jimena on 20/07/2016.
 */
public class GamerOverDialog extends DialogFragment {
    private Button doneB;
    private TextView rWins, rLose, rounds, name;
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.game_over_dialog, container, false);

        doneB = (Button) rootView.findViewById(R.id.doneButton);
        doneB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        getDialog().setTitle(getResources().getString(R.string.game_over));
        return rootView;
    }


    public void onStart(){
        super.onStart();
        rWins = (TextView) rootView.findViewById(R.id.numWinTV);
        rLose = (TextView) rootView.findViewById(R.id.numLoseTV);
        rounds = (TextView) rootView.findViewById(R.id.numRoundsTV);
        name = (TextView) rootView.findViewById(R.id.playerNameTV);

        String roundWins = ""+ Scoreboard.bundle.getInt(Scoreboard.P1_ROUND_WINS);
        String roundLose = ""+ Scoreboard.bundle.getInt(Scoreboard.P2_ROUND_WINS);
        String numLegs = ""+ Scoreboard.bundle.getInt(Scoreboard.NUM_ROUNDS);
        String winnersName = "" + getArguments().getString(Scoreboard.PLAYER_NAME);

        rWins.setText(roundWins);
        rLose.setText(roundLose);
        rounds.setText(numLegs);
        name.setText(winnersName);
    }
}
