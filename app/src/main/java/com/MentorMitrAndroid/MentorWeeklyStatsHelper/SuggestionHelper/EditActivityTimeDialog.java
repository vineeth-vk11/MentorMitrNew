package com.MentorMitrAndroid.MentorWeeklyStatsHelper.SuggestionHelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
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

public class EditActivityTimeDialog extends AppCompatDialogFragment {

    TextView activityName;
    EditText activityTime;

    String activityNameValue, activityTimeValue, id;

    public EditActivityTimeDialog(String activityNameValue, String activityTimeValue, String id) {
        this.activityNameValue = activityNameValue;
        this.activityTimeValue = activityTimeValue;
        this.id = id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_edit_activity_time, null);

        activityName = view.findViewById(R.id.activityName);
        activityTime = view.findViewById(R.id.suggested_time_edit);

        activityName.setText(activityNameValue);
        activityTime.setText(activityTimeValue);

        builder.setView(view)
                .setTitle("Suggest Time")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String enteredActivityTime = activityTime.getText().toString();

                        if(TextUtils.isEmpty(enteredActivityTime)){

                        }
                        else {

                            HashMap<String, Object> data = new HashMap<>();
                            data.put("activityName", activityNameValue);
                            data.put("hours",enteredActivityTime);

                            FirebaseFirestore db;
                            db = FirebaseFirestore.getInstance();

                            db.collection("MentorSuggestions").document(id).collection("data")
                                    .document(activityNameValue).update(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                        }
                    }
                });
        return builder.create();
    }
}
