package com.MentorMitrAndroid.MentorDashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.MentorMitrAndroid.MentorHomeHelper.MentorHomeFragment;
import com.MentorMitrAndroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MentorDashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;

    MentorHomeFragment homeFragment = new MentorHomeFragment();
    MentorSessionsFragment sessionsFragment = new MentorSessionsFragment();
    MentorMoreFragment moreFragment = new MentorMoreFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_dashboard);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame,homeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.sessions:
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.main_frame,sessionsFragment);
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();
                return true;
            case R.id.more:
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.main_frame,moreFragment);
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();
                return true;
        }
        return false;
    }
}