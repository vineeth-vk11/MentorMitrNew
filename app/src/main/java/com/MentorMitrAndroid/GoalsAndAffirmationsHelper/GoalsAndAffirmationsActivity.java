package com.MentorMitrAndroid.GoalsAndAffirmationsHelper;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.MentorMitrAndroid.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class GoalsAndAffirmationsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    GoalsFragment goalsFragment = new GoalsFragment();
    AffirmationsFragment affirmationsFragment = new AffirmationsFragment();

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_and_affirmations);

        tabLayout =findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.view_pager);

        if(getIntent() != null){
            id = getIntent().getStringExtra("id");
        }
        else {
            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        goalsFragment.setArguments(bundle);
        affirmationsFragment.setArguments(bundle);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(goalsFragment,"Goals");
        viewPagerAdapter.addFragment(affirmationsFragment,"Affirmations");
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