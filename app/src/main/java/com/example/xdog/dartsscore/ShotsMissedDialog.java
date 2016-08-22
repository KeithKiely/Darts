package com.example.xdog.dartsscore;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Keith Kiely on 22/08/2016.
 * Fragment Dialog: Displays the 4 highest number of missed throws
 */
public class ShotsMissedDialog extends DialogFragment {
    private ArrayList <HashMap> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void populateList() {

        list = new ArrayList<>();

        HashMap temp = new HashMap();

        temp.put(Constant.FIRST_COLUMN,"Colored Notebooks");
        temp.put(Constant.SECOND_COLUMN, "By NavNeet");
        temp.put(Constant.THIRD_COLUMN, "Rs. 200");
        list.add(temp);

        HashMap temp1 = new HashMap();
        temp1.put(Constant.FIRST_COLUMN,"Diaries");
        temp1.put(Constant.SECOND_COLUMN, "By Amee Products");
        temp1.put(Constant.THIRD_COLUMN, "Rs. 400");
        list.add(temp1);

        HashMap temp2 = new HashMap();
        temp2.put(Constant.FIRST_COLUMN,"Note Books and Stationery");
        temp2.put(Constant.SECOND_COLUMN, "By National Products");
        temp2.put(Constant.THIRD_COLUMN, "Rs. 600");
        list.add(temp2);

        HashMap temp3 = new HashMap();
        temp3.put(Constant.FIRST_COLUMN,"Corporate Diaries");
        temp3.put(Constant.SECOND_COLUMN, "By Devarsh Prakashan");
        temp3.put(Constant.THIRD_COLUMN, "Rs. 800");
        list.add(temp3);

        HashMap temp4 = new HashMap();
        temp4.put(Constant.FIRST_COLUMN,"Writing Pad");
        temp4.put(Constant.SECOND_COLUMN, "By TechnoTalaktive Pvt. Ltd.");
        temp4.put(Constant.THIRD_COLUMN, "Rs. 100");
        list.add(temp4);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.missed_shots_dialog, container, false);

        ListView lView = (ListView) rootView.findViewById(R.id.threeColumnListview);
        populateList();
        ListviewAdapter listAdapter = new ListviewAdapter(getActivity(), list);
        lView.setAdapter(listAdapter);

        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}
