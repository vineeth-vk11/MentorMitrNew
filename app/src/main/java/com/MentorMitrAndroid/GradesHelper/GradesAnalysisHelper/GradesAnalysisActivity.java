package com.MentorMitrAndroid.GradesHelper.GradesAnalysisHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.MentorMitrAndroid.GradesHelper.GradesHelper.GradeModel;
import com.MentorMitrAndroid.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GradesAnalysisActivity extends AppCompatActivity {

    String id;

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList<BarEntry> barEntryArrayList = new ArrayList<>();
    ArrayList<String> labelNames = new ArrayList<>();

    FirebaseFirestore db;
    ArrayList<GradeModel> gradeModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades_analysis);

        id = getIntent().getStringExtra("id");

        db = FirebaseFirestore.getInstance();

        barChart = findViewById(R.id.gradeAnalysisChart);

        getGrades();


    }

    private void getGrades(){
        db.collection("Subjects").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("subjects").document(id).collection("grades")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                gradeModelArrayList.clear();

                for(DocumentSnapshot documentSnapshot: task.getResult()){

                    GradeModel gradeModel = new GradeModel();

                    gradeModel.setDateSelected(documentSnapshot.getString("date"));
                    gradeModel.setMarksScored(documentSnapshot.getString("marksScored"));
                    gradeModel.setTotalMarks(documentSnapshot.getString("totalMarks"));
                    gradeModel.setPercentage(documentSnapshot.getDouble("percentage"));

                    gradeModelArrayList.add(gradeModel);
                }

                setGraphData();
            }
        });

    }

    private void setGraphData(){

        for(int i = 0; i<gradeModelArrayList.size();i++){

            String date = gradeModelArrayList.get(i).getDateSelected();
            Double percentage = gradeModelArrayList.get(i).getPercentage();

            barEntryArrayList.add(new BarEntry(i, Integer.valueOf(percentage.intValue())));
            labelNames.add(String.valueOf(i));

        }

        barDataSet = new BarDataSet(barEntryArrayList, "Grades Analysis");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.getValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);

        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);

        barData = new BarData(barDataSet);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelNames));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelNames.size());
        barChart.animateY(2000);
        barChart.invalidate();

        YAxis yAxis = barChart.getAxisRight();
        yAxis.setEnabled(false);
        yAxis.setDrawGridLines(false);
    }
}