package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class TextEntryFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        LayoutInflater i = LayoutInflater.from(getActivity());
        View v = i.inflate(R.layout.frag_classinfo, null);
        builder.setView(v);

        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getActivity().getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);

                EditText title = (EditText) getDialog().findViewById(R.id.title_entry);
                EditText units = (EditText) getDialog().findViewById(R.id.numunits);
                Spinner grade = (Spinner) getDialog().findViewById(R.id.grades);
                EditText hw = (EditText) getDialog().findViewById(R.id.hwpercent);
                EditText mid = (EditText) getDialog().findViewById(R.id.midpercent);
                EditText fin = (EditText) getDialog().findViewById(R.id.finalpercent);
                EditText proj = (EditText) getDialog().findViewById(R.id.projpercent);
                EditText other = (EditText) getDialog().findViewById(R.id.othpercent);

                String courseTitle = title.getText().toString();
                String numUnits = units.getText().toString();
                String gradeDesired = grade.getSelectedItem().toString();
                String hwPercent = hw.getText().toString();
                String midPercent = mid.getText().toString();
                String finalPercent = fin.getText().toString();
                String projectPercent = proj.getText().toString();
                String otherPercent = other.getText().toString();

                SharedPreferences.Editor prefsEditor = preferences.edit();
                int num = preferences.getInt("classNum", 0);
                prefsEditor.putInt("classNum", num+1);
                prefsEditor.putString("courseTitle" + num, courseTitle);
                prefsEditor.putFloat("numUnits" + num, Float.parseFloat(numUnits));
                prefsEditor.putString("gradeDesired" + num, gradeDesired);
                prefsEditor.putFloat("hwPercent" + num, Float.parseFloat(hwPercent));
                prefsEditor.putFloat("midPercent" + num, Float.parseFloat(midPercent));
                prefsEditor.putFloat("finalPercent" + num, Float.parseFloat(finalPercent));
                prefsEditor.putFloat("projectPercent" + num, Float.parseFloat(projectPercent));
                prefsEditor.putFloat("otherPercent" + num, Float.parseFloat(otherPercent));
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
