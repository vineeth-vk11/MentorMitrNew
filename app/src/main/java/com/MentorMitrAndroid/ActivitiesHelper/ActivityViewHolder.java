package com.MentorMitrAndroid.ActivitiesHelper;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

public class ActivityViewHolder extends RecyclerView.ViewHolder{

    TextView activity;

    public ActivityViewHolder(@NonNull View itemView) {
        super(itemView);

        activity = (TextView) itemView.findViewById(R.id.activity);
    }
}
