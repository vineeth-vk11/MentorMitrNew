package com.MentorMitrAndroid.MentorWeeklyStatsHelper.SuggestionHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

import java.util.ArrayList;

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsViewHolder> {

    Context context;
    ArrayList<SuggestionsModel> suggestionsModelArrayList;
    FragmentManager fragmentManager;
    String id;

    public SuggestionsAdapter(Context context, ArrayList<SuggestionsModel> suggestionsModelArrayList, FragmentManager fragmentManager, String id) {
        this.context = context;
        this.suggestionsModelArrayList = suggestionsModelArrayList;
        this.fragmentManager = fragmentManager;
        this.id = id;
    }

    @NonNull
    @Override
    public SuggestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_weekly_suggestion_mentor, parent, false);
        return new SuggestionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionsViewHolder holder, int position) {
        holder.activityName.setText(suggestionsModelArrayList.get(position).getActivityName());
        holder.hours.setText(suggestionsModelArrayList.get(position).getHours() + "Hrs");

        holder.editHours.setImageResource(R.drawable.ic_baseline_edit_24);

        holder.editHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditActivityTimeDialog editActivityTimeDialog = new EditActivityTimeDialog(suggestionsModelArrayList.get(position).getActivityName(),
                        suggestionsModelArrayList.get(position).getHours(), id);
                editActivityTimeDialog.show(fragmentManager,"Add");

            }
        });
    }

    @Override
    public int getItemCount() {
        return suggestionsModelArrayList.size();
    }
}
