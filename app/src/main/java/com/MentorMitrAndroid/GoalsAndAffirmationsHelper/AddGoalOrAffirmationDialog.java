package com.MentorMitrAndroid.GoalsAndAffirmationsHelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AddGoalOrAffirmationDialog extends AppCompatDialogFragment {

    String type;

    public AddGoalOrAffirmationDialog(String type) {
        this.type = type;
    }

    String title;
    EditText text;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_add_goal_or_affirmation, null);

        text = view.findViewById(R.id.goal_or_affirmation_edit);

        if (type.equals("goal")){
            title = "Add Goal";
            text.setHint("Enter Goal");
        }
        else {
            title = "Add Affirmation";
            text.setHint("Enter Affirmation");
        }

        builder.setView(view)
                .setTitle(title)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String enteredText = text.getText().toString();

                        if(TextUtils.isEmpty(enteredText)){

                        }
                        else{

                            HashMap<String, Object> data = new HashMap<>();

                            data.put(type,enteredText);
                            data.put("type",type);

                            if(type.equals("goal")){

                                FirebaseFirestore db;
                                db = FirebaseFirestore.getInstance();

                                db.collection("GoalsAndAffirmations").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("goals").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {

                                    }
                                });
                            }

                            else {
                                FirebaseFirestore db;
                                db = FirebaseFirestore.getInstance();

                                db.collection("GoalsAndAffirmations").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("affirmations").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {

                                    }
                                });
                            }
                        }

                    }
                });
        return builder.create();
    }




}
