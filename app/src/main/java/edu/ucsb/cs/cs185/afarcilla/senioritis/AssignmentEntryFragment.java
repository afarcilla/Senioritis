package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class AssignmentEntryFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        LayoutInflater i = LayoutInflater.from(getActivity());
        builder.setView(i.inflate(R.layout.frag_assignment_info, null));

        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SharedPreferences preferences = getActivity().getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);

                EditText title = (EditText) getDialog().findViewById(R.id.title_entry);
                Spinner category = (Spinner) getDialog().findViewById(R.id.grades);
                EditText points = (EditText) getDialog().findViewById(R.id.points);
                EditText outOf = (EditText) getDialog().findViewById(R.id.outof);

                String assignment = title.getText().toString();
                String categoryType = category.getSelectedItem().toString();
                String pointsNum = points.getText().toString();
                String outOfNum = outOf.getText().toString();


                SharedPreferences.Editor prefsEditor = preferences.edit();
                int num = preferences.getInt("AssignmentNum", 0);
                prefsEditor.putInt("AssignmentNum", num + 1);
                prefsEditor.putString("assignmentTitle" + num, assignment);
                prefsEditor.putString("assignmentCategory" + num, categoryType);
                prefsEditor.putString("assignmentScore" + num, pointsNum);
                prefsEditor.putString("assignmentOutOf" + num, outOfNum);

                prefsEditor.commit();
                FragmentTabHost mTabHost = (FragmentTabHost) getActivity().findViewById(android.R.id.tabhost);




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
