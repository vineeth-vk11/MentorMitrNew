package com.MentorMitrAndroid.AfterPaymentStudentDashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.MentorMitrAndroid.MainActivity;
import com.MentorMitrAndroid.R;

public class SchoolStudentProcessFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school_student_process, container, false);

        ImageView chatbot=view.findViewById(R.id.after_payment_school_chatbot);
        chatbot.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onClick(View view) {

                Intent chatbotintent=new Intent(getContext(), MainActivity.class);
                startActivity(chatbotintent);
            }
        });

        return view;
    }
}