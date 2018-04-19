package com.sampleproject.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sampleproject.R;
import com.sampleproject.fragment.DashboardFragment;
import com.sampleproject.fragment.FeedFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView mbottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
         mbottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mbottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void switchToFragment1()
    {

//        navigation.setSelectedItemId(R.id.navigation_home);

//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.container, new AssistantFragment()).commit();
    }

    private void switchToFragment2()
    {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, new DashboardFragment()).commit();
    }

    private void switchToFragment3()
    {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, new FeedFragment()).commit();
    }
    private void switchToFragment4()
    {
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.container, new SettingsFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_assistant:
                //  switchToFragment2();
                break;
            case R.id.navigation_dashboard:
                switchToFragment2();
                break;
            case R.id.navigation_feed:
                  switchToFragment3();
                break;
            case R.id.navigation_settings:
                //   switchToFragment2();
                break;

        }
        return true;
    }

}

