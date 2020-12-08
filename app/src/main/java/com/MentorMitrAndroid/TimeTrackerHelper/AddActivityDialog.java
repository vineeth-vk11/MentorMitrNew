package com.MentorMitrAndroid.TimeTrackerHelper;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

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
import java.util.Calendar;
import java.util.HashMap;

public class AddActivityDialog extends AppCompatDialogFragment {

    AutoCompleteTextView activities;
    Button selectDate, selectStartTime, selectEndTime;

    ArrayList<String> ACTIVITIES = new ArrayList<>();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_add_time_tracker_item, null);

        activities = view.findViewById(R.id.activities_drop_down);
        selectDate = view.findViewById(R.id.button2);
        selectStartTime = view.findViewById(R.id.button3);
        selectEndTime = view.findViewById(R.id.button4);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });

        selectStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeButton();
            }
        });

        selectEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeButton1();
            }
        });

        ACTIVITIES.add("Studying");
        ACTIVITIES.add("Gaming");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.dropdown_menu_popup_item,
                ACTIVITIES
        );

        activities.setAdapter(adapter);
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
                        String activity = activities.getText().toString();
                        String date = selectDate.getText().toString();
                        String startTime = selectStartTime.getText().toString();
                        String endTime = selectEndTime.getText().toString();

                        if(date.equals("Select Date")){

                        }
                        else if(startTime.equals("Select Start Time")){

                        }
                        else if(endTime.equals("Select End Date")){

                        }
                        else if(TextUtils.isEmpty(activity)){

                        }
                        else {

                            HashMap<String, Object> data = new HashMap<>();
                            data.put("activity",activity);
                            data.put("date",date);
                            data.put("startTime",startTime);
                            data.put("endTime",endTime);

                            FirebaseFirestore db;
                            db = FirebaseFirestore.getInstance();

                            db.collection("TimeTrackerData").document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())
                                    .collection("data").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                }
                            });
                        }
                    }
                });
        return builder.create();
    }

    private void handleTimeButton(){
        Calendar calendar = Calendar.getInstance();
        int Hour = calendar.get(Calendar.HOUR);
        int Minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                selectStartTime.setText(time);
            }
        },Hour,Minute,false);
        timePickerDialog.show();
    }

    private void handleTimeButton1(){
        Calendar calendar = Calendar.getInstance();
        int Hour = calendar.get(Calendar.HOUR);
        int Minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                selectEndTime.setText(time);
            }
        },Hour,Minute,false);
        timePickerDialog.show();
    }

    private void handleDateButton(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth+ "/" +  month + "/" + year;
                selectDate.setText(date);
            }
        }, year, month, date);

        datePickerDialog.show();
    }
}
