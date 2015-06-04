package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class GradDateActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graddate);

    }

    public void startClick(View v){
        SharedPreferences preferences = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        DatePicker t_gradDate = (DatePicker) findViewById(R.id.datePicker);
        int day = t_gradDate.getDayOfMonth();
        int month = t_gradDate.getMonth();
        int year =  t_gradDate.getYear();
        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.putString("grad_day", Integer.toString(day));
        prefsEditor.putString("grad_month", Integer.toString(month));
        prefsEditor.putString("grad_year", Integer.toString(year));
        prefsEditor.commit();

        startActivity(new Intent(GradDateActivity.this, MainActivity.class));
        finish();

    }

    public void skip(View v){
        startActivity(new Intent(GradDateActivity.this, MainActivity.class));
        finish();
    }
}
