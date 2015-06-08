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

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProfileTab extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_layout, container, false);
        setDaysLeftView(v);
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

        //set name
        TextView name = (TextView) v.findViewById(R.id.nametext);
        String fullName = (preferences.getString("name", "name"));
        name.setText(fullName);
        name.setTypeface(null, Typeface.BOLD);

        //set units
        TextView units = (TextView) v.findViewById(R.id.units);
        String unitsCompleted = (preferences.getString("units", "0"));
        units.setText(unitsCompleted);

        //set gpa
        TextView gpa = (TextView) v.findViewById(R.id.current);
        String currentGPA = (preferences.getString("gpa", "4.0"));
        gpa.setText(currentGPA);

        //set projected
        TextView pGpa = (TextView) v.findViewById(R.id.projected);
        String projectedGPA = (preferences.getString("pGpa", "4.0"));
        pGpa.setText(projectedGPA);

        //set grad date
        TextView grad = (TextView) v.findViewById(R.id.enddate);
        Long endDate = (preferences.getLong("gradDate", 0));
        Date date = new Date(endDate);
        SimpleDateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
        String dateText = df2.format(date);
        grad.setText(dateText);
    }
}