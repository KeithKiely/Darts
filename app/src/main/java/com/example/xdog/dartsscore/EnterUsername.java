package com.example.xdog.dartsscore;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EnterUsername.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EnterUsername#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnterUsername extends Fragment {
    private int numParticipants;
    private Button nextBT;
    private EditText usernameET;
    private TextView playerNumTV;
    private int currentPlayer = 0;
    private ArrayList<String> playerNames = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    private Player player;
    private ArrayList <Player> players = new ArrayList<>();
    private int numPlayer;
    private int numLegs;
    private int gameScore;
    public static final String PLAYER_ARRAY = "players";
    public static final String NUMBER_OF_LEGS = "numLegs";

    public EnterUsername() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EnterUsername newInstance(String param1, String param2) {
        EnterUsername fragment = new EnterUsername();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        numPlayer = getArguments().getInt(Constant.NUM_PLAYERS);
        numLegs = getArguments().getInt(Constant.NUM_LEGS);
        gameScore = getArguments().getInt(Constant.GAME_SCORE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enter_username, container, false);

        usernameET = (EditText) view.findViewById(R.id.usernameET);
        playerNumTV = (TextView) view.findViewById(R.id.playerNumTV);
        String tempCurrentP = getResources().getString(R.string.player) + (currentPlayer + 1);
        playerNumTV.setText(tempCurrentP);
        nextBT = (Button) view.findViewById(R.id.nextBT);
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPlayer != numPlayer) {
                    if (!usernameET.getText().toString().trim().equals("")) {
                        String tempCurrentP = getResources().getString(R.string.player) + (currentPlayer + 2);
                        playerNumTV.setText(tempCurrentP);
                        player = new Player(usernameET.getText().toString().trim(), gameScore, currentPlayer);
                        players.add(player);
                        currentPlayer++;
                        usernameET.setText("");
                    } else {
                        Context context1 = getActivity().getApplicationContext();
                        CharSequence message = getResources().getString(R.string.invalid_entry);
                        final Toast toastBasic = Toast.makeText(context1, message, Toast.LENGTH_SHORT);
                        toastBasic.show();
                    }
                }
                if (currentPlayer == numPlayer) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(PLAYER_ARRAY,players);
                    bundle.putInt(NUMBER_OF_LEGS, numLegs);
                    mListener.sendPlayerNames(bundle);
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
        return view;
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

    public interface OnFragmentInteractionListener {
        void sendPlayerNames(Bundle bundle);
    }
}
