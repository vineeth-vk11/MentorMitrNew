package com.MentorMitrAndroid.MentorDashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.MentorMitrAndroid.GoalsAndAffirmationsHelper.GoalsAndAffirmationsActivity;
import com.MentorMitrAndroid.MentorAnswersHelper.MentorCollegeTrackAnswersActivity;
import com.MentorMitrAndroid.MentorAnswersHelper.MentorQuestionnaireAnswersActivity;
import com.MentorMitrAndroid.MentorAnswersHelper.MentorSportsAnswersActivity;
import com.MentorMitrAndroid.MentorAnswersHelper.MentorTracksAndOptionsActivity;
import com.MentorMitrAndroid.MentorWeeklyStatsHelper.MentorWeeklyStatsActivity;
import com.MentorMitrAndroid.PriorityHelper.PrioritiesActivity;
import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.WeeklyTimetableHelper.WeeklyTimetableActivity;

public class StudentDetailsForMentorAcitivity extends AppCompatActivity {

    CardView questionnaire, sports, tracks, college, priority, goalsAndAffirmations, weeklyTimetable, stats;

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
        questionnaire = findViewById(R.id.materialCardView);
        sports = findViewById(R.id.sports);
        tracks = findViewById(R.id.tracks);
        college = findViewById(R.id.college);

        college.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MentorCollegeTrackAnswersActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("type","School");
                intent.putExtra("from","Mentor");
                startActivity(intent);
            }
        });

        tracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MentorTracksAndOptionsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("type","School");
                intent.putExtra("from","Mentor");
                startActivity(intent);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MentorSportsAnswersActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("type","School");
                intent.putExtra("from","Mentor");
                startActivity(intent);
            }
        });

        questionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MentorQuestionnaireAnswersActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("type","School");
                intent.putExtra("from","Mentor");
                startActivity(intent);
            }
        });

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