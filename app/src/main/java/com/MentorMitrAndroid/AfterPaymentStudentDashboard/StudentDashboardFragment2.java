package com.MentorMitrAndroid.AfterPaymentStudentDashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.MentorMitrAndroid.GradesHelper.SubjectsHelper.SubjectsActivity;
import com.MentorMitrAndroid.R;

public class StudentDashboardFragment2 extends Fragment {

    CardView grade, timeTracker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_dashboard2, container, false);

        grade = view.findViewById(R.id.grades);

        grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubjectsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}