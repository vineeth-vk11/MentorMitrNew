package com.MentorMitrAndroid.MentorsHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.MentorMitrAndroid.R;

import java.util.ArrayList;

public class MentorsAdapter extends PagerAdapter {

    Context context;
    ArrayList<MentorsModel> mentorsModelArrayList;
    LayoutInflater layoutInflater;

    public MentorsAdapter(Context context, ArrayList<MentorsModel> mentorsModelArrayList) {
        this.context = context;
        this.mentorsModelArrayList = mentorsModelArrayList;
        if(context!=null){
            layoutInflater = LayoutInflater.from(context);
        }
    }

    @Override
    public int getCount() {
        return mentorsModelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ((ViewPager)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = layoutInflater.inflate(R.layout.list_item_mentor, container, false);

        TextView name = view.findViewById(R.id.mentorName);
        TextView desc = view.findViewById(R.id.mentorDesc);
        ImageView image = view.findViewById(R.id.mentorImage);

        name.setText(mentorsModelArrayList.get(position).getName());
        desc.setText(mentorsModelArrayList.get(position).getDesc());
        image.setImageResource(mentorsModelArrayList.get(position).getImage());

        container.addView(view);
        return view;
    }
}
