package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by Natasha on 6/9/15.
 */
public class ActivityEditFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        LayoutInflater i = LayoutInflater.from(getActivity());
        builder.setView(i.inflate(R.layout.frag_activitiesinfo, null));

        return builder.create();
    }
}
