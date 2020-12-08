package com.MentorMitrAndroid.WeeklyTimetableHelper.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MentorMitrAndroid.GoalsAndAffirmationsHelper.AddGoalOrAffirmationDialog;
import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.WeeklyTimetableHelper.AddWeeklyItemDialog;
import com.MentorMitrAndroid.WeeklyTimetableHelper.WeeklyTimetableAdapter;
import com.MentorMitrAndroid.WeeklyTimetableHelper.WeeklyTimetableModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class MondayFragment extends Fragment {

    ExtendedFloatingActionButton floatingActionButton;
    ArrayList<String> activities = new ArrayList<>();
    RecyclerView mondayRecycler;
    ArrayList<WeeklyTimetableModel> weeklyTimetableModelArrayList;

    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_monday, container, false);

        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();

        floatingActionButton = view.findViewById(R.id.floating_action_button);

        mondayRecycler = view.findViewById(R.id.mondayRecycler);
        mondayRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mondayRecycler.setHasFixedSize(true);

        if(getArguments().getString("id") != null){
            id = getArguments().getString("id");
            floatingActionButton.setVisibility(View.GONE);
        }
        else {
            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        weeklyTimetableModelArrayList = new ArrayList<>();

        db.collection("Activities").document(id)
                .collection("activites").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                activities.clear();

                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    activities.add(documentSnapshot.getString("activity"));
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddWeeklyItemDialog addWeeklyItemDialog = new AddWeeklyItemDialog(activities,"Monday");
                addWeeklyItemDialog.show(getFragmentManager(), "Add Activity");

            }
        });

        db.collection("WeeklyTimetables").document(id)
                .collection("Monday").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                weeklyTimetableModelArrayList.clear();

                for(DocumentSnapshot documentSnapshot: value.getDocuments()){
                    WeeklyTimetableModel weeklyTimetableModel =new WeeklyTimetableModel();

                    weeklyTimetableModel.setActivity(documentSnapshot.getString("activity"));
                    weeklyTimetableModel.setTime(documentSnapshot.getString("timing"));
                    weeklyTimetableModelArrayList.add(weeklyTimetableModel);
                }

                WeeklyTimetableAdapter weeklyTimetableAdapter = new WeeklyTimetableAdapter(getContext(), weeklyTimetableModelArrayList);
                mondayRecycler.setAdapter(weeklyTimetableAdapter);
            }
        });


        return view;
    }
}