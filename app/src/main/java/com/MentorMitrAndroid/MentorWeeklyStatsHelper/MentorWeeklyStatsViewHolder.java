package com.MentorMitrAndroid.MentorWeeklyStatsHelper;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

public class MentorWeeklyStatsViewHolder extends RecyclerView.ViewHolder {
    TextView activityName, activityTime;

    public MentorWeeklyStatsViewHolder(@NonNull View itemView) {
        super(itemView);

        activityName = (TextView) itemView.findViewById(R.id.activityName);
        activityTime = (TextView) itemView.findViewById(R.id.activityTime);

    }
}
