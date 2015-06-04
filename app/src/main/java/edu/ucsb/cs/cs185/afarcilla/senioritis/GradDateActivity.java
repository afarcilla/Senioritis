package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.view.View;
import android.widget.DatePicker;

<<<<<<< HEAD
import java.util.Calendar;
import java.util.Date;
=======
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
>>>>>>> added days left til grad

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

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date gradDate = calendar.getTime();

        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.putLong("gradDate", gradDate.getTime());
        prefsEditor.commit();

        long days = getDaysTilGrad(day, month, year);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("daysTilGrad", days);
        startActivity(intent);
    }

    public void skip(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public long getDaysTilGrad(int day, int month, int year) {
        Calendar gradDate = Calendar.getInstance();
        Calendar todayDate = Calendar.getInstance();

        gradDate.set(year, month, day);

        long grad = gradDate.getTimeInMillis();
        long today = todayDate.getTimeInMillis();

        long diff = grad - today;
        long sec = diff / 1000 ;
        long minutes = sec / 60 ;
        long hr = minutes / 60 ;
        long days = hr / 24 ;

        return days;
    }
}
