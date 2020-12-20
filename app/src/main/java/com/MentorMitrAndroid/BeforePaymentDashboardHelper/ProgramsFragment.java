package com.MentorMitrAndroid.BeforePaymentDashboardHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.MentorMitrAndroid.MainActivity;
import com.MentorMitrAndroid.ProgramsAndPaymentsHelper.StudentProgram;
import com.MentorMitrAndroid.ProgramsAndPaymentsHelper.UniversityStudentProgram;
import com.MentorMitrAndroid.ProgramsAndPaymentsHelper.WorkingProfessionalProgram;
import com.MentorMitrAndroid.R;

public class ProgramsFragment extends Fragment {

    CardView studentProgram, collegeProgram, workingProgram;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_programs, container, false);

        studentProgram = view.findViewById(R.id.cardView2);
        collegeProgram = view.findViewById(R.id.cardView);
        workingProgram = view.findViewById(R.id.workingCard);

        studentProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StudentProgram.class);
                startActivity(intent);
            }
        });

        collegeProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UniversityStudentProgram.class);
                startActivity(intent);
            }
        });

        workingProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WorkingProfessionalProgram.class);
                startActivity(intent);
            }
        });

        return view;
    }
}