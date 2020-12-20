package com.MentorMitrAndroid.MentorSessionFeedbackHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MentorSessionFeedbackActivity extends AppCompatActivity {

    EditText goalsSummary, goalsConclusion, tracksAndOptions, timeManagement, emotionalHealth, perfomanceMeasurement, suggestions1,
    suggestions2, suggestions3, suggestions4, suggestions5, specific;

    FirebaseFirestore db;

    Button submit;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_session_feedback);

        id = getIntent().getStringExtra("id");

        goalsSummary = findViewById(R.id.summary_edit);
        goalsConclusion = findViewById(R.id.conclusion_edit);
        tracksAndOptions = findViewById(R.id.tracks_and_options_edit);
        timeManagement = findViewById(R.id.time_management_edit);
        emotionalHealth = findViewById(R.id.emotional_health_edit);
        perfomanceMeasurement = findViewById(R.id.perfomance_edit);
        suggestions1 = findViewById(R.id.suggestion1_edit);
        suggestions2 = findViewById(R.id.suggestion2_edit);
        suggestions3 = findViewById(R.id.suggestion3_edit);
        suggestions4 = findViewById(R.id.suggestion4_edit);
        suggestions5 = findViewById(R.id.suggestion5_edit);
        specific = findViewById(R.id.specific_edit);

        submit = findViewById(R.id.submit);

        db = FirebaseFirestore.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enteredGoalsSummary = goalsSummary.getText().toString();
                String enteredGoalsConclusion = goalsConclusion.getText().toString();
                String enteredTracksAndOptions = tracksAndOptions.getText().toString();
                String enteredTimeManagement = timeManagement.getText().toString();
                String enteredEmotionalHealth = emotionalHealth.getText().toString();
                String enteredPerfomanceMeasurement = perfomanceMeasurement.getText().toString();
                String enteredSuggestions1 = suggestions1.getText().toString();
                String enteredSuggestions2 = suggestions2.getText().toString();
                String enteredSuggestions3 = suggestions3.getText().toString();
                String enteredSuggestions4 = suggestions4.getText().toString();
                String enteredSuggestions5 = suggestions5.getText().toString();
                String enteredSpecific = specific.getText().toString();
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                if(TextUtils.isEmpty(enteredGoalsSummary)){

                }
                else if(TextUtils.isEmpty(enteredGoalsConclusion)){

                }
                else if(TextUtils.isEmpty(enteredTracksAndOptions)){

                }
                else if(TextUtils.isEmpty(enteredTimeManagement)){

                }
                else if(TextUtils.isEmpty(enteredEmotionalHealth)){

                }
                else if(TextUtils.isEmpty(enteredPerfomanceMeasurement)){

                }
                else if(TextUtils.isEmpty(enteredSuggestions1)){

                }
                else if(TextUtils.isEmpty(enteredSuggestions2)){

                }
                else if(TextUtils.isEmpty(enteredSuggestions3)){

                }
                else if(TextUtils.isEmpty(enteredSuggestions4)){

                }
                else if(TextUtils.isEmpty(enteredSuggestions5)){

                }
                else if(TextUtils.isEmpty(enteredSpecific)){

                }
                else {

                    HashMap<String, Object> data = new HashMap<>();

                    data.put("date",date);
                    data.put("goalSummary", enteredGoalsSummary);
                    data.put("goalConclusion", enteredGoalsConclusion);
                    data.put("tracksAndOptions", enteredTracksAndOptions);
                    data.put("timeManagement", enteredTimeManagement);
                    data.put("emotionalHealth",enteredEmotionalHealth);
                    data.put("perfomanceMeasurement", enteredPerfomanceMeasurement);
                    data.put("suggestion1",enteredSuggestions1);
                    data.put("suggestion2",enteredSuggestions2);
                    data.put("suggestion3",enteredSuggestions3);
                    data.put("suggestion4",enteredSuggestions4);
                    data.put("suggestion5",enteredSuggestions5);
                    data.put("specific",enteredSpecific);



                    db.collection("MentorsFeedback").document(id).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finish();
                        }
                    });
                }
            }
        });



    }
}