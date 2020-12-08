package com.MentorMitrAndroid.TracksAndOptionsHelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.MentorMitrAndroid.AfterPaymentStudentDashboard.AfterPaymentSchoolStudentDashboardActivity;
import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TracksAndOptionsActivity extends AppCompatActivity {

    CheckBox engineering, medical, science, bba, iim, bca, pharma, du, bcom, fine, visual, design, pilot, forces, ca, ba, vocational, law, performing, enterpreneurship, traditional, languages;
    Button submit;
    DatabaseReference mdatabase;
    private FirebaseAuth mAuth;

    FirebaseFirestore db;
    FirebaseUser currentUser;
    private Map<String, Object> currentUserData;
    private boolean fetchedUserData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_template);
        engineering = (CheckBox) findViewById(R.id.engineering);
        medical = (CheckBox) findViewById(R.id.medical);
        science = (CheckBox) findViewById(R.id.science);
        bba = (CheckBox) findViewById(R.id.bba);
        bca = (CheckBox) findViewById(R.id.bca);
        pharma = (CheckBox) findViewById(R.id.pharma);
        visual = (CheckBox) findViewById(R.id.visual);
        design = (CheckBox) findViewById(R.id.design);
        du = (CheckBox) findViewById(R.id.du);
        iim = (CheckBox) findViewById(R.id.iim);
        bcom = (CheckBox) findViewById(R.id.bcom);
        fine = (CheckBox) findViewById(R.id.fine);
        ca = (CheckBox) findViewById(R.id.ca);
        law = (CheckBox) findViewById(R.id.law);
        languages = (CheckBox) findViewById(R.id.languages);
        performing = (CheckBox) findViewById(R.id.performing);
        pilot = (CheckBox) findViewById(R.id.pilot);
        forces = (CheckBox) findViewById(R.id.forces);
        enterpreneurship = (CheckBox) findViewById(R.id.enterpreneurship);
        traditional = (CheckBox) findViewById(R.id.traditional);
        ba = (CheckBox) findViewById(R.id.ba);
        vocational = (CheckBox) findViewById(R.id.vocational);

        submit = (Button) findViewById(R.id.submit);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        mdatabase = FirebaseDatabase.getInstance().getReference("user");
        String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference subDatabaseReference = mdatabase.child(user_id).child("options");

        DatabaseReference subDatabaseReference1 = mdatabase.child(user_id).child("cbase");

        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                List<String> optionsList = new ArrayList<>();

                List<String> cbase = new ArrayList<>();

                if (engineering.isChecked()) {
                    optionsList.add("Engineering");
                }
                if (medical.isChecked()) {
                    optionsList.add("Medical");
                }
                if (bba.isChecked()) {
                    optionsList.add("BBA");
                }
                if (du.isChecked()) {
                    optionsList.add("DU-JAT");
                }
                if (law.isChecked()) {
                    optionsList.add("LAW");
                }
                if (bca.isChecked()) {
                    optionsList.add("BCA");
                }
                if (bba.isChecked()) {
                    optionsList.add("BBA");
                }
                if (pharma.isChecked()) {
                    optionsList.add("B.Pharma");
                }
                if (bcom.isChecked()) {
                    optionsList.add("B.Com");
                }
                if (science.isChecked()) {
                    optionsList.add("Science");
                }
                if (ba.isChecked()) {
                    optionsList.add("BA");
                }
                if (vocational.isChecked()) {
                    optionsList.add("Vocational Studies");
                }
                if (languages.isChecked()) {
                    optionsList.add("Lanuages");
                }
                if (ca.isChecked()) {
                    optionsList.add("CA");
                }
                if (forces.isChecked()) {
                    optionsList.add("Armed Forces");
                }
                if (pilot.isChecked()) {
                    optionsList.add("Pilot");
                }
                if (iim.isChecked()) {
                    optionsList.add("IIM");
                }
                if (enterpreneurship.isChecked()) {
                    optionsList.add("Enterpreneurship");
                }
                if (traditional.isChecked()) {
                    optionsList.add("Non-Traditional");
                }
                if (fine.isChecked()) {
                    optionsList.add("Fine Arts");
                }
                if (performing.isChecked()) {
                    optionsList.add("Performing Arts");
                }
                if (design.isChecked()) {
                    optionsList.add("Design");
                }
                if (visual.isChecked()) {
                    optionsList.add("Visual");
                }

                if(optionsList.size() != 0){
                    subDatabaseReference.setValue(optionsList).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "Options saved successfully",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AfterPaymentSchoolStudentDashboardActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Select atleast one option",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}