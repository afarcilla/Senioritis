package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ClassActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_layout);

        TextView title = (TextView) findViewById(R.id.title);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String className = extras.getString("className");
            title.setText(className);


        }


    }

    public void backButton(View v){
        this.finish();
    }
}
