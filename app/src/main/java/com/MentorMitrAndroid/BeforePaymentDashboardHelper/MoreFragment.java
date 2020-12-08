package com.MentorMitrAndroid.BeforePaymentDashboardHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.MentorMitrAndroid.BuildConfig;
import com.MentorMitrAndroid.MainActivity;
import com.MentorMitrAndroid.R;
import com.google.firebase.auth.FirebaseAuth;


public class MoreFragment extends Fragment {

    Button joinUs, questionnaire, webinarVideos, editProfile, share, logout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        joinUs = view.findViewById(R.id.joinUs);
        questionnaire = view.findViewById(R.id.questionnaire);
        webinarVideos = view.findViewById(R.id.webinarVideos);
        editProfile = view.findViewById(R.id.editProfile);
        share = view.findViewById(R.id.share);
        logout = view.findViewById(R.id.logout);

        joinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        questionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        webinarVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MentorMitr");
                String shareMessage= "\nHey,\n" +
                        "I wanted to share about MentorMitr with you. They've several skill learning courses to help us manage our time, priorities, enhance our EQ and performance and provide soft skills grooming. They provide weekly reports and mentoring sessions. Use promocode WELCOME500 to get Rs 500 off on all their programs. Wish you a great journey ahead \uD83D\uDE03\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
            }
        });
        return view;
    }
}