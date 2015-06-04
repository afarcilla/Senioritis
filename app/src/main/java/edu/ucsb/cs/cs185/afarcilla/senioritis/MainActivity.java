package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.content.SharedPreferences;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

        setDaysLeftView();
=======
>>>>>>> adding floating action button to main activity
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setDaysLeftView(){
        String daysLeft;

        //get current date
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH),
                month = c.get(Calendar.MONTH),
                year = c.get(Calendar.YEAR);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date currentDate = calendar.getTime();
        long current = currentDate.getTime();

        //get grad date
        SharedPreferences shared = getSharedPreferences(getString(R.string.preference_file_key),
                MODE_PRIVATE);
        long gradDateLong = (shared.getLong("gradDate", 0));

        //calculate days left
        long diff = gradDateLong - current;
        long days = diff / (24 * 60 * 60 * 1000);

        //set textView
        TextView text = (TextView) findViewById(R.id.daysleft);
        daysLeft = (gradDateLong == 0) ? "?" : String.valueOf(days);
        text.setText(String.valueOf(daysLeft));
    }

}
