package com.MentorMitrAndroid.WeeklyTimetableHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.WeeklyTimetableHelper.Fragments.FridayFragment;
import com.MentorMitrAndroid.WeeklyTimetableHelper.Fragments.MondayFragment;
import com.MentorMitrAndroid.WeeklyTimetableHelper.Fragments.SaturdayFragment;
import com.MentorMitrAndroid.WeeklyTimetableHelper.Fragments.SundayFragment;
import com.MentorMitrAndroid.WeeklyTimetableHelper.Fragments.ThursdayFragment;
import com.MentorMitrAndroid.WeeklyTimetableHelper.Fragments.TuesdayFragment;
import com.MentorMitrAndroid.WeeklyTimetableHelper.Fragments.WednesdayFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WeeklyTimetableActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    MondayFragment mondayFragment = new MondayFragment();
    TuesdayFragment tuesdayFragment = new TuesdayFragment();
    WednesdayFragment wednesdayFragment = new WednesdayFragment();
    ThursdayFragment thursdayFragment = new ThursdayFragment();
    FridayFragment fridayFragment = new FridayFragment();
    SaturdayFragment saturdayFragment = new SaturdayFragment();
    SundayFragment sundayFragment = new SundayFragment();

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_timetable);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.view_pager);

        tabLayout.setupWithViewPager(viewPager);

        if(getIntent()!=null){
            id = getIntent().getStringExtra("id");
        }

        Bundle bundle = new Bundle();
        bundle.putString("id",id);

        mondayFragment.setArguments(bundle);
        tuesdayFragment.setArguments(bundle);
        wednesdayFragment.setArguments(bundle);
        thursdayFragment.setArguments(bundle);
        fridayFragment.setArguments(bundle);
        saturdayFragment.setArguments(bundle);
        sundayFragment.setArguments(bundle);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(mondayFragment,"Monday");
        viewPagerAdapter.addFragment(tuesdayFragment,"Tuesday");
        viewPagerAdapter.addFragment(wednesdayFragment,"Wednesday");
        viewPagerAdapter.addFragment(thursdayFragment,"Thursday");
        viewPagerAdapter.addFragment(fridayFragment, "Friday");
        viewPagerAdapter.addFragment(saturdayFragment, "Saturday");
        viewPagerAdapter.addFragment(sundayFragment, "Sunday");

        viewPager.setAdapter(viewPagerAdapter);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title){
            fragmentList.add(fragment);
            fragmentTitle.add(title);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
}