package com.MentorMitrAndroid.AfterPaymentStudentDashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.MentorMitrAndroid.ActivitiesHelper.ActivitiesActivity;
import com.MentorMitrAndroid.CollegeTrackHelper.CollegeTrack;
import com.MentorMitrAndroid.GoalsAndAffirmationsHelper.GoalsAndAffirmationsActivity;
import com.MentorMitrAndroid.MainActivity;
import com.MentorMitrAndroid.PriorityHelper.PrioritiesActivity;
import com.MentorMitrAndroid.QuestionnaireHelper.SurveyActivity;
import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.TracksAndOptionsHelper.TracksAndOptionsActivity;
import com.MentorMitrAndroid.WeeklyTimetableHelper.WeeklyTimetableActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentDashboardFragment extends Fragment {

    CardView questionnaire, tracksAndOptions, sportsTrack, collegeTrack, priority, goalsAndAffirmations, activities, weeklyTimetable;

    String userType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_dashboard, container, false);

        questionnaire = view.findViewById(R.id.materialCardView);
        tracksAndOptions = view.findViewById(R.id.tracks);
        sportsTrack = view.findViewById(R.id.sports);
        collegeTrack = view.findViewById(R.id.college);
        priority = view.findViewById(R.id.priority);
        goalsAndAffirmations = view.findViewById(R.id.goals);
        activities = view.findViewById(R.id.activities);
        weeklyTimetable = view.findViewById(R.id.materialCardView2);
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();

        db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();

                userType = documentSnapshot.getString("type");

            }
        });

        questionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSurveyIntent("questions_section_1");
            }
        });

        tracksAndOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent priorityIntent = new Intent(getContext(), TracksAndOptionsActivity.class);
                startActivity(priorityIntent);
            }
        });

        sportsTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSurveyIntent("questions_specific");
            }
        });

        collegeTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent collegeintent=new Intent(getContext(), CollegeTrack.class);
                startActivity(collegeintent);
            }
        });

        priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PrioritiesActivity.class);
                startActivity(intent);
            }
        });

        goalsAndAffirmations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GoalsAndAffirmationsActivity.class);
                startActivity(intent);
            }
        });

        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivitiesActivity.class);
                startActivity(intent);
            }
        });

        weeklyTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WeeklyTimetableActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    void callSurveyIntent(String questionReference) {
        Intent surveyIntent = new Intent(getContext(), SurveyActivity.class);
        surveyIntent.putExtra("reference", questionReference);
        surveyIntent.putExtra("type","normal");
        if(questionReference.equalsIgnoreCase("questions_section_1")) {
            startActivityForResult(surveyIntent, 1337);
        }
        if(questionReference.equalsIgnoreCase("questions_specific"))
        {
            surveyIntent.putExtra("type","sports");
            startActivityForResult(surveyIntent,1335);
        }
    }

}