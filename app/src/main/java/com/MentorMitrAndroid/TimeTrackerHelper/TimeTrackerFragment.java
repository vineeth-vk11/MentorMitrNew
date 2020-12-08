package com.MentorMitrAndroid.TimeTrackerHelper;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MentorMitrAndroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class TimeTrackerFragment extends Fragment {

    FloatingActionButton floatingActionButton;

    RecyclerView timeTrackerRecycler;
    FirebaseFirestore db;
    ArrayList<TimeTrackerModel> timeTrackerModelArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_tracker, container, false);

        floatingActionButton = view.findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivityDialog addActivityDialog = new AddActivityDialog();
                addActivityDialog.show(getFragmentManager(), "Add Activity");
            }
        });

        timeTrackerModelArrayList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        timeTrackerRecycler = view.findViewById(R.id.timeTrackerRecycler);
        timeTrackerRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        timeTrackerRecycler.setHasFixedSize(true);
        timeTrackerRecycler.setNestedScrollingEnabled(false);

        db.collection("TimeTrackerData").document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())
                .collection("data").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                timeTrackerModelArrayList.clear();

                for(DocumentSnapshot documentSnapshot: value.getDocuments()){
                    TimeTrackerModel timeTrackerModel = new TimeTrackerModel();
                    timeTrackerModel.setActivity(documentSnapshot.getString("activity"));
                    timeTrackerModel.setDate(documentSnapshot.getString("date"));
                    timeTrackerModel.setStartTime(documentSnapshot.getString("startTime"));
                    timeTrackerModel.setEndTime(documentSnapshot.getString("endTime"));

                    timeTrackerModelArrayList.add(timeTrackerModel);
                }

                TimeTrackerAdapter timeTrackerAdapter = new TimeTrackerAdapter(getContext(), timeTrackerModelArrayList);
                timeTrackerRecycler.setAdapter(timeTrackerAdapter);
            }
        });

        return view;
    }
}