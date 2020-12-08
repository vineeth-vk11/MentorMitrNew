package com.MentorMitrAndroid.MentorWeeklyStatsHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.WeeklyTimetableHelper.WeeklyTimetableModel;
import com.MentorMitrAndroid.WeeklyTimetableHelper.WeeklyTimetableViewHolder;

import java.util.ArrayList;

public class MentorWeeklyStatsAdapter extends RecyclerView.Adapter<MentorWeeklyStatsViewHolder> {

    Context context;
    ArrayList<MentorWeeklyStatsModel> mentorWeeklyStatsModelArrayList;

    public MentorWeeklyStatsAdapter(Context context, ArrayList<MentorWeeklyStatsModel> mentorWeeklyStatsModelArrayList) {
        this.context = context;
        this.mentorWeeklyStatsModelArrayList = mentorWeeklyStatsModelArrayList;
    }

    @NonNull
    @Override
    public MentorWeeklyStatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_mentor_weekly_stats, parent, false);
        return new MentorWeeklyStatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentorWeeklyStatsViewHolder holder, int position) {
        holder.activityName.setText(mentorWeeklyStatsModelArrayList.get(position).getActivity());
        holder.activityTime.setText(mentorWeeklyStatsModelArrayList.get(position).getHours());
    }

    @Override
    public int getItemCount() {
        return mentorWeeklyStatsModelArrayList.size();
    }
}
