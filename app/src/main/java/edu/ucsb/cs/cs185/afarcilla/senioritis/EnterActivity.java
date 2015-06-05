package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class EnterActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
    }

    public void startClick(View v){

        SharedPreferences preferences = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        EditText t_name = (EditText) findViewById(R.id.name);
        EditText t_gpa = (EditText) findViewById(R.id.gpa);
        EditText t_units = (EditText) findViewById(R.id.units);

        String name = t_name.getText().toString();
        String gpa = t_gpa.getText().toString();
        String units = t_units.getText().toString();

        if(name.equals("")  ||
           gpa.equals("")   ||
           units.equals(""))
                Toast.makeText(this, "missing fields", Toast.LENGTH_LONG).show();
        else{
            SharedPreferences.Editor prefsEditor = preferences.edit();
            prefsEditor.putString("name", name);
            prefsEditor.putString("gpa", gpa);
            prefsEditor.putString("units", units);
            prefsEditor.putBoolean("firstRun", false);
            prefsEditor.commit();

            startActivity(new Intent(EnterActivity.this, GradDateActivity.class));
        }

    }

    public void skip(View v){
        startActivity(new Intent(EnterActivity.this, GradDateActivity.class));
    }

}
