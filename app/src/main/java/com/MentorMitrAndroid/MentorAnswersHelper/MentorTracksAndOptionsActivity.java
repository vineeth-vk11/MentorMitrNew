package com.MentorMitrAndroid.MentorAnswersHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MentorTracksAndOptionsActivity extends AppCompatActivity {

    TextView optionsSelected;
    FirebaseFirestore db;

    ArrayList<String> data;
    String options = "";

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_tracks_and_options);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        optionsSelected = findViewById(R.id.optionsText);

        db = FirebaseFirestore.getInstance();

        db.collection("TracksAndOptions").document(id)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();

                if(documentSnapshot.exists()){
                    data = (ArrayList<String>) documentSnapshot.get(("options"));

                    for(int i = 0; i<data.size(); i++){
                        if( i == data.size()-1){
                            options = options + data.get(i);
                        }
                        else {
                            options = options + data.get(i) + ",";
                        }
                    }

                    optionsSelected.setText(options);
                }
            }
        });
    }
}