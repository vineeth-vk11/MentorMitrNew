package com.MentorMitrAndroid.PriorityHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

import java.util.ArrayList;

public class PrioritiesAdapter extends RecyclerView.Adapter<PriorityViewHolder> {

    Context context;
    ArrayList<PriorityModel> priorityModelArrayList;

    public PrioritiesAdapter(Context context, ArrayList<PriorityModel> priorityModelArrayList) {
        this.context = context;
        this.priorityModelArrayList = priorityModelArrayList;
    }

    @NonNull
    @Override
    public PriorityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_priority, parent, false);
        return new PriorityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriorityViewHolder holder, int position) {
        holder.desc.setText(priorityModelArrayList.get(position).getDescription());
        holder.urgency.setText(priorityModelArrayList.get(position).getUrgency());
        holder.importance.setText(priorityModelArrayList.get(position).getImportance());

    }

    @Override
    public int getItemCount() {
        return priorityModelArrayList.size();
    }
}
