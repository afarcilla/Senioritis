package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class ClassActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_layout);

    }

    public void backButton(View v){
        this.finish();
    }
}
