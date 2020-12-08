package com.MentorMitrAndroid.BeforePaymentDashboardHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.MentorMitrAndroid.BlogsHelper.BlogsFragment;
import com.MentorMitrAndroid.MainActivity;
import com.MentorMitrAndroid.MentorsHelper.MentorsFragment;
import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class BeforePaymentDashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    BeforePaymentHomeFragment beforePaymentHomeFragment = new BeforePaymentHomeFragment();
    MentorsFragment mentorsFragment = new MentorsFragment();
    MoreFragment moreFragment = new MoreFragment();
    ProgramsFragment programsFragment = new ProgramsFragment();
    BlogsFragment blogsFragment = new BlogsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_payment_dashboard);

        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();

                Boolean paid = documentSnapshot.getBoolean("paid");

                if(paid){
                    String type = documentSnapshot.getString("type");
                    moveToPage(type);

                }
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

    }

    public void moveToPage(String type) {
        switch (type) {
            case "School":
                Intent schooldashintent = new Intent(BeforePaymentDashboardActivity.this, MainActivity.class);
                schooldashintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(schooldashintent);
                finish();
                break;
            case "College":
                Intent collegedashintent = new Intent(BeforePaymentDashboardActivity.this, MainActivity.class);
                collegedashintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(collegedashintent);
                finish();
                break;
            case "Parent":
                Intent parentdashintent = new Intent(BeforePaymentDashboardActivity.this, MainActivity.class);
                parentdashintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(parentdashintent);
                finish();
                break;

            case "Working":
                Intent i = new Intent(BeforePaymentDashboardActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
                break;

            default:
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame,beforePaymentHomeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;

            case R.id.programs:
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1= fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.main_frame,programsFragment);
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();
                return true;

            case R.id.mentors:
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2= fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.main_frame,mentorsFragment);
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();
                return true;

            case R.id.blogs:
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                fragmentTransaction3.replace(R.id.main_frame,blogsFragment);
                fragmentTransaction3.addToBackStack(null);
                fragmentTransaction3.commit();
                return true;

            case R.id.more:
                FragmentManager fragmentManager4 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction4= fragmentManager4.beginTransaction();
                fragmentTransaction4.replace(R.id.main_frame,moreFragment);
                fragmentTransaction4.addToBackStack(null);
                fragmentTransaction4.commit();
                return true;
        }
        return false;
    }
}