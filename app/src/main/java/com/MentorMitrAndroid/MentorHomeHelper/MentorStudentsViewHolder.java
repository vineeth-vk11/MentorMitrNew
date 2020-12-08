package com.MentorMitrAndroid.MentorHomeHelper;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

public class MentorStudentsViewHolder extends RecyclerView.ViewHolder {

    TextView name, type;
    CardView student;

    public MentorStudentsViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.nameOfStudent);
        type = itemView.findViewById(R.id.studentType);
        student = itemView.findViewById(R.id.cardView4);

    }
}
