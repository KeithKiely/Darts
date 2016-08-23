package com.example.xdog.dartsscore;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Keith Kiely on 22/08/2016.
 * DialogFragment: Displays user information related to current game type
 */
public class UsageFragment  extends DialogFragment {

    public UsageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        Log.i("UsageFragment: ", getActivity().getClass().getSimpleName());
        if (getActivity().getClass().getSimpleName().equals("Scoreboard")) {
            rootView = inflater.inflate(R.layout.useage_info_layout, container, false);
        } else {
            rootView = inflater.inflate(R.layout.practice_session_info, container, false);
        }
        getDialog().setTitle(getResources().getString(R.string.how_to));
        return rootView;
    }
}
