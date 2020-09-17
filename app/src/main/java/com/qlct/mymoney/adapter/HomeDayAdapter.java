package com.qlct.mymoney.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qlct.mymoney.R;

public class HomeDayAdapter extends RecyclerView.Adapter<HomeDayAdapter.HomeDayViewHolder> {
    @NonNull
    @Override
    public HomeDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeDayViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public  class HomeDayViewHolder extends RecyclerView.ViewHolder{
       ImageView imageView;
       TextView textView;
       HomeDayViewHolder(View itemView)
       {
           super(itemView);
           imageView = itemView.findViewById(R.id.image_group_day);
           textView = itemView.findViewById(R.id.tv_name_group_day);
       }
    }
}
