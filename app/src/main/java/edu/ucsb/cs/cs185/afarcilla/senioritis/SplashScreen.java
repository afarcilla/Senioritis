package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;


public class SplashScreen extends Activity {

    private ProgressBar spinner;
    protected boolean _active = true;
    protected int _splashTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)){
                        sleep(100);
                        if (_active) waited += 100;
                    }
                }catch(Exception e){}
                finally {
                    //Check preferences
                    SharedPreferences preferences = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
                    String value = preferences.getString("firstRun", "true");
                    if (value.equals("true")) {
                        // the key does not exist, first time opening app
                        startActivity(new Intent(SplashScreen.this, EnterActivity.class));

                    } else {
                        // they key exists, so load main activity
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    }
                    finish();
                }
            };
        };
        splashTread.start();
    }
}
