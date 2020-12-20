package com.MentorMitrAndroid.GradesHelper.SubjectsHelper;

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

public class AddSubjectDialog extends AppCompatDialogFragment {

    EditText subject, goal;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_add_subject, null);

        subject = view.findViewById(R.id.subject_edit);
        goal = view.findViewById(R.id.goal_edit);

        builder.setView(view)
                .setTitle("Add Subject")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String enteredSubject = subject.getText().toString();
                        String enteredGoal = goal.getText().toString();

                        if(TextUtils.isEmpty(enteredSubject)){

                        }
                        else if(TextUtils.isEmpty(enteredGoal)){

                        }
                        else{

                            HashMap<String, Object> data = new HashMap<>();

                            data.put("subject",enteredSubject);
                            data.put("goal",enteredGoal);

                            FirebaseFirestore db;
                            db = FirebaseFirestore.getInstance();

                            db.collection("Subjects").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .collection("subjects").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                }
                            });
                        }
                    }
                });
        return builder.create();

    }
}
