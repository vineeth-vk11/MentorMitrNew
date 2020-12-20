package com.MentorMitrAndroid.GradesHelper.GradesHelper;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

public class GradesViewHolder extends RecyclerView.ViewHolder {

    TextView date, grade, percentage;

    public GradesViewHolder(@NonNull View itemView) {
        super(itemView);

        date = itemView.findViewById(R.id.date);
        grade = itemView.findViewById(R.id.grade);
        percentage = itemView.findViewById(R.id.percentage);

    }
}
