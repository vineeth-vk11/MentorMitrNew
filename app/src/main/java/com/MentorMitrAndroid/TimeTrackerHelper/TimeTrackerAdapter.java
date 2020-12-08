package com.MentorMitrAndroid.TimeTrackerHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

import java.util.ArrayList;

public class TimeTrackerAdapter extends RecyclerView.Adapter<TimeTrackerViewHolder> {

    Context context;
    ArrayList<TimeTrackerModel> timeTrackerModelArrayList;

    public TimeTrackerAdapter(Context context, ArrayList<TimeTrackerModel> timeTrackerModelArrayList) {
        this.context = context;
        this.timeTrackerModelArrayList = timeTrackerModelArrayList;
    }

    @NonNull
    @Override
    public TimeTrackerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_time_tracker,parent,false);
        return new TimeTrackerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTrackerViewHolder holder, int position) {
        holder.activity.setText(timeTrackerModelArrayList.get(position).getActivity());

        String dateAndTime = timeTrackerModelArrayList.get(position).getStartTime() + " - "+ timeTrackerModelArrayList.get(position).getEndTime() + ","+timeTrackerModelArrayList.get(position).getDate();
        holder.dateAndTime.setText(dateAndTime);
    }

    @Override
    public int getItemCount() {
        return timeTrackerModelArrayList.size();
    }
}
