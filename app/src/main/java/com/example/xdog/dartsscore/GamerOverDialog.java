package com.example.xdog.dartsscore;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Jimena on 20/07/2016.
 */
public class GamerOverDialog extends DialogFragment {
    private Button doneB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.game_over_dialog_layout, container, false);
        doneB = (Button) rootView.findViewById(R.id.doneButton);
        doneB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();
                //dismiss();
            }
        });
        getDialog().setTitle("Simple Dialog");
        return rootView;
    }
}
