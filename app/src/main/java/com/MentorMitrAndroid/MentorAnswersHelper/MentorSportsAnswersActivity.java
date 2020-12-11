package com.MentorMitrAndroid.MentorAnswersHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.MentorMitrAndroid.MentorAnswersHelper.Adapters.MentorSportsAdapter;
import com.MentorMitrAndroid.QuestionnaireHelper.models.MentorSports;
import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MentorSportsAnswersActivity extends AppCompatActivity {

    String type, id;

    RecyclerView answersRecycler;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_sports_answers);


        answersRecycler = findViewById(R.id.answers_recycler);
        answersRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        id = intent.getStringExtra("id");

        List<MentorSports> mentorSportsList = new ArrayList<>();
        MentorSportsAdapter mentorSportsAdapter = new MentorSportsAdapter(this, mentorSportsList);

        db.collection("questions_specific_reply").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                Map<String, Object> fullMap = task.getResult().getData();
                List<Map<String, Object>> list = (List) fullMap.get("response_general");

                for (Map<String, Object> map : list) {
                    mentorSportsList.add(new MentorSports(map.get("answer").toString(), map.get("question").toString(), (int) (long) map.get("serial")));
                }

                answersRecycler.setAdapter(mentorSportsAdapter);
            }
        });

    }
}