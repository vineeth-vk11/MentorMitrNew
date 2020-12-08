package com.MentorMitrAndroid.WeeklyTimetableHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

import java.util.ArrayList;

public class WeeklyTimetableAdapter extends RecyclerView.Adapter<WeeklyTimetableViewHolder> {

    Context context;
    ArrayList<WeeklyTimetableModel> weeklyTimetableModelArrayList;

    public WeeklyTimetableAdapter(Context context, ArrayList<WeeklyTimetableModel> weeklyTimetableModelArrayList) {
        this.context = context;
        this.weeklyTimetableModelArrayList = weeklyTimetableModelArrayList;
    }

    @NonNull
    @Override
    public WeeklyTimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_weekly_timetable, parent, false);
        return new WeeklyTimetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyTimetableViewHolder holder, int position) {
        holder.timing.setText(weeklyTimetableModelArrayList.get(position).getTime());
        holder.activityDone.setText(weeklyTimetableModelArrayList.get(position).getActivity());
    }

    @Override
    public int getItemCount() {
        return weeklyTimetableModelArrayList.size();
    }
}
