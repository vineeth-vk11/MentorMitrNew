package com.MentorMitrAndroid.ActivitiesHelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class DialogAddActivity extends AppCompatDialogFragment {

    EditText activity;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_add_activity, null);

        activity = view.findViewById(R.id.activity_edit);

        builder.setView(view)
                .setTitle("Add Activity")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String enteredText = activity.getText().toString();

                        if(TextUtils.isEmpty(enteredText)){

                        }
                        else{

                            HashMap<String, Object> data = new HashMap<>();

                            data.put("activity",enteredText);

                            FirebaseFirestore db;
                            db = FirebaseFirestore.getInstance();

                            db.collection("Activities").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .collection("activites").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
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
