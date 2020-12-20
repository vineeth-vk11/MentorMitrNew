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

public class UniversityStudentProgram extends AppCompatActivity /*implements PaytmPaymentTransactionCallback*/ {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Map<String, Object> currentUserData;
    private boolean fetchedUserData = false;

    String custId = "100", mid = "kXUAwf16188730506375";
    static int orderId = 101;
    String timestamp;
    public String str = "";
    //GoogleSignIn mGoogleSignIn;
    GoogleSignInAccount account;
    FirebaseFirestore db;
    public int flag = 0;
    public int amount3, amount1;

    TextInputLayout promocode3, promocode1;
    public String promo;
    String collegestr;
    private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_student_program);

        MaterialToolbar toolbar = findViewById(R.id.material_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)  {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CardView threemonthscollege=findViewById(R.id.college_student_3months_cardview);
        threemonthscollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent threemonthcollege = new Intent(UniversityStudentProgram.this , UniversityProgram3Month.class);
                startActivity(threemonthcollege);

            }
        });
        CardView onemonthcollege=findViewById(R.id.college_student_1month_cardview);
        onemonthcollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent onemonthcollege= new Intent(UniversityStudentProgram.this , UniversityProgram1Month.class);
                startActivity(onemonthcollege);

            }
        });


    }
}
