package com.MentorMitrAndroid.GradesHelper.SubjectsHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.MentorMitrAndroid.GradesHelper.SubjectsHelper.AddSubjectDialog;
import com.MentorMitrAndroid.GradesHelper.SubjectsHelper.SubjectModel;
import com.MentorMitrAndroid.GradesHelper.SubjectsHelper.SubjectsAdapter;
import com.MentorMitrAndroid.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SubjectsActivity extends AppCompatActivity {

    ExtendedFloatingActionButton floatingActionButton;
    RecyclerView subjectsRecycler;
    ArrayList<SubjectModel> subjectModelArrayList;
    FirebaseFirestore db;

    String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        floatingActionButton = findViewById(R.id.floating_action_button);

        if(getIntent().getStringExtra("from") != null){
            from = getIntent().getStringExtra("from");
            floatingActionButton.setVisibility(View.INVISIBLE);
        }
        else{
            from = "data";
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSubjectDialog addSubjectDialog = new AddSubjectDialog();
                addSubjectDialog.show(getSupportFragmentManager(), "Add Subject");
            }
        });

        db = FirebaseFirestore.getInstance();

        subjectsRecycler = findViewById(R.id.subjectsRecycler);
        subjectsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        subjectsRecycler.setHasFixedSize(true);

        subjectModelArrayList = new ArrayList<>();

        db.collection("Subjects").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("subjects").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                subjectModelArrayList.clear();

                for(DocumentSnapshot documentSnapshot: value.getDocuments()){

                    SubjectModel subjectModel = new SubjectModel();
                    subjectModel.setSubjectName(documentSnapshot.getString("subject"));
                    subjectModel.setSubjectId(documentSnapshot.getId());

                    subjectModelArrayList.add(subjectModel);
                }

                SubjectsAdapter subjectsAdapter = new SubjectsAdapter(getApplicationContext(), subjectModelArrayList, from);
                subjectsRecycler.setAdapter(subjectsAdapter);

            }
        });
    }
}