package com.MentorMitrAndroid.WeeklyTimetableHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

import java.util.ArrayList;

public class WeeklyTimetableAdapter extends RecyclerView.Adapter<WeeklyTimetableViewHolder> {

    Context context;
    ArrayList<WeeklyTimetableModel> weeklyTimetableModelArrayList;
    String from;
    ArrayList<String> activities = new ArrayList<>();
    FragmentManager fragmentManager;
    String id;
    String day;

    public WeeklyTimetableAdapter(Context context, ArrayList<WeeklyTimetableModel> weeklyTimetableModelArrayList, String from, ArrayList<String> activities, FragmentManager fragmentManager,
                                  String id, String day) {
        this.context = context;
        this.weeklyTimetableModelArrayList = weeklyTimetableModelArrayList;
        this.from = from;
        this.activities = activities;
        this.fragmentManager = fragmentManager;
        this.id = id;
        this.day = day;
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

//        if(from.equals("Mentor")){
//            holder.editButton.setVisibility(View.VISIBLE);
//        }
//
//        holder.editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AddWeeklyItemDialog addWeeklyItemDialog = new AddWeeklyItemDialog(activities,day,weeklyTimetableModelArrayList.get(position).getActivity()
//                        ,weeklyTimetableModelArrayList.get(position).getTime(),id);
//                addWeeklyItemDialog.show(fragmentManager, "Edit Activity");
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return weeklyTimetableModelArrayList.size();
    }
}
