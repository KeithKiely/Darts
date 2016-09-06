package com.example.xdog.dartsscore;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameSetUpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameSetUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameSetUpFragment extends Fragment {

    private RadioGroup matchScoreGr;
    private TextView leg;
    private int gameScore, numPlayers;
    private int legs;
    private OnFragmentInteractionListener mListener;


    public GameSetUpFragment() {
        // Required empty public constructor
    }

    public static GameSetUpFragment newInstance(String param1, String param2) {
        GameSetUpFragment fragment = new GameSetUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_set_up, container, false);
        matchScoreGr = (RadioGroup) view.findViewById(R.id.gameType);
        leg = (TextView) view.findViewById(R.id.legsTV);

        final SeekBar numLegs = (SeekBar) view.findViewById(R.id.seekBar);
        if (numLegs != null) {
            numLegs.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    String temp = ""+progress;
                    leg.setText(temp);
                    legs = progress;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
        Button button = (Button) view.findViewById(R.id.startBT);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leg.getText().toString().trim() != "") {
                    legs = Integer.parseInt(leg.getText().toString());
                    switch (matchScoreGr.getCheckedRadioButtonId()) {
                        case R.id.radioB101:
                            gameScore = 101;
                            break;
                        case R.id.radioB301:
                            gameScore = 301;
                            break;
                        case R.id.radioB501:
                            gameScore = 501;
                            break;
                    }
                    if (numPlayers > 0 && legs > 0 ) { //todo throws invalid entry error
                        EnterUsername userNameFrag = new EnterUsername();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();

                        Bundle args = new Bundle();
                        args.putInt(Constant.NUM_PLAYERS, numPlayers);
                        args.putInt(Constant.NUM_LEGS, legs);
                        args.putInt(Constant.GAME_SCORE, gameScore);

                        userNameFrag.setArguments(args);
                        ft.replace(R.id.fragmentContainer, userNameFrag);
                        ft.addToBackStack(null);
                        ft.commit();
                        mListener.onGameSetupFragmentInteraction(gameScore);
                    }
                } else {
                    //todo Add class to catch invalid entry error
                    Context context1 = getActivity().getApplicationContext();
                    CharSequence message = getResources().getString(R.string.invalid_entry);
                    final Toast toastBasic = Toast.makeText(context1, message, Toast.LENGTH_SHORT);
                    toastBasic.show();
                }
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int uri) {
        if (mListener != null) {
            mListener.onGameSetupFragmentInteraction(uri);
        }
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

    public void setNumPlayers(String num) {
        numPlayers = Integer.parseInt(num);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onGameSetupFragmentInteraction(int score);
    }
}
