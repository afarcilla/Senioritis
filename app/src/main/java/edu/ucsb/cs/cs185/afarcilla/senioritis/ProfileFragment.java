package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Natasha on 6/7/15.
 */
public class ProfileFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        LayoutInflater i = LayoutInflater.from(getActivity());
        builder.setView(i.inflate(R.layout.frag_profile, null));

        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getActivity().getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);

                EditText units = (EditText) getDialog().findViewById(R.id.units);
                EditText current = (EditText) getDialog().findViewById(R.id.current);

                String totalUnits = units.getText().toString();
                String currentgpa = current.getText().toString();

                SharedPreferences.Editor prefsEditor = preferences.edit();
                prefsEditor.putString("units", totalUnits);
                prefsEditor.putString("gpa", currentgpa);
                prefsEditor.commit();

                TextView unitsMain = (TextView) getActivity().findViewById(R.id.units);
                TextView gpaMain = (TextView) getActivity().findViewById(R.id.current);
                unitsMain.setText(totalUnits);
                gpaMain.setText(currentgpa);

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
