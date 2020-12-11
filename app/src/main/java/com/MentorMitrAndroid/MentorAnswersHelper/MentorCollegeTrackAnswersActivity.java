package com.MentorMitrAndroid.MentorAnswersHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.MentorMitrAndroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MentorCollegeTrackAnswersActivity extends AppCompatActivity {

    TextView serial, colleges, courses, selection, achieve, improve;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_college_track_answers);

        serial = findViewById(R.id.serialNumber);
        colleges = findViewById(R.id.colleges);
        courses = findViewById(R.id.courses);
        selection = findViewById(R.id.selection);
        achieve = findViewById(R.id.achieve);
        improve = findViewById(R.id.improvement);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        FirebaseDatabase db;
        db = FirebaseDatabase.getInstance();

        db.getReference().child("college").child(id).child("college_options").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    serial.setText(snapshot.child("serialNumber").getValue().toString());
                    colleges.setText(snapshot.child("college").getValue().toString());
                    courses.setText(snapshot.child("course").getValue().toString());
                    selection.setText(snapshot.child("selection").getValue().toString());
                    achieve.setText(snapshot.child("achieve").getValue().toString());
                    improve.setText(snapshot.child("improve").getValue().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}