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

public class ActivityEntryFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        LayoutInflater i = LayoutInflater.from(getActivity());
        builder.setView(i.inflate(R.layout.frag_activitiesinfo, null));

        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getActivity().getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);

                EditText title = (EditText) getDialog().findViewById(R.id.title);
                Spinner imp = (Spinner) getDialog().findViewById(R.id.grades);

                String activityTitle = title.getText().toString();
                String importance = imp.getSelectedItem().toString();

                SharedPreferences.Editor prefsEditor = preferences.edit();
                int num = preferences.getInt("ActivityNum", 0);
                prefsEditor.putInt("ActivityNum", num+1);
                prefsEditor.putString("activityTitle" + num, activityTitle);
                prefsEditor.putString("importance" + num, importance);
                prefsEditor.commit();
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

