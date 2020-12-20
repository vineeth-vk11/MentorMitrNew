package com.MentorMitrAndroid.AfterPaymentStudentDashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.MentorMitrAndroid.GradesHelper.GradesHelper.GradesActivity;
import com.MentorMitrAndroid.GradesHelper.SubjectsHelper.SubjectsActivity;
import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.StudentsMentorsFeedbackActivity;
import com.MentorMitrAndroid.StudentsSuggestedTimingsHelper.StudentsSuggestedTimingsActivity;

public class StudentDashboardFragment4 extends Fragment {

    CardView gradesProgress, mentorsFeedback, mentorSuggestedTimings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_dashboard4, container, false);

        gradesProgress = view.findViewById(R.id.gradesProgress);
        mentorsFeedback = view.findViewById(R.id.mentorsFeedback);
        mentorSuggestedTimings = view.findViewById(R.id.weeklyTemplate);

        mentorSuggestedTimings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), StudentsSuggestedTimingsActivity.class);
                startActivity(intent);
            }
        });

        mentorsFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), StudentsMentorsFeedbackActivity.class);
                startActivity(intent);
            }
        });

        gradesProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubjectsActivity.class);
                intent.putExtra("from","stats");
                startActivity(intent);
            }
        });


        return view;
    }
}