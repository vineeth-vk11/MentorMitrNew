package com.MentorMitrAndroid.WeeklyTimetableHelper;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

public class WeeklyTimetableViewHolder extends RecyclerView.ViewHolder {

    TextView timing, activityDone;

    public WeeklyTimetableViewHolder(@NonNull View itemView) {
        super(itemView);

        timing = itemView.findViewById(R.id.timing);
        activityDone = itemView.findViewById(R.id.activityDone);

    }
}
