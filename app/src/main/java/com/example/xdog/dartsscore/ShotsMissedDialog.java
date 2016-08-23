package com.example.xdog.dartsscore;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Keith Kiely on 22/08/2016.
 * Fragment Dialog: Displays the 4 highest number of missed throws
 */
public class ShotsMissedDialog extends DialogFragment {
    private ArrayList <HashMap> list;
    private HashMap<String, Integer> playerStats;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerStats = (HashMap<String, Integer>) PracticeSession.bundle.getSerializable(PracticeSession.HASH_MAP);
        if (playerStats != null) {
            for (Map.Entry<String, Integer> map: playerStats.entrySet()) {
                Log.i(" ShotsMissedDialog: ", " Key: " + map.getKey() + " Value: " + map.getValue());
            }
        }
    }

    private void populateList() {
        list = new ArrayList<>();
        int index = 0;
        for (Map.Entry<String, Integer> map: playerStats.entrySet()) {
            index++;

            if (index == 1 ) {
                HashMap temp = new HashMap();
                String split[] = map.getKey().split("\\s+");
                temp.put(Constant.FIRST_COLUMN, split[0]);
                temp.put(Constant.SECOND_COLUMN, split[1]);
                temp.put(Constant.THIRD_COLUMN, map.getValue());
                list.add(temp);
            }
            if (index == 2 ) {
                HashMap temp2 = new HashMap();
                String split[] = map.getKey().split("\\s+");
                temp2.put(Constant.FIRST_COLUMN, split[0]);
                temp2.put(Constant.SECOND_COLUMN, split[1]);
                temp2.put(Constant.THIRD_COLUMN, map.getValue());
                list.add(temp2);
            }
            if (index == 3 ) {
                HashMap temp3 = new HashMap();
                String split[] = map.getKey().split("\\s+");
                temp3.put(Constant.FIRST_COLUMN, split[0]);
                temp3.put(Constant.SECOND_COLUMN, split[1]);
                temp3.put(Constant.THIRD_COLUMN, map.getValue());
                list.add(temp3);
            }
            if (index == 4 ) {
                HashMap temp4 = new HashMap();
                String split[] = map.getKey().split("\\s+");
                temp4.put(Constant.FIRST_COLUMN, split[0]);
                temp4.put(Constant.SECOND_COLUMN, split[1]);
                temp4.put(Constant.THIRD_COLUMN, map.getValue());
                list.add(temp4);
            }
            if (index == 5 ) {
                HashMap temp5 = new HashMap();
                String split[] = map.getKey().split("\\s+");
                temp5.put(Constant.FIRST_COLUMN, split[0]);
                temp5.put(Constant.SECOND_COLUMN, split[1]);
                temp5.put(Constant.THIRD_COLUMN, map.getValue());
                list.add(temp5);
            }
            if (index == 6 ) {
                HashMap temp6 = new HashMap();
                String split[] = map.getKey().split("\\s+");
                temp6.put(Constant.FIRST_COLUMN, split[0]);
                temp6.put(Constant.SECOND_COLUMN, split[1]);
                temp6.put(Constant.THIRD_COLUMN, map.getValue());
                list.add(temp6);
            }
            if (index == 7 ) {
                HashMap temp7 = new HashMap();
                String split[] = map.getKey().split("\\s+");
                temp7.put(Constant.FIRST_COLUMN, split[0]);
                temp7.put(Constant.SECOND_COLUMN, split[1]);
                temp7.put(Constant.THIRD_COLUMN, map.getValue());
                list.add(temp7);
            }
            if (index == 8 ) {
                HashMap temp8 = new HashMap();
                String split[] = map.getKey().split("\\s+");
                temp8.put(Constant.FIRST_COLUMN, split[0]);
                temp8.put(Constant.SECOND_COLUMN, split[1]);
                temp8.put(Constant.THIRD_COLUMN, map.getValue());
                list.add(temp8);
            }
        }
        /*temp.put(Constant.FIRST_COLUMN,"Colored Notebooks");
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
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.missed_shots_dialog, container, false);
        Button button = (Button) rootView.findViewById(R.id.finishedButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        ListView lView = (ListView) rootView.findViewById(R.id.threeColumnListview);
        populateList();
        ListviewAdapter listAdapter = new ListviewAdapter(getActivity(), list);
        lView.setAdapter(listAdapter);
        getDialog().setCanceledOnTouchOutside(false);
        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

}
