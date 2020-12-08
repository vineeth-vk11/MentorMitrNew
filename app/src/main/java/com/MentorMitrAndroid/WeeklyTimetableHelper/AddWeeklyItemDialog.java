package com.MentorMitrAndroid.WeeklyTimetableHelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class AddWeeklyItemDialog extends AppCompatDialogFragment {

    AutoCompleteTextView timings, activities;

    ArrayList<String> timingsList = new ArrayList<>();
    ArrayList<String> activitiesList;

    String dayOfWeek;

    public AddWeeklyItemDialog(ArrayList<String> activitiesList, String dayOfWeek) {
        this.activitiesList = activitiesList;
        this.dayOfWeek = dayOfWeek;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_add_weekly_timetable_item, null);

        timings = view.findViewById(R.id.timings_drop_down);
        activities = view.findViewById(R.id.activities_drop_down);

        timingsList.add("4AM - 5AM");
        timingsList.add("5AM - 6AM");
        timingsList.add("6AM - 7AM");
        timingsList.add("7AM - 8AM");
        timingsList.add("8AM - 9AM");
        timingsList.add("9AM - 10AM");
        timingsList.add("10AM - 11AM");
        timingsList.add("11AM - 12PM");
        timingsList.add("12PM - 1PM");
        timingsList.add("1PM - 2PM");
        timingsList.add("2PM - 3PM");
        timingsList.add("3PM - 4PM");
        timingsList.add("4PM - 5PM");
        timingsList.add("5PM - 6PM");
        timingsList.add("6PM - 7PM");
        timingsList.add("7PM - 8PM");
        timingsList.add("8PM - 9PM");
        timingsList.add("9PM - 10PM");
        timingsList.add("10PM - 11PM");
        timingsList.add("11PM - 12AM");
        timingsList.add("12AM - 1AM");
        timingsList.add("1AM - 2AM");
        timingsList.add("2AM - 3PM");
        timingsList.add("3AM - 4AM");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.dropdown_menu_popup_item,
                timingsList
        );

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                getActivity(),
                R.layout.dropdown_menu_popup_item,
                activitiesList
        );

        timings.setAdapter(adapter);
        activities.setAdapter(adapter1);

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

                        String selectedTiming = timings.getText().toString().trim();
                        String activity = activities.getText().toString();

                        if(TextUtils.isEmpty(selectedTiming)){

                        }
                        else if(TextUtils.isEmpty(activity)){

                        }
                        else {
                            int serial = timingsList.indexOf(selectedTiming);

                            HashMap<String, Object> data = new HashMap<>();
                            data.put("timing", selectedTiming);
                            data.put("activity", activity);
                            data.put("number",String.valueOf(serial));

                            FirebaseFirestore db;
                            db = FirebaseFirestore.getInstance();

                            db.collection("WeeklyTimetables").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .collection(dayOfWeek).document(String.valueOf(serial)).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                        }
                    }
                });
        return builder.create();    }
}
