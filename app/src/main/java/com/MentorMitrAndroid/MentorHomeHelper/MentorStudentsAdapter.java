package com.MentorMitrAndroid.MentorHomeHelper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.MentorDashboard.CollegeDetailsForMentorActivity;
import com.MentorMitrAndroid.MentorDashboard.StudentDetailsForMentorAcitivity;
import com.MentorMitrAndroid.MentorDashboard.WorkingDetailsForMentorActivity;
import com.MentorMitrAndroid.R;

import java.util.ArrayList;

public class MentorStudentsAdapter extends RecyclerView.Adapter<MentorStudentsViewHolder> {

    Context context;
    ArrayList<MentorStudentsModel> mentorStudentsModelArrayList;

    public MentorStudentsAdapter(Context context, ArrayList<MentorStudentsModel> mentorStudentsModelArrayList) {
        this.context = context;
        this.mentorStudentsModelArrayList = mentorStudentsModelArrayList;
    }

    @NonNull
    @Override
    public MentorStudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_students_mentor, parent, false);
        return new MentorStudentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentorStudentsViewHolder holder, int position) {
        String type = mentorStudentsModelArrayList.get(position).getType();

        holder.name.setText(mentorStudentsModelArrayList.get(position).getName());
        holder.type.setText(mentorStudentsModelArrayList.get(position).getType());

        holder.student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(type.equals("School")){
                    Intent intent = new Intent(context, StudentDetailsForMentorAcitivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id",mentorStudentsModelArrayList.get(position).getId());
                    context.startActivity(intent);
                }
                else if(type.equals("College")){
                    Intent intent = new Intent(context, CollegeDetailsForMentorActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id",mentorStudentsModelArrayList.get(position).getId());
                    context.startActivity(intent);
                }
                else if(type.equals("Working")){
                    Intent intent = new Intent(context, WorkingDetailsForMentorActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id",mentorStudentsModelArrayList.get(position).getId());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mentorStudentsModelArrayList.size();
    }
}
