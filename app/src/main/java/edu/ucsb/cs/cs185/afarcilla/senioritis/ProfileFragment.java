package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Natasha on 6/7/15.
 */
public class ProfileFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        LayoutInflater i = LayoutInflater.from(getActivity());
        View v = i.inflate(R.layout.frag_profile, null);
        builder.setView(v);

        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getActivity().getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);

                EditText units = (EditText) getDialog().findViewById(R.id.units);
                EditText current = (EditText) getDialog().findViewById(R.id.current);
                EditText date = (EditText) getDialog().findViewById(R.id.gradDate);

                String totalUnits = units.getText().toString();
                String currentgpa = current.getText().toString();
                String gradDate = date.getText().toString();

                SharedPreferences.Editor prefsEditor = preferences.edit();
                prefsEditor.putString("units", totalUnits);
                prefsEditor.putString("gpa", currentgpa);
                prefsEditor.putLong("gradDate", calendar.getTime().getTime());
                prefsEditor.commit();

                TextView unitsMain = (TextView) getActivity().findViewById(R.id.units);
                TextView gpaMain = (TextView) getActivity().findViewById(R.id.current);
                TextView graduationDate = (TextView) getActivity().findViewById(R.id.enddate);
                unitsMain.setText(totalUnits);
                gpaMain.setText(currentgpa);
                graduationDate.setText(gradDate);

                //update days left
                TextView text = (TextView) getActivity().findViewById(R.id.daysleft);
                Calendar c = Calendar.getInstance();
                long currentTime = c.getTime().getTime();
                long days = (calendar.getTime().getTime() - currentTime) / (24 * 60 * 60 * 1000);
                String daysLeft = String.valueOf(days);

                if(days < 0){
                    daysLeft = "ended";
                    text.setTextColor(getResources().getColor(R.color.light_pink));
                    text.setTypeface(null, Typeface.BOLD);
                }
                text.setText(daysLeft);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final EditText date = (EditText) v.findViewById(R.id.gradDate);
        date.setInputType(InputType.TYPE_NULL);
        date.setFocusable(false);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate=Calendar.getInstance();
                int mYear=mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int month, int day) {
                        date.setText(String.format("%02d", month + 1) + "/" + String.format("%02d", day) + "/" + String.valueOf(year));
                        calendar.set(year, month, day);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setCalendarViewShown(false);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });


        return builder.create();
    }
}
