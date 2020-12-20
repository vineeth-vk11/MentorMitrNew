package com.MentorMitrAndroid.MentorWeeklyStatsHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.MentorMitrAndroid.MentorWeeklyStatsHelper.SuggestionHelper.SuggestionsAdapter;
import com.MentorMitrAndroid.MentorWeeklyStatsHelper.SuggestionHelper.SuggestionsModel;
import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MentorWeeklySuggestionsActivity extends AppCompatActivity {

    RecyclerView weeklySuggestionsRecycler;
    FirebaseFirestore db;

    ArrayList<SuggestionsModel> suggestionsModelArrayList;

    ArrayList<SuggestionsModel> suggestionsModelArrayList1 = new ArrayList<>();

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_weekly_suggestions);

        id = getIntent().getStringExtra("id");

        Intent intent = getIntent();
        suggestionsModelArrayList = (ArrayList<SuggestionsModel>)intent.getSerializableExtra("data");

        db = FirebaseFirestore.getInstance();

        weeklySuggestionsRecycler = findViewById(R.id.weeklySuggestionsRecycler);
        weeklySuggestionsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        weeklySuggestionsRecycler.setHasFixedSize(true);

        db.collection("MentorSuggestions").document(id)
                .collection("data").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                suggestionsModelArrayList1.clear();

                for(DocumentSnapshot documentSnapshot: value.getDocuments()){
                    SuggestionsModel suggestionsModel = new SuggestionsModel();

                    suggestionsModel.setActivityName(documentSnapshot.getString("activityName"));
                    suggestionsModel.setHours(documentSnapshot.getString("hours"));

                    suggestionsModelArrayList1.add(suggestionsModel);
                }
                SuggestionsAdapter suggestionsAdapter = new SuggestionsAdapter(getApplicationContext(), suggestionsModelArrayList1, getSupportFragmentManager(),id);
                weeklySuggestionsRecycler.setAdapter(suggestionsAdapter);
            }
        });

        db.collection("MentorSuggestions").document(id).collection("data").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.getResult().size() != 0){
                    Log.i("it", "exists");

                    if(suggestionsModelArrayList1.size() == 0){
                        db.collection("MentorSuggestions").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .collection("data").addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                for(DocumentSnapshot documentSnapshot: value.getDocuments()){
                                    SuggestionsModel suggestionsModel = new SuggestionsModel();

                                    suggestionsModel.setActivityName(documentSnapshot.getString("activityName"));
                                    suggestionsModel.setHours(documentSnapshot.getString("hours"));

                                    suggestionsModelArrayList1.add(suggestionsModel);
                                }
                                SuggestionsAdapter suggestionsAdapter = new SuggestionsAdapter(getApplicationContext(), suggestionsModelArrayList1, getSupportFragmentManager(),id);
                                weeklySuggestionsRecycler.setAdapter(suggestionsAdapter);
                            }
                        });
                    }
                }
                else {
                    Log.i("not", "exists");
                    for(int i=0; i<suggestionsModelArrayList.size();i++){
                        db.collection("MentorSuggestions").document(id)
                                .collection("data").document(suggestionsModelArrayList.get(i).getActivityName()).set(suggestionsModelArrayList.get(i))
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });
                    }
                    SuggestionsAdapter suggestionsAdapter = new SuggestionsAdapter(getApplicationContext(), suggestionsModelArrayList, getSupportFragmentManager(),id);
                    weeklySuggestionsRecycler.setAdapter(suggestionsAdapter);
                }
            }
        });
    }
}