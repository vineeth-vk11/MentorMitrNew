package com.MentorMitrAndroid.GoalsAndAffirmationsHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class GoalsFragment extends Fragment {

    ExtendedFloatingActionButton floatingActionButton;

    RecyclerView goalsRecycler;
    ArrayList<GoalAndAffirmationModel> goalAndAffirmationModelArrayList;
    FirebaseFirestore db;

    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goals, container, false);

        floatingActionButton = view.findViewById(R.id.floating_action_button);

        if(getArguments().getString("id")!= null){
            id = getArguments().getString("id");
            floatingActionButton.setVisibility(View.GONE);
        }
        else {
            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGoalOrAffirmationDialog addGoalOrAffirmationDialog = new AddGoalOrAffirmationDialog("goal");
                addGoalOrAffirmationDialog.show(getFragmentManager(), "Add Goal");
            }
        });

        db = FirebaseFirestore.getInstance();
        goalAndAffirmationModelArrayList = new ArrayList<>();

        goalsRecycler = view.findViewById(R.id.goalsRecycler);
        goalsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        goalsRecycler.setHasFixedSize(true);
        goalsRecycler.setNestedScrollingEnabled(false);

        db.collection("GoalsAndAffirmations").document(id)
                .collection("goals").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                goalAndAffirmationModelArrayList.clear();
                for(DocumentSnapshot documentSnapshot: value.getDocuments()){

                    GoalAndAffirmationModel goalAndAffirmationModel = new GoalAndAffirmationModel();

                    goalAndAffirmationModel.setText(documentSnapshot.getString("goal"));
                    goalAndAffirmationModel.setType("goal");

                    goalAndAffirmationModelArrayList.add(goalAndAffirmationModel);

                }

                GoalAndAffirmationAdapter goalAndAffirmationAdapter = new GoalAndAffirmationAdapter(getContext(), goalAndAffirmationModelArrayList);
                goalsRecycler.setAdapter(goalAndAffirmationAdapter);
            }
        });

        return view;
    }
}