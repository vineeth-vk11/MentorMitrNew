package com.MentorMitrAndroid.GoalsAndAffirmationsHelper;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

public class GoalAndAffirmationViewHolder extends RecyclerView.ViewHolder {

    TextView text;

    public GoalAndAffirmationViewHolder(@NonNull View itemView) {
        super(itemView);

        text = itemView.findViewById(R.id.goal_or_affirmation);

    }
}
