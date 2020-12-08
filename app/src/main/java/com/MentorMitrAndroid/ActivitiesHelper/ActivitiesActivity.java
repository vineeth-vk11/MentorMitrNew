package com.MentorMitrAndroid.ActivitiesHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.MentorMitrAndroid.GoalsAndAffirmationsHelper.AddGoalOrAffirmationDialog;
import com.MentorMitrAndroid.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ActivitiesActivity extends AppCompatActivity {

    ExtendedFloatingActionButton floatingActionButton;
    ArrayList<ActivityModel> activityModelArrayList;
    RecyclerView activitiesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        floatingActionButton = findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddActivity dialogAddActivity = new DialogAddActivity();
                dialogAddActivity.show(getSupportFragmentManager(), "Add Activity");
            }
        });

        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();

        activityModelArrayList = new ArrayList<>();

        activitiesRecycler = findViewById(R.id.activitiesRecycler);
        activitiesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        activitiesRecycler.setHasFixedSize(true);

        db.collection("Activities").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("activites").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                activityModelArrayList.clear();
                for(DocumentSnapshot documentSnapshot: value.getDocuments()){

                    ActivityModel activityModel = new ActivityModel();

                    activityModel.setActivityName(documentSnapshot.getString("activity"));
                    activityModelArrayList.add(activityModel);
                }

                ActivitiesAdapter activitiesAdapter = new ActivitiesAdapter(getApplicationContext(), activityModelArrayList);
                activitiesRecycler.setAdapter(activitiesAdapter);

            }
        });
    }
}