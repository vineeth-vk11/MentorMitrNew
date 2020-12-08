package com.MentorMitrAndroid.MentorHomeHelper;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MentorHomeFragment extends Fragment {

    RecyclerView mentorStudents;
    FirebaseFirestore db;
    ArrayList<MentorStudentsModel> mentorStudentsModelArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mentor_home, container, false);

        db = FirebaseFirestore.getInstance();

        mentorStudentsModelArrayList = new ArrayList<>();

        mentorStudents = view.findViewById(R.id.mentorStudentsRecycler);
        mentorStudents.setLayoutManager(new LinearLayoutManager(getContext()));

        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                mentorStudentsModelArrayList.clear();
                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    MentorStudentsModel mentorStudentsModel = new MentorStudentsModel();

                    mentorStudentsModel.setName(documentSnapshot.getString("name"));
                    mentorStudentsModel.setType(documentSnapshot.getString("type"));
                    mentorStudentsModel.setId(documentSnapshot.getId());

                    if(!documentSnapshot.getString("type").equals("Mentor")){
                        mentorStudentsModelArrayList.add(mentorStudentsModel);
                    }
                }

                MentorStudentsAdapter mentorStudentsAdapter = new MentorStudentsAdapter(getContext(), mentorStudentsModelArrayList);
                mentorStudents.setAdapter(mentorStudentsAdapter);
            }
        });
        return view;
    }
}