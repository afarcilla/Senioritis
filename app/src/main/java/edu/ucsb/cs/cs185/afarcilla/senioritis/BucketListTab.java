package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BucketListTab extends Fragment {
    private ActivityRecycleAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bucket_list_layout, container, false);
        setDaysLeftView(v);

        SharedPreferences preferences = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        int numActivities = preferences.getInt("ActivityNum", 0);

        final List<ActivityStruct> myDataset = new ArrayList<>();
        for(int i = 0; i < numActivities; i++){
            myDataset.add(new ActivityStruct(preferences.getString("activityTitle" + i, "Empty"),
                    preferences.getInt("importance" + i, 0)));

        }

        if(!myDataset.isEmpty()) {
            TextView emptyText = (TextView) v.findViewById(R.id.empty);
            emptyText.setText("");
        }

        final FragmentActivity c = getActivity();
        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);

        new Thread(new Runnable() {
            @Override
            public void run() {
                adapter = new ActivityRecycleAdapter(myDataset);
                c.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();

        return v;
    }

    public void setDaysLeftView(View v){

        //get current date
        Calendar c = Calendar.getInstance();
        long current = (c.getTime()).getTime();

        //get grad date
        SharedPreferences preferences = this.getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        long gradDate = (preferences.getLong("gradDate", 0));

        //calculate days left
        long days = (gradDate - current) / (24 * 60 * 60 * 1000);
        String daysLeft = String.valueOf(days+1);

        //set textView
        TextView text = (TextView) v.findViewById(R.id.daysleft);

        if(gradDate == 0){daysLeft = "?";}
        else if(days < 0 && gradDate != 0){
            daysLeft= "ended";
            text.setTextColor(getResources().getColor(R.color.light_pink));
            text.setTypeface(null, Typeface.BOLD);
        }

        text.setText(daysLeft);
    }
}