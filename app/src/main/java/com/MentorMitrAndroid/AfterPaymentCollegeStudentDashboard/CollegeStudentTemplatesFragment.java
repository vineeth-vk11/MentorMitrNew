package com.MentorMitrAndroid.AfterPaymentCollegeStudentDashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MentorMitrAndroid.ActivitiesHelper.ActivitiesActivity;
import com.MentorMitrAndroid.CollegeTrackHelper.CollegeTrack;
import com.MentorMitrAndroid.GoalsAndAffirmationsHelper.GoalsAndAffirmationsActivity;
import com.MentorMitrAndroid.PriorityHelper.PrioritiesActivity;
import com.MentorMitrAndroid.QuestionnaireHelper.SurveyActivity;
import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.WeeklyTimetableHelper.WeeklyTimetableActivity;

public class CollegeStudentTemplatesFragment extends Fragment {

    CardView questionnaire, sportsTrack, collegeTrack, priority, goalsAndAffirmations, activities, weeklyTimetable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_college_student_templates, container, false);

        questionnaire = view.findViewById(R.id.materialCardView);
        sportsTrack = view.findViewById(R.id.sports);
        collegeTrack = view.findViewById(R.id.college);
        priority = view.findViewById(R.id.priority);
        goalsAndAffirmations = view.findViewById(R.id.goals);
        activities = view.findViewById(R.id.activities);
        weeklyTimetable = view.findViewById(R.id.materialCardView2);

        questionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("intent","started");
                callSurveyIntent("questions_college");
            }
        });

        collegeTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent collegeintent = new Intent(getContext(), CollegeTrack.class);
                startActivity(collegeintent);
            }
        });

        sportsTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callSurveyIntent("questions_specific");
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
        if(questionReference.equalsIgnoreCase("questions_college")) {
            startActivityForResult(surveyIntent, 1337);
        }
        if(questionReference.equalsIgnoreCase("questions_specific"))
        {
            startActivityForResult(surveyIntent,1335);
        }
    }
}