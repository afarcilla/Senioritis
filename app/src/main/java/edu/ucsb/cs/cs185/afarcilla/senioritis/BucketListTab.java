package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

public class BucketListTab extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bucket_list_layout, container, false);
        setDaysLeftView(v);
        return v;
    }

    public void setDaysLeftView(View v){

        //get current date
        Calendar c = Calendar.getInstance();
        long current = (c.getTime()).getTime();

        //get grad date
        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        long gradDate = (preferences.getLong("gradDate", 0));

        //calculate days left
        long days = (gradDate - current) / (24 * 60 * 60 * 1000);
        String daysLeft = String.valueOf(days);

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