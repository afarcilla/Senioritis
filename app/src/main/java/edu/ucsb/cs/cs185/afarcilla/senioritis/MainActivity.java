package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        setDaysLeftView();
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

        //get current date
        Calendar c = Calendar.getInstance();
        long current = (c.getTime()).getTime();

        //get grad date
        SharedPreferences shared = getSharedPreferences(getString(R.string.preference_file_key),
                MODE_PRIVATE);
        long gradDate = (shared.getLong("gradDate", 0));

        //calculate days left
        long days = (gradDate - current) / (24 * 60 * 60 * 1000);
        String daysLeft = String.valueOf(days);

        //set textView
        TextView text = (TextView) findViewById(R.id.daysleft);

        if(gradDate == 0){daysLeft = "?";}
        else if(days < 0 && gradDate != 0){
            daysLeft= "ended";
            text.setTextColor(getResources().getColor(R.color.light_pink));
        }

        text.setText(daysLeft);
    }

}
