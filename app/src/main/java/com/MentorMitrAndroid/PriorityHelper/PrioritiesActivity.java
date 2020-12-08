package com.MentorMitrAndroid.PriorityHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PrioritiesActivity extends AppCompatActivity {

    FloatingActionButton addPriority;
    RecyclerView priorities;
    ArrayList<PriorityModel> priorityModelArrayList;
    FirebaseFirestore db;

    String from;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priorities);

        addPriority = findViewById(R.id.floating_action_button);
        priorities = findViewById(R.id.prioritiesRecycler);

        Intent intent = getIntent();

        if(getIntent().getStringExtra("id") != null){
            userId = intent.getStringExtra("id");
            from = intent.getStringExtra("from");
            addPriority.setVisibility(View.GONE);
        }
        else {
            userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        addPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPriorityDialog addPriorityDialog = new AddPriorityDialog();
                addPriorityDialog.show(getSupportFragmentManager(), "Add Priority");
            }
        });

        db = FirebaseFirestore.getInstance();
        priorities.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        priorities.setHasFixedSize(true);
        priorities.setNestedScrollingEnabled(false);

        priorityModelArrayList = new ArrayList<>();

        db.collection("priorities").document(userId)
                .collection("priorities").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                priorityModelArrayList.clear();
                for(DocumentSnapshot documentSnapshot:value.getDocuments()){
                    PriorityModel priorityModel = new PriorityModel();

                    priorityModel.setDescription(documentSnapshot.getString("description"));
                    priorityModel.setImportance(documentSnapshot.getString("importance"));
                    priorityModel.setUrgency(documentSnapshot.getString("urgency"));

                    priorityModelArrayList.add(priorityModel);
                }

                PrioritiesAdapter prioritiesAdapter = new PrioritiesAdapter(getApplicationContext(), priorityModelArrayList);
                priorities.setAdapter(prioritiesAdapter);
            }
        });
    }
}