package com.MentorMitrAndroid.TimeTrackerHelper;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

public class TimeTrackerViewHolder extends RecyclerView.ViewHolder {
    TextView activity, dateAndTime;
    public TimeTrackerViewHolder(@NonNull View itemView) {
        super(itemView);

        activity = itemView.findViewById(R.id.activity);
        dateAndTime = itemView.findViewById(R.id.dateAndTime);
    }
}
