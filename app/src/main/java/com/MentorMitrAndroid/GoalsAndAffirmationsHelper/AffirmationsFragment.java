package com.MentorMitrAndroid.GoalsAndAffirmationsHelper;

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


public class AffirmationsFragment extends Fragment {

    ExtendedFloatingActionButton floatingActionButton;

    RecyclerView affirmationsRecycler;
    ArrayList<GoalAndAffirmationModel> goalAndAffirmationModelArrayList;
    FirebaseFirestore db;

    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_affirmations, container, false);

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
                AddGoalOrAffirmationDialog addGoalOrAffirmationDialog = new AddGoalOrAffirmationDialog("affirmation");
                addGoalOrAffirmationDialog.show(getFragmentManager(), "Add Affirmation");
            }
        });

        db = FirebaseFirestore.getInstance();
        goalAndAffirmationModelArrayList = new ArrayList<>();

        affirmationsRecycler = view.findViewById(R.id.affirmationsRecycler);
        affirmationsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        affirmationsRecycler.setHasFixedSize(true);
        affirmationsRecycler.setNestedScrollingEnabled(false);

        db.collection("GoalsAndAffirmations").document(id)
                .collection("affirmations").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                goalAndAffirmationModelArrayList.clear();
                for(DocumentSnapshot documentSnapshot: value.getDocuments()){

                    GoalAndAffirmationModel goalAndAffirmationModel = new GoalAndAffirmationModel();

                    goalAndAffirmationModel.setText(documentSnapshot.getString("affirmation"));
                    goalAndAffirmationModel.setType("affirmation");

                    goalAndAffirmationModelArrayList.add(goalAndAffirmationModel);

                }

                GoalAndAffirmationAdapter goalAndAffirmationAdapter = new GoalAndAffirmationAdapter(getContext(), goalAndAffirmationModelArrayList);
                affirmationsRecycler.setAdapter(goalAndAffirmationAdapter);
            }
        });

        return view;
    }
}