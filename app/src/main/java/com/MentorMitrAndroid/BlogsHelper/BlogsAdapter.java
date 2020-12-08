package com.MentorMitrAndroid.BlogsHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BlogsAdapter extends PagerAdapter {

    Context context;
    ArrayList<BlogsModel> blogsModelArrayList;
    LayoutInflater layoutInflater;

    public BlogsAdapter(Context context, ArrayList<BlogsModel> blogsModelArrayList) {
        this.context = context;
        this.blogsModelArrayList = blogsModelArrayList;
        if(context!=null){
            layoutInflater = LayoutInflater.from(context);
        }
    }

    @Override
    public int getCount() {
        return blogsModelArrayList.size();
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

        View view = layoutInflater.inflate(R.layout.list_item_blog, container, false);

        TextView author = view.findViewById(R.id.author);
        TextView date = view.findViewById(R.id.date);
        TextView name = view.findViewById(R.id.blogName);
        ImageView imageView = view.findViewById(R.id.blogImage);
        RecyclerView desc = view.findViewById(R.id.blogDesc);

        desc.setLayoutManager(new LinearLayoutManager(context));
        desc.setHasFixedSize(true);

        ArrayList<String> paras = new ArrayList<>();

        FirebaseFirestore.getInstance().collection("blogs").document(blogsModelArrayList.get(position).getId()).collection("paras").orderBy("number").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot documentSnapshot : task.getResult()){
                    String para = documentSnapshot.getString("desc");

                    paras.add(para);
                }
                BlogDescAdapter blogDescAdapter = new BlogDescAdapter(context, paras);
                desc.setAdapter(blogDescAdapter);
            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);


        Picasso.get().load(blogsModelArrayList.get(position).getImage()).into(imageView);
        author.setText(blogsModelArrayList.get(position).getCreator());
        try {
            date.setText(String.valueOf(simpleDateFormat.parse(String.valueOf(blogsModelArrayList.get(position).getDate()))));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        name.setText(blogsModelArrayList.get(position).getTitle());

        container.addView(view);
        return view;

    }
}
