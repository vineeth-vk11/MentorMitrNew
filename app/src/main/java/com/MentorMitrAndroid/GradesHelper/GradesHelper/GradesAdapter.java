package com.MentorMitrAndroid.GradesHelper.GradesHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

import java.util.ArrayList;

public class GradesAdapter extends RecyclerView.Adapter<GradesViewHolder> {

    Context context;
    ArrayList<GradeModel> gradeModelArrayList;

    public GradesAdapter(Context context, ArrayList<GradeModel> gradeModelArrayList) {
        this.context = context;
        this.gradeModelArrayList = gradeModelArrayList;
    }

    @NonNull
    @Override
    public GradesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_grade, parent, false);
        return new GradesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradesViewHolder holder, int position) {
        holder.date.setText(gradeModelArrayList.get(position).getDateSelected());
        holder.grade.setText(gradeModelArrayList.get(position).getMarksScored() + "/" + gradeModelArrayList.get(position).getTotalMarks());
        holder.percentage.setText(String.valueOf(gradeModelArrayList.get(position).getPercentage() + "%"));
    }

    @Override
    public int getItemCount() {
        return gradeModelArrayList.size();
    }
}
