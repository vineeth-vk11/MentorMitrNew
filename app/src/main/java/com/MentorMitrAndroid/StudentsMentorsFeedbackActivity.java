package com.MentorMitrAndroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentsMentorsFeedbackActivity extends AppCompatActivity {

    FirebaseFirestore db;

    TextView goalsSummary, goalsConclusion, tracksAndOptions, timeManagement, emotionalHealth, perfomanceMeasurement,
    suggestion1, suggestion2, suggestion3, suggestion4, suggestion5, specific;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_mentors_feedback);

        goalsSummary = findViewById(R.id.summary_text_layout);
        goalsConclusion = findViewById(R.id.conclusion_text_layout);
        tracksAndOptions = findViewById(R.id.tracks_and_options_text_layout);
        timeManagement = findViewById(R.id.time_management_text_layout);
        emotionalHealth = findViewById(R.id.emotional_health_text_layout);
        perfomanceMeasurement = findViewById(R.id.perfomance_text_layout);
        suggestion1 = findViewById(R.id.suggestion1_text_layout);
        suggestion2 = findViewById(R.id.suggestion2_text_layout);
        suggestion3 = findViewById(R.id.suggestion3_text_layout);
        suggestion4 = findViewById(R.id.suggestion4_text_layout);
        suggestion5 = findViewById(R.id.suggestion5_text_layout);
        specific = findViewById(R.id.specific_text_layout);

        db = FirebaseFirestore.getInstance();

        db.collection("MentorsFeedback").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot documentSnapshot = task.getResult();

                goalsSummary.setText(documentSnapshot.getString("goalSummary"));
                goalsConclusion.setText(documentSnapshot.getString("goalConclusion"));
                tracksAndOptions.setText(documentSnapshot.getString("tracksAndOptions"));
                timeManagement.setText(documentSnapshot.getString("timeManagement"));
                emotionalHealth.setText(documentSnapshot.getString("emotionalHealth"));
                perfomanceMeasurement.setText(documentSnapshot.getString("perfomanceMeasurement"));
                suggestion1.setText(documentSnapshot.getString("suggestion1"));
                suggestion2.setText(documentSnapshot.getString("suggestion2"));
                suggestion3.setText(documentSnapshot.getString("suggestion3"));
                suggestion4.setText(documentSnapshot.getString("suggestion4"));
                suggestion5.setText(documentSnapshot.getString("suggestion5"));
                specific.setText(documentSnapshot.getString("specific"));
            }
        });
    }
}