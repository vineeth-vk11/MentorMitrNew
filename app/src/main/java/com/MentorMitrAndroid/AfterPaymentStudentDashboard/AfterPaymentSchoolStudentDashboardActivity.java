package com.MentorMitrAndroid.AfterPaymentStudentDashboard;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.MentorMitrAndroid.BlogsHelper.BlogsFragment;
import com.MentorMitrAndroid.MentorsHelper.MentorsFragment;
import com.MentorMitrAndroid.QuestionnaireHelper.models.SurveyAnswers;
import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.TimeTrackerHelper.TimeTrackerFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AfterPaymentSchoolStudentDashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;

    StudentDashboardFragment studentDashboardFragment = new StudentDashboardFragment();
    StudentDashboardFragment3 studentDashboardFragment3 = new StudentDashboardFragment3();
    StudentDashboardFragment4 studentDashboardFragment4 = new StudentDashboardFragment4();
    TimeTrackerFragment timeTrackerFragment = new TimeTrackerFragment();

    AfterPaymentMoreFragment afterPaymentMoreFragment = new AfterPaymentMoreFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_payment_student_dashboard);

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
                fragmentTransaction.replace(R.id.main_frame,studentDashboardFragment);
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
                fragmentTransaction3.replace(R.id.main_frame,studentDashboardFragment4);
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