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

public class MentorQuestionnaireAnswersActivity extends AppCompatActivity {

    String type, id;

    RecyclerView answersRecycler;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_questionnaire_answers);

        answersRecycler = findViewById(R.id.answers_recycler);
        answersRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        id = intent.getStringExtra("id");

        if(type.equals("School")){
            List<MentorSports> mentorSportsList = new ArrayList<>();
            MentorSportsAdapter mentorSportsAdapter = new MentorSportsAdapter(this, mentorSportsList);

            db.collection("questions_reply").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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

        else if(type.equals("College")){
            List<MentorSports> mentorCollegeQuesList = new ArrayList<>();
            MentorSportsAdapter mentorCollegeQuesAdapter = new MentorSportsAdapter(this, mentorCollegeQuesList);

            db.collection("questions_college_reply").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()){
                        Map<String, Object> fullMap = task.getResult().getData();
                        List<Map<String, Object>> list = (List) fullMap.get("response_general");
                        for (Map<String, Object> map : list) {
                            mentorCollegeQuesList.add(new MentorSports(map.get("answer").toString(), map.get("question").toString(), (int) (long) map.get("serial")));
                        }
                        answersRecycler.setAdapter(mentorCollegeQuesAdapter);
                    }
                }
            });
        }

        else if(type.equals("Working")){
            List<MentorSports> mentorWorkingQuesList = new ArrayList<>();
            MentorSportsAdapter mentorWorkingQuesAdapter = new MentorSportsAdapter(this, mentorWorkingQuesList);

            db.collection("working_professional_reply").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()){
                        Map<String, Object> fullMap = task.getResult().getData();
                        List<Map<String, Object>> list = (List) fullMap.get("response_general");
                        for (Map<String, Object> map : list) {
                            mentorWorkingQuesList.add(new MentorSports(map.get("answer").toString(), map.get("question").toString(), (int) (long) map.get("serial")));
                        }
                        answersRecycler.setAdapter(mentorWorkingQuesAdapter);
                    }
                }
            });
        }
    }
}