package com.MentorMitrAndroid.BlogsHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.R;

import java.util.ArrayList;

public class BlogDescAdapter extends RecyclerView.Adapter<BlogDescriptionViewHolder> {

    Context context;
    ArrayList<String> paras;

    public BlogDescAdapter(Context context, ArrayList<String> paras) {
        this.context = context;
        this.paras = paras;
    }

    @NonNull
    @Override
    public BlogDescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_blog_desc, parent, false);
        return new BlogDescriptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogDescriptionViewHolder holder, int position) {
        holder.descItem.setText(paras.get(position));
    }

    @Override
    public int getItemCount() {
        return paras.size();
    }
}
