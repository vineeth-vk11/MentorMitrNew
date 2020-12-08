package com.MentorMitrAndroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.MentorMitrAndroid.AfterPaymentCollegeStudentDashboard.AfterPaymentCollegeStudentDashboardActivity;
import com.MentorMitrAndroid.AfterPaymentStudentDashboard.AfterPaymentSchoolStudentDashboardActivity;
import com.MentorMitrAndroid.AfterPaymentWorkingProfessionalDashboard.AfterPaymentWorkingProfessionalActivity;
import com.MentorMitrAndroid.BeforePaymentDashboardHelper.BeforePaymentDashboardActivity;
import com.MentorMitrAndroid.IntroHelper.IntroActivity;
import com.MentorMitrAndroid.LoginHelperNew.LoginMobileNumberActivity;
import com.MentorMitrAndroid.MentorDashboard.MentorDashboardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_acitivity);

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {

                if(sharedPreferences.getBoolean("firstStart",true)){
                    Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    if(FirebaseAuth.getInstance().getCurrentUser() != null){

                        FirebaseFirestore db;
                        db = FirebaseFirestore.getInstance();
                        db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                DocumentSnapshot documentSnapshot = task.getResult();

                                String type = documentSnapshot.getString("type");

                                Log.i("type", type);

                                if(type.equals("School")){
                                    Intent intent = new Intent(SplashActivity.this, AfterPaymentSchoolStudentDashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else if(type.equals("Mentor")){
                                    Intent intent = new Intent(SplashActivity.this, MentorDashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else if(type.equals("Working")){
                                    Intent intent = new Intent(SplashActivity.this, AfterPaymentWorkingProfessionalActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else if(type.equals("College")){
                                    Intent intent = new Intent(SplashActivity.this, AfterPaymentCollegeStudentDashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

                    }
                    else {
                        Intent intent = new Intent(SplashActivity.this, LoginMobileNumberActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        }, secondsDelayed * 3000);
    }
}