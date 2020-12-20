package com.MentorMitrAndroid.ProgramsAndPaymentsHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.MentorMitrAndroid.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class StudentProgram extends AppCompatActivity /*implements PaytmPaymentTransactionCallback*/ {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Map<String, Object> currentUserData;
    private DatabaseReference mdatabase;
    private boolean fetchedUserData = false;

    String custId = "100", mid = "kXUAwf16188730506375";
    static int orderId = 101;
    String timestamp;
    //GoogleSignIn mGoogleSignIn;
    GoogleSignInAccount account;
    FirebaseFirestore db;

    public int flag = 0;
    public int amnt3;
    public int amnt1;
    public int amnt12;
    String studentstr;

    TextInputLayout promocode3, promocode1, promocode12;
    public String promo;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_program);
        MaterialToolbar toolbar = findViewById(R.id.material_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CardView oneyear = findViewById(R.id.school_student_12months_cardview);
        oneyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent oneyear = new Intent(StudentProgram.this, StudentProgram1Year.class);
                startActivity(oneyear);

            }
        });

        CardView threemonths = findViewById(R.id.school_student_3months_cardview);
        threemonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent threemonth = new Intent(StudentProgram.this, StudentProgram3Months.class);
                startActivity(threemonth);

            }
        });

        CardView onemonth = findViewById(R.id.school_student_1month_cardview);
        onemonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent onemonth = new Intent(StudentProgram.this, StudentProgram1Month.class);
                startActivity(onemonth);

            }
        });


    }
}