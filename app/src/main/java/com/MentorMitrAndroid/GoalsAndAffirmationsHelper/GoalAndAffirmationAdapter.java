package com.MentorMitrAndroid.GoalsAndAffirmationsHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

import java.util.ArrayList;

public class GoalAndAffirmationAdapter extends RecyclerView.Adapter<GoalAndAffirmationViewHolder> {

    Context context;
    ArrayList<GoalAndAffirmationModel> goalAndAffirmationModelArrayList;

    public GoalAndAffirmationAdapter(Context context, ArrayList<GoalAndAffirmationModel> goalAndAffirmationModelArrayList) {
        this.context = context;
        this.goalAndAffirmationModelArrayList = goalAndAffirmationModelArrayList;
    }

    @NonNull
    @Override
    public GoalAndAffirmationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_goal_or_affirmation,parent, false);
        return new GoalAndAffirmationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalAndAffirmationViewHolder holder, int position) {
        holder.text.setText(goalAndAffirmationModelArrayList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return goalAndAffirmationModelArrayList.size();
    }
}
