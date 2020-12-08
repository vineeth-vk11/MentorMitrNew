package com.MentorMitrAndroid.PriorityHelper;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

public class PriorityViewHolder extends RecyclerView.ViewHolder {

    TextView desc, importance, urgency;
    Button seeMore;

    public PriorityViewHolder(@NonNull View itemView) {
        super(itemView);

        desc = itemView.findViewById(R.id.desc);
        importance = itemView.findViewById(R.id.importance);
        urgency = itemView.findViewById(R.id.urgency);

    }
}
