package com.MentorMitrAndroid.StudentsSuggestedTimingsHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.MentorMitrAndroid.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentsSuggestedTimingsActivity extends AppCompatActivity {

    PieChart pieChart;
    HashMap<String, Object> data;
    ArrayList<PieEntry> chartData = new ArrayList<>();

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_suggested_timings);

        pieChart = findViewById(R.id.statsChart);

        db = FirebaseFirestore.getInstance();

        db.collection("MentorSuggestions").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("data").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    chartData.add(new PieEntry(Integer.parseInt(documentSnapshot.getString("hours")), documentSnapshot.getString("activityName")));
                }

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
        });

    }
}