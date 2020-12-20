package com.MentorMitrAndroid.GradesHelper.SubjectsHelper;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

public class SubjectsViewHolder extends RecyclerView.ViewHolder{

    TextView subjectName;
    CardView subjectCard;

    public SubjectsViewHolder(@NonNull View itemView) {
        super(itemView);

        subjectName = itemView.findViewById(R.id.subject);
        subjectCard = itemView.findViewById(R.id.subjectCard);

    }
}
