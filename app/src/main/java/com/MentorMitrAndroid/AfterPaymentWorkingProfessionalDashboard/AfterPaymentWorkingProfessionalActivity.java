package com.MentorMitrAndroid.AfterPaymentWorkingProfessionalDashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.MentorMitrAndroid.AfterPaymentStudentDashboard.AfterPaymentMoreFragment;
import com.MentorMitrAndroid.AfterPaymentStudentDashboard.SchoolStudentProcessFragment;
import com.MentorMitrAndroid.AfterPaymentStudentDashboard.StudentDashboardFragment3;
import com.MentorMitrAndroid.AfterPaymentStudentDashboard.StudentDashboardFragment4;
import com.MentorMitrAndroid.BlogsHelper.BlogsFragment;
import com.MentorMitrAndroid.MentorsHelper.MentorsFragment;
import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.TimeTrackerHelper.TimeTrackerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AfterPaymentWorkingProfessionalActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;

    WorkingDashboardFragment workingDashboardFragment = new WorkingDashboardFragment();
    TimeTrackerFragment timeTrackerFragment = new TimeTrackerFragment();
    StudentDashboardFragment3 studentDashboardFragment3 = new StudentDashboardFragment3();
    StudentDashboardFragment4 studentDashboardFragment4 = new StudentDashboardFragment4();
    AfterPaymentMoreFragment afterPaymentMoreFragment = new AfterPaymentMoreFragment();
    WorkingProfessionalOutputsFragment workingProfessionalOutputsFragment = new WorkingProfessionalOutputsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_payment_working_professional);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.dashboard:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame,workingDashboardFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;

            case R.id.process:
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.main_frame,timeTrackerFragment);
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();
                return true;

            case R.id.mentors:
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.main_frame,studentDashboardFragment3);
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();
                return true;

            case R.id.blogs:
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                fragmentTransaction3.replace(R.id.main_frame,workingProfessionalOutputsFragment);
                fragmentTransaction3.addToBackStack(null);
                fragmentTransaction3.commit();
                return true;

            case R.id.more:
                FragmentManager fragmentManager4 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                fragmentTransaction4.replace(R.id.main_frame,afterPaymentMoreFragment);
                fragmentTransaction4.addToBackStack(null);
                fragmentTransaction4.commit();
                return true;
        }
        return false;
    }
}