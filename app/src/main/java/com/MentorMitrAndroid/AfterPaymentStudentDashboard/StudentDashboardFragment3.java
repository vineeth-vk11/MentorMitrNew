package com.MentorMitrAndroid.AfterPaymentStudentDashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.MentorMitrAndroid.MainActivity;
import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.SessionFeedbackHelper.SessionFeedbackActivity;
import com.MentorMitrAndroid.SessionSchedulingHelper.SessionSchedulingActivity;

public class StudentDashboardFragment3 extends Fragment {

    CardView sessionScheduling, sessionFeedback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_dashboard3, container, false);

        sessionScheduling = view.findViewById(R.id.scheduling);
        sessionFeedback = view.findViewById(R.id.feedback);

        sessionScheduling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SessionSchedulingActivity.class);
                startActivity(intent);
            }
        });

        sessionFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SessionFeedbackActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}