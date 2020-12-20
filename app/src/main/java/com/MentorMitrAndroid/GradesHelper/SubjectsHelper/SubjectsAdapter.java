package com.MentorMitrAndroid.GradesHelper.SubjectsHelper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.GradesHelper.GradesAnalysisHelper.GradesAnalysisActivity;
import com.MentorMitrAndroid.GradesHelper.GradesHelper.GradesActivity;
import com.MentorMitrAndroid.R;

import java.util.ArrayList;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsViewHolder> {

    Context context;
    ArrayList<SubjectModel> subjectModelArrayList;
    String from;

    public SubjectsAdapter(Context context, ArrayList<SubjectModel> subjectModelArrayList, String from) {
        this.context = context;
        this.subjectModelArrayList = subjectModelArrayList;
        this.from = from;
    }

    @NonNull
    @Override
    public SubjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_subject, parent, false);
        return new SubjectsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectsViewHolder holder, int position) {
        holder.subjectName.setText(subjectModelArrayList.get(position).getSubjectName());

        holder.subjectCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(from.equals("data")){
                    Intent intent = new Intent(context, GradesActivity.class);
                    intent.putExtra("id",subjectModelArrayList.get(position).getSubjectId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, GradesAnalysisActivity.class);
                    intent.putExtra("id",subjectModelArrayList.get(position).getSubjectId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectModelArrayList.size();
    }
}
