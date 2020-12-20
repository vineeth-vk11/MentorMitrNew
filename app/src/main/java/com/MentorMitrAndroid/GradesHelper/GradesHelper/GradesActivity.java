package com.MentorMitrAndroid.GradesHelper.GradesHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.MentorMitrAndroid.ActivitiesHelper.DialogAddActivity;
import com.MentorMitrAndroid.GradesHelper.GradesHelper.AddGradeDialog;
import com.MentorMitrAndroid.GradesHelper.SubjectsHelper.SubjectModel;
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

public class GradesActivity extends AppCompatActivity {

    ExtendedFloatingActionButton floatingActionButton;

    String subjectId;

    RecyclerView gradesRecycler;
    FirebaseFirestore db;
    ArrayList<GradeModel> gradeModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        Intent intent = getIntent();
        subjectId = intent.getStringExtra("id");

        floatingActionButton = findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddGradeDialog addGradeDialog = new AddGradeDialog(subjectId);
                addGradeDialog.show(getSupportFragmentManager(), "Add Grade");
            }
        });

        gradesRecycler = findViewById(R.id.gradesRecycler);
        gradesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        gradesRecycler.setHasFixedSize(true);

        db = FirebaseFirestore.getInstance();

        gradeModelArrayList = new ArrayList<>();

        db.collection("Subjects").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("subjects").document(subjectId).collection("grades")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        gradeModelArrayList.clear();

                        for(DocumentSnapshot documentSnapshot: value.getDocuments()){

                            GradeModel gradeModel = new GradeModel();
                            gradeModel.setDateSelected(documentSnapshot.getString("date"));
                            gradeModel.setMarksScored(documentSnapshot.getString("marksScored"));
                            gradeModel.setTotalMarks(documentSnapshot.getString("totalMarks"));
                            gradeModel.setPercentage(documentSnapshot.getDouble("percentage"));

                            gradeModelArrayList.add(gradeModel);
                        }

                        GradesAdapter gradesAdapter = new GradesAdapter(getApplicationContext(), gradeModelArrayList);
                        gradesRecycler.setAdapter(gradesAdapter);
                    }
                });

    }
}