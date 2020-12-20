package com.MentorMitrAndroid.MentorWeeklyStatsHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.MentorMitrAndroid.MentorWeeklyStatsHelper.SuggestionHelper.SuggestionsModel;
import com.MentorMitrAndroid.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MentorWeeklyStatsActivity extends AppCompatActivity {

    RecyclerView mentorWeeklyStatsRecycler;
    FirebaseFirestore db;
    ArrayList<MentorWeeklyStatsModel> mentorWeeklyStatsModelArrayList;

    String id;

    ArrayList<String> activities = new ArrayList<>();
    ArrayList<MentorWeeklyActivityModel> mentorWeeklyActivityModelArrayList = new ArrayList<>();

    PieChart pieChart;
    HashMap<String, Object> data;
    ArrayList<PieEntry> chartData = new ArrayList<>();

    Button suggest;
    ArrayList<SuggestionsModel> suggestionsModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_weekly_stats_activity);

        pieChart = findViewById(R.id.statsChart);
        suggest = findViewById(R.id.suggest);

        if(getIntent()!=null){
            id = getIntent().getStringExtra("id");
        }

        suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MentorWeeklySuggestionsActivity.class);
                intent.putExtra("data",suggestionsModelArrayList);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });


        db = FirebaseFirestore.getInstance();

        getActivities();
    }


    private void getActivities(){
        db.collection("Activities").document(id)
                .collection("activites").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    activities.add(documentSnapshot.getString("activity"));
                }
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
                    MentorWeeklyActivityModel mentorWeeklyActivityModel = new MentorWeeklyActivityModel();
                    mentorWeeklyActivityModel.setActivity(documentSnapshot.getString("activity"));
                    mentorWeeklyActivityModelArrayList.add(mentorWeeklyActivityModel);
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
                    MentorWeeklyActivityModel mentorWeeklyActivityModel = new MentorWeeklyActivityModel();
                    mentorWeeklyActivityModel.setActivity(documentSnapshot.getString("activity"));
                    mentorWeeklyActivityModelArrayList.add(mentorWeeklyActivityModel);
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
                    MentorWeeklyActivityModel mentorWeeklyActivityModel = new MentorWeeklyActivityModel();
                    mentorWeeklyActivityModel.setActivity(documentSnapshot.getString("activity"));
                    mentorWeeklyActivityModelArrayList.add(mentorWeeklyActivityModel);
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
                    MentorWeeklyActivityModel mentorWeeklyActivityModel = new MentorWeeklyActivityModel();
                    mentorWeeklyActivityModel.setActivity(documentSnapshot.getString("activity"));
                    mentorWeeklyActivityModelArrayList.add(mentorWeeklyActivityModel);
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
                    MentorWeeklyActivityModel mentorWeeklyActivityModel = new MentorWeeklyActivityModel();
                    mentorWeeklyActivityModel.setActivity(documentSnapshot.getString("activity"));
                    mentorWeeklyActivityModelArrayList.add(mentorWeeklyActivityModel);
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
                    MentorWeeklyActivityModel mentorWeeklyActivityModel = new MentorWeeklyActivityModel();
                    mentorWeeklyActivityModel.setActivity(documentSnapshot.getString("activity"));
                    mentorWeeklyActivityModelArrayList.add(mentorWeeklyActivityModel);
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
                    MentorWeeklyActivityModel mentorWeeklyActivityModel = new MentorWeeklyActivityModel();
                    mentorWeeklyActivityModel.setActivity(documentSnapshot.getString("activity"));
                    mentorWeeklyActivityModelArrayList.add(mentorWeeklyActivityModel);
                }

                calculate();
            }
        });
    }

    private void calculate(){

        data = new HashMap<>();

        for(int i=0;i<activities.size();i++){
            int x=0;
            for(int j=0; j<mentorWeeklyActivityModelArrayList.size();j++){
                if(mentorWeeklyActivityModelArrayList.get(j).getActivity().equals(activities.get(i))){
                    x=x+1;
                }
            }
            Log.i(activities.get(i), String.valueOf(x));
            data.put(activities.get(i), x);
            chartData.add(new PieEntry(x,activities.get(i)));

            SuggestionsModel suggestionsModel = new SuggestionsModel();
            suggestionsModel.setActivityName(activities.get(i));
            suggestionsModel.setHours(String.valueOf(x));

            suggestionsModelArrayList.add(suggestionsModel);

            setGraph();
        }
    }

    private void setGraph(){
        PieDataSet pieDataSet = new PieDataSet(chartData,"" );
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Weekly");
        pieChart.animate();
        pieChart.invalidate();

    }
}