package com.MentorMitrAndroid.MentorAnswersHelper.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.QuestionnaireHelper.models.MentorSports;
import com.MentorMitrAndroid.R;

import java.util.List;

public class MentorSportsAdapter extends RecyclerView.Adapter<MentorSportsAdapter.ViewHolder> {
    private List<MentorSports> mentorSportsList;
    Context context;

    public MentorSportsAdapter(Context context, List<MentorSports> mentoSportsList) {
        this.mentorSportsList = mentoSportsList;
        this.context=context;
    }

    @NonNull
    @Override
    public MentorSportsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MentorSportsAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.mentor_sports_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MentorSportsAdapter.ViewHolder holder, int position) {
        final MentorSports mentorSports = mentorSportsList.get(position);

        holder.answer.setText(mentorSports.getAnswer());
        holder.ques.setText(mentorSports.getQues());
    }

    @Override
    public int getItemCount() {
        return mentorSportsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView answer,ques,serial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            answer=itemView.findViewById(R.id.mentor_sportsanswers);
            ques=itemView.findViewById(R.id.mentor_sportsques);
        }
    }


}
