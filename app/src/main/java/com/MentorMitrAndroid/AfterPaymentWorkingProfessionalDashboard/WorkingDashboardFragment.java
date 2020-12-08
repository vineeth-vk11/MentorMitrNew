package com.MentorMitrAndroid.AfterPaymentWorkingProfessionalDashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MentorMitrAndroid.ActivitiesHelper.ActivitiesActivity;
import com.MentorMitrAndroid.GoalsAndAffirmationsHelper.GoalsAndAffirmationsActivity;
import com.MentorMitrAndroid.PriorityHelper.PrioritiesActivity;
import com.MentorMitrAndroid.QuestionnaireHelper.SurveyActivity;
import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.WeeklyTimetableHelper.WeeklyTimetableActivity;

public class WorkingDashboardFragment extends Fragment {

    CardView questionnaire, priority, goalsAndAffirmations, activities, weeklyTimetable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_working_dashboard, container, false);

        questionnaire = view.findViewById(R.id.materialCardView);
        priority = view.findViewById(R.id.priority);
        goalsAndAffirmations = view.findViewById(R.id.goals);
        activities = view.findViewById(R.id.activities);
        weeklyTimetable = view.findViewById(R.id.materialCardView2);

        questionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callSurveyIntent("working_professionals");
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
        startActivityForResult(surveyIntent, 1337);
    }
}