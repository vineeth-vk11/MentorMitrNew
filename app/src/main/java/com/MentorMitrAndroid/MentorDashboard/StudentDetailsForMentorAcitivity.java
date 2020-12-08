package com.MentorMitrAndroid.MentorDashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.MentorMitrAndroid.ActivitiesHelper.ActivitiesActivity;
import com.MentorMitrAndroid.GoalsAndAffirmationsHelper.GoalsAndAffirmationsActivity;
import com.MentorMitrAndroid.MentorWeeklyStatsHelper.MentorWeeklyStatsActivity;
import com.MentorMitrAndroid.PriorityHelper.PrioritiesActivity;
import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.WeeklyTimetableHelper.WeeklyTimetableActivity;

public class StudentDetailsForMentorAcitivity extends AppCompatActivity {

    CardView priority, goalsAndAffirmations, weeklyTimetable, stats;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details_for_mentor_acitivity);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        priority = findViewById(R.id.priority);
        goalsAndAffirmations = findViewById(R.id.goals);
        weeklyTimetable = findViewById(R.id.weeklyTimetable);
        stats = findViewById(R.id.stats);

        priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrioritiesActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("from","Mentor");
                startActivity(intent);
            }
        });

        goalsAndAffirmations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GoalsAndAffirmationsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("from","Mentor");
                startActivity(intent);
            }
        });
        
        weeklyTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WeeklyTimetableActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("from","Mentor");
                startActivity(intent);
            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MentorWeeklyStatsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("from","Mentor");
                startActivity(intent);
            }
        });
    }
}