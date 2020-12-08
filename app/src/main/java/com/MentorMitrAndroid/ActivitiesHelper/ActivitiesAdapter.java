package com.MentorMitrAndroid.ActivitiesHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

import java.util.ArrayList;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivityViewHolder> {

    Context context;
    ArrayList<ActivityModel> activityModelArrayList;

    public ActivitiesAdapter(Context context, ArrayList<ActivityModel> activityModelArrayList) {
        this.context = context;
        this.activityModelArrayList = activityModelArrayList;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_activity, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        holder.activity.setText(activityModelArrayList.get(position).getActivityName());
    }

    @Override
    public int getItemCount() {
        return activityModelArrayList.size();
    }
}
