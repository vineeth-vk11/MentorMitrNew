package com.MentorMitrAndroid.WeeklyTimetableHelper;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

public class WeeklyTimetableViewHolder extends RecyclerView.ViewHolder {

    TextView timing, activityDone;
    ImageButton editButton;

    public WeeklyTimetableViewHolder(@NonNull View itemView) {
        super(itemView);

        timing = itemView.findViewById(R.id.timing);
        activityDone = itemView.findViewById(R.id.activityDone);
        editButton = itemView.findViewById(R.id.editWeeklyItem);
    }
}
