package edu.ucsb.cs.cs185.afarcilla.senioritis;

import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("Home").setIndicator("", getResources().getDrawable(R.drawable.home60)),
                HomeTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Bucket List").setIndicator("", getResources().getDrawable(R.drawable.list18)),
                BucketListTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Profile").setIndicator("", getResources().getDrawable(R.drawable.users86)),
                ProfileTab.class, null);

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

    public void addClass(View v) {
        DialogFragment newFragment = new TextEntryFragment();
        newFragment.show(getFragmentManager(), "addClass");
    }

    public void addActivity(View v) {
        DialogFragment newFragment = new ActivityEntryFragment();
        newFragment.show(getFragmentManager(), "addActivity");
    }

    public void startHomeInfo(View v){
        DialogFragment newFragment = new InfoHomeFragment();
        newFragment.show(getFragmentManager(), "homeInfoScreen");
    }

    public void startBucketListInfo(View v){
        DialogFragment newFragment = new InfoBucketListFragment();
        newFragment.show(getFragmentManager(), "bucketListInfoScreen");
    }

    public void startProfileInfo(View v){
        DialogFragment newFragment = new InfoProfileFragment();
        newFragment.show(getFragmentManager(), "profileInfoScreen");
    }

}
