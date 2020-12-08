package com.MentorMitrAndroid.BlogsHelper;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

public class BlogDescriptionViewHolder extends RecyclerView.ViewHolder {

    TextView descItem;

    public BlogDescriptionViewHolder(@NonNull View itemView) {
        super(itemView);

        descItem = itemView.findViewById(R.id.descItem);

    }
}
