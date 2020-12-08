package com.MentorMitrAndroid.MentorWeeklyStatsHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MentorWeeklyStatsActivity extends AppCompatActivity {

    RecyclerView mentorWeeklyStatsRecycler;
    FirebaseFirestore db;
    ArrayList<MentorWeeklyStatsModel> mentorWeeklyStatsModelArrayList;

    String id;

    ArrayList<String> activities = new ArrayList<>();
    ArrayList<String> all = new ArrayList<>(100);
    ArrayList<MentorWeeklyActivityModel> mentorWeeklyActivityModelArrayList = new ArrayList<>();

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_weekly_stats_activity);

        for(int i=0; i<100;i++){
            all.add(String.valueOf(i));
        }

        if(getIntent()!=null){
            id = getIntent().getStringExtra("id");
        }

        db = FirebaseFirestore.getInstance();
        mentorWeeklyStatsModelArrayList = new ArrayList<>();
        mentorWeeklyStatsRecycler = findViewById(R.id.weeklyStatsRecycler);
        mentorWeeklyStatsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mentorWeeklyStatsRecycler.setHasFixedSize(true);

        getMonday();
    }

    private void getActivities(){
        db.collection("Activities").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("activites").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    activities.add(documentSnapshot.getString("activity"));
                }
                Log.i("activities",String.valueOf(activities));
                getMonday();
            }
        });
    }
    private void getMonday(){
        db.collection("WeeklyTimetables").document(id)
                .collection("Monday").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    all.set(i,documentSnapshot.getString("activity"));
                    i++;
                }
                getTuesday();
            }
        });
    }

    private void getTuesday(){
        db.collection("WeeklyTimetables").document(id)
                .collection("Tuesday").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    all.set(i,documentSnapshot.getString("activity"));
                }
                getWednesday();
            }
        });
    }

    private void getWednesday(){
        db.collection("WeeklyTimetables").document(id)
                .collection("Wednesday").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    all.set(i,documentSnapshot.getString("activity"));
                }
                getThursday();
            }
        });
    }

    private void getThursday(){
        db.collection("WeeklyTimetables").document(id)
                .collection("Thursday").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    all.set(i,documentSnapshot.getString("activity"));
                }
                getFriday();
            }
        });
    }

    private void getFriday(){
        db.collection("WeeklyTimetables").document(id)
                .collection("Friday").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    all.set(i,documentSnapshot.getString("activity"));
                }
                getSaturday();
            }
        });
    }

    private void getSaturday(){
        db.collection("WeeklyTimetables").document(id)
                .collection("Saturday").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    all.set(i,documentSnapshot.getString("activity"));
                }
                getSunday();
            }
        });
    }

    private void getSunday(){
        db.collection("WeeklyTimetables").document(id)
                .collection("Sunday").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    all.set(i,documentSnapshot.getString("activity"));
                }

                calculate();
            }
        });
    }

    private void calculate(){

        Set<String > list = new HashSet<>(all);

        Log.i("list",String.valueOf(list));

        for(String s: list){
            Log.i(s, String.valueOf(Collections.frequency(list,s)));
        }

    }

}