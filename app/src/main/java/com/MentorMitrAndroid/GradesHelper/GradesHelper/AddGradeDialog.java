package com.MentorMitrAndroid.GradesHelper.GradesHelper;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.util.Calendar;
import java.util.HashMap;

public class AddGradeDialog extends AppCompatDialogFragment {

    String subjectId;

    public AddGradeDialog(String subjectId) {
        this.subjectId = subjectId;
    }

    Button dateSelector;
    EditText marksScored, totalMarks;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_add_grade, null);

        dateSelector = view.findViewById(R.id.selectExamDate);
        marksScored = view.findViewById(R.id.marks_scored_edit);
        totalMarks = view.findViewById(R.id.total_marks_edit);

        dateSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });

        builder.setView(view)
                .setTitle("Add Grade")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String enteredMarksScored = marksScored.getText().toString();
                        String enteredTotalMarks = totalMarks.getText().toString();
                        String selectedDate = dateSelector.getText().toString();

                        if(TextUtils.isEmpty(enteredMarksScored)){

                        }
                        else if(TextUtils.isEmpty(enteredTotalMarks)){

                        }
                        else if(dateSelector.getText().toString().equals("Select Exam Date")){

                        }
                        else{

                            Double p = Double.parseDouble(enteredMarksScored)/Double.parseDouble(enteredTotalMarks);
                            Double totalPercentage = p * 100;

                            HashMap<String, Object> data = new HashMap<>();

                            data.put("totalMarks",enteredTotalMarks);
                            data.put("marksScored",enteredMarksScored);
                            data.put("date",selectedDate);
                            data.put("percentage", totalPercentage);

                            FirebaseFirestore db;
                            db = FirebaseFirestore.getInstance();

                            db.collection("Subjects").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .collection("subjects").document(subjectId).collection("grades").add(data)
                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {

                                        }
                                    });
                        }
                    }
                });
        return builder.create();
    }

    private void handleDateButton(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int correctedMonth = month + 1;

                String date = dayOfMonth+ "/" +  correctedMonth + "/" + year;
                dateSelector.setText(date);
            }
        }, year, month, date);

        datePickerDialog.show();
    }
}
