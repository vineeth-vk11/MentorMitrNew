package com.MentorMitrAndroid.MentorDashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.MentorMitrAndroid.LoginHelperNew.LoginMobileNumberActivity;
import com.MentorMitrAndroid.R;
import com.google.firebase.auth.FirebaseAuth;

public class MentorMoreFragment extends Fragment {

    Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mentor_more, container, false);

        logout = view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getContext(), LoginMobileNumberActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        return view;
    }
}