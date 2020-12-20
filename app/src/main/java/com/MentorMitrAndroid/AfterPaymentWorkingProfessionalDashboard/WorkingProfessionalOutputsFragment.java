package com.MentorMitrAndroid.AfterPaymentWorkingProfessionalDashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MentorMitrAndroid.GradesHelper.SubjectsHelper.SubjectsActivity;
import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.StudentsMentorsFeedbackActivity;
import com.MentorMitrAndroid.StudentsSuggestedTimingsHelper.StudentsSuggestedTimingsActivity;


public class WorkingProfessionalOutputsFragment extends Fragment {

    CardView  mentorsFeedback, mentorSuggestedTimings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_working_professional_outputs, container, false);

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

        return view;
    }
}