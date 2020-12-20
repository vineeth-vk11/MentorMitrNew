package com.MentorMitrAndroid.MentorWeeklyStatsHelper.SuggestionHelper;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

public class SuggestionsViewHolder extends RecyclerView.ViewHolder {

    TextView activityName, hours;
    ImageButton editHours;

    public SuggestionsViewHolder(@NonNull View itemView) {
        super(itemView);

        activityName = itemView.findViewById(R.id.activityName);
        hours = itemView.findViewById(R.id.activityTime);
        editHours = itemView.findViewById(R.id.editHoursButton);

    }
}
