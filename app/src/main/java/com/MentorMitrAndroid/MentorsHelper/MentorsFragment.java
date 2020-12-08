package com.MentorMitrAndroid.MentorsHelper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.MentorMitrAndroid.R;

import java.util.ArrayList;


public class MentorsFragment extends Fragment {

    ArrayList<MentorsModel> mentorsModelArrayList = new ArrayList<>();

    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mentors, container, false);

        mentorsModelArrayList.clear();

        MentorsModel mentorsModel = new MentorsModel();
        mentorsModel.setName("Dr. Jaideep Kumar Sharma");
        mentorsModel.setDesc("Dr Jaideep Kumar Sharma is a TEDx speaker , A motivator and is engaged with various causes where he talks to students , people at large . By background he is a renowned Radiologist from All India Institute of Medical Sciences (AIIMS), Delhi , a Gold Medalist  Delhi. Though Dr. Jaideep pursues his medical practise , his aim is to contribute to making people happy and community a happier place where people live without fear . He himself has fought a battle for his life and a champion of overcoming adversity and give it a fight .");
        mentorsModel.setImage(R.drawable.jaideep);

        MentorsModel mentorsModel1 = new MentorsModel();
        mentorsModel1.setName("Partha Roy");
        mentorsModel1.setDesc("Mr Partha Roy is a reflective leader who can diagnose a complex problem and can break it into pieces and devise comprehensive solutions solving any challenge . By background he is a telecom engineer, with corporate exposure of 29+ years at highest levels in largest organizations. Partha engages with business leaders and Youth across schools and universities with same zeal to add value very successfully. Partha is also the founder of The Prism Consulting, an organization with its core intervention and focuses on advisory consulting  transformation.");
        mentorsModel1.setImage(R.drawable.parth);

        MentorsModel mentorsModel2 = new MentorsModel();
        mentorsModel2.setName("Aarushi Bhardwaj");
        mentorsModel2.setDesc("Ms Aaarushi Bharwaj is a passionate young corporate Leader . She is somebody who believes in power of realizing your true potential by exposing yourself to ideas you cannot comprehend. She also believes its never late to re-orient your career . With a degree in Architecture fromLondon and Masters in Luxury Management from SP Jain institute , she is a digital marketing specialist in one of the BIG 4 of the world . She is a passionate traveller , digital marketer, architect, and a student mentor. Aarushi truly symbolises a blend of knowledge , goal-accomplishment and happiness and has done it at a very young age .");
        mentorsModel2.setImage(R.drawable.aarushi);

        MentorsModel mentorsModel3 = new MentorsModel();
        mentorsModel3.setName("Pooja Awasty");
        mentorsModel3.setDesc("Ms. Pooja Awasty is a passionate Public Speaker and mentor . She has addressed hundreds of thousands of people in last 20 years and impacted them positively in areas of Goal Setting , Leadership development and identifying Gratitude as a principle of success which she passionately drives . Pooja has been an educationist by background , an entrepreneur and a Wellness Expert by practise.\" />\n");
        mentorsModel3.setImage(R.drawable.pooja);

        mentorsModelArrayList.add(mentorsModel);
        mentorsModelArrayList.add(mentorsModel1);
        mentorsModelArrayList.add(mentorsModel2);
        mentorsModelArrayList.add(mentorsModel3);
        
        viewPager = view.findViewById(R.id.mentorsPager);
        viewPager.setPageTransformer(true,new DepthPageTransformer());

        MentorsAdapter mentorsAdapter = new MentorsAdapter(getContext(), mentorsModelArrayList);
        viewPager.setAdapter(mentorsAdapter);

        return view;
    }
}