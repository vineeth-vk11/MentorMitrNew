package com.MentorMitrAndroid.CollegeTrackHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.QuestionnaireHelper.models.StoreItems;
import com.MentorMitrAndroid.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private static final int TYPE_ROW = 0;
    private static final int TYPE_ROW_COLORFUL = 1;

    private List<StoreItems> items;
    private int itemLayout;
    private final Context mContext;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    private View.OnLongClickListener onLongClickListener;

    public ItemAdapter(List<StoreItems> items, int itemLayout, Context context) {
        this.items = items;
        this.itemLayout = itemLayout;
        this.mContext = context;
        onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StoreItems item = (StoreItems) buttonView.getTag();
                if(isChecked){

                         deleteItem(item);
                    Toast.makeText(context, "Row Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        };
        onLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CollegeTrack activity = (CollegeTrack) mContext;
                v.setOnCreateContextMenuListener(activity);
                activity.openMenu(v);
                return false;
            }
        };
    }
    @Override
    public int getItemViewType(int position)
    {
        if (position % 2 == 0)
        {
            return TYPE_ROW_COLORFUL;
        }

        return TYPE_ROW;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ROW) {
            View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            return new ViewHolder(v);
        }
        else
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.college_track_colorful,
                    parent, false);
            return new ViewHolder(v);

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StoreItems item = items.get(position);
        holder.tvSerialNumber.setText(item.getSerialNumber());
        holder.tvCollege.setText(item.getCollege());
        holder.tvCourse.setText(item.getCourse());
        holder.tvSelection.setText(item.getSelection());
        holder.tvAchieve.setText(item.getAchieve());
        holder.tvImprove.setText(item.getImprove());

        holder.checkBox.setTag(item);
        holder.checkBox.setOnCheckedChangeListener(onCheckedChangeListener);

    }

    @Override
    public int getItemCount() {
        if(items == null) {
            return 0;
        }
        return items.size();
    }

    public void addItem(StoreItems item){
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void updateItem(StoreItems item){
        int pos = items.indexOf(item);
        notifyItemChanged(pos);
    }

    public void deleteItem(StoreItems item){
        int pos = items.indexOf(item);
        items.remove(pos);
        notifyItemRemoved(pos);
    }

    public List<StoreItems> getItems(){
        if(items == null){
            return new ArrayList<>();
        }
        return items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CheckBox checkBox;
        public TextView tvSerialNumber;
        public TextView tvCollege;
        public TextView tvCourse;
        public TextView tvSelection;
        public TextView tvAchieve;
        public TextView tvImprove;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            tvSerialNumber = (TextView) itemView.findViewById(R.id.tvSerialNumber);
            tvCollege = (TextView) itemView.findViewById(R.id.tvCollege);
            tvCourse = (TextView) itemView.findViewById(R.id.tvCourse);
            tvSelection = (TextView) itemView.findViewById(R.id.tvSelection);
            tvAchieve = (TextView) itemView.findViewById(R.id.tvAchieve);
            tvImprove = (TextView) itemView.findViewById(R.id.tvImprove);

        }
    }
}
