package com.MentorMitrAndroid.PriorityHelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class AddPriorityDialog extends AppCompatDialogFragment {

    ArrayList<String> IMPORTANCE = new ArrayList<>();
    ArrayList<String> URGENCY = new ArrayList<>();

    AutoCompleteTextView importance, urgency;
    EditText description;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_add_priority, null);

        importance = view.findViewById(R.id.importance_drop_down);
        urgency = view.findViewById(R.id.urgency_drop_down);
        description = view.findViewById(R.id.description);

        IMPORTANCE.add("Important");
        IMPORTANCE.add("Not Important");

        URGENCY.add("Urgent");
        URGENCY.add("Not Urgent");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.dropdown_menu_popup_item,
                IMPORTANCE
        );

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                getActivity(),
                R.layout.dropdown_menu_popup_item,
                URGENCY
        );

        importance.setAdapter(adapter);
        urgency.setAdapter(adapter1);

        builder.setView(view)
                .setTitle("Add Priority")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String importanceValue = importance.getText().toString();
                        String urgencyValue = urgency.getText().toString();
                        String descriptionValue = description.getText().toString();

                        if(TextUtils.isEmpty(importanceValue)){
                            Toast.makeText(getContext(), "Select Importance", Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.isEmpty(urgencyValue)){
                            Toast.makeText(getContext(), "Select Urgency", Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.isEmpty(descriptionValue)){
                            Toast.makeText(getContext(), "Enter description", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            HashMap<String, Object> data = new HashMap<>();
                            data.put("importance", importanceValue);
                            data.put("urgency", urgencyValue);
                            data.put("description", descriptionValue);

                            FirebaseFirestore db;
                            db = FirebaseFirestore.getInstance();


                            db.collection("priorities").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .collection("priorities").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
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
