package com.example.xdog.dartsscore;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String p1Name, p2Name, p1RoundWins, p2RoundWins, maxRounds;
    private int totalPlayers;
    private TextView player1NameTV, player2NameTV, player1RoundsTV,
            player2RoundsTV, p1WinsTV, p2WinsTV,
            player1LostTV, player2LostTV;

    private OnFragmentInteractionListener mListener;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
            p1RoundWins = " " + getArguments().getInt(Scoreboard.P1_ROUND_WINS);
            maxRounds = " " + getArguments().getInt(Scoreboard.NUM_ROUNDS);
            p2Name = " " + getArguments().getString(Scoreboard.PLAYER_2_NAME);
            p2RoundWins = " " + getArguments().getInt(Scoreboard.P2_ROUND_WINS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        TableRow player2Row = (TableRow) rootView.findViewById(R.id.player2Row);
        player1NameTV = (TextView) rootView.findViewById(R.id.player1NameTV);
        player1RoundsTV = (TextView) rootView.findViewById(R.id.player1RoundsTV);
        p1WinsTV = (TextView) rootView.findViewById(R.id.playerWinsTV);
        player1LostTV = (TextView) rootView.findViewById(R.id.player1LostTV);
        player1NameTV.setText(p1Name);
        player1RoundsTV.setText(maxRounds);
        p1WinsTV.setText(p1RoundWins);
        player1LostTV.setText(p2RoundWins);
        if (totalPlayers == 1) {
            player2Row.setVisibility(View.INVISIBLE);
        }
        if (totalPlayers == 2) {
            player2NameTV = (TextView) rootView.findViewById(R.id.player2NameTV);
            player2LostTV = (TextView) rootView.findViewById(R.id.player2LostTV);
            player2RoundsTV = (TextView) rootView.findViewById(R.id.player2RoundsTV);
            p2WinsTV = (TextView) rootView.findViewById(R.id.player2WinsTV);
            player2NameTV.setText(p2Name);
            player2RoundsTV.setText(maxRounds);
            p2WinsTV.setText(p2RoundWins);
            player2LostTV.setText(p1RoundWins);
        }


        // Inflate the layout for this fragment
        getDialog().setTitle(getResources().getString(R.string.current_score));
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
