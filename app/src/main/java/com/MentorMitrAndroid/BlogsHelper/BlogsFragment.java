package com.MentorMitrAndroid.BlogsHelper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;


public class BlogsFragment extends Fragment {

    ViewPager viewPager;
    ArrayList<BlogsModel> blogsModelArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blogs, container, false);

        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();

        blogsModelArrayList = new ArrayList<>();

        viewPager = view.findViewById(R.id.blogsPager);
        viewPager.setPageTransformer(true,new DepthPageTransformer());

        db.collection("blogs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for(DocumentSnapshot documentSnapshot: task.getResult()){
                    BlogsModel blogsModel = new BlogsModel();

                    blogsModel.setCreator(documentSnapshot.getString("creator"));
                    blogsModel.setTitle(documentSnapshot.getString("title"));
                    blogsModel.setImage(documentSnapshot.getString("img"));
                    blogsModel.setDate(documentSnapshot.getDate("date"));
                    blogsModel.setId(documentSnapshot.getId());

                    blogsModelArrayList.add(blogsModel);
                }

                BlogsAdapter blogsAdapter = new BlogsAdapter(getContext(), blogsModelArrayList);
                viewPager.setAdapter(blogsAdapter);

            }
        });



        return view;
    }
}