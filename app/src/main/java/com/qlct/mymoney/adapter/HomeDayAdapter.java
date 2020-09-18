package com.qlct.mymoney.adapter;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qlct.mymoney.R;
import com.qlct.mymoney.model.Expenditures;

import java.util.List;

public class HomeDayAdapter extends RecyclerView.Adapter<HomeDayAdapter.HomeDayViewHolder> {
    private List<Expenditures> expendituresList;

    public HomeDayAdapter(List<Expenditures> expendituresList)
    {
        this.expendituresList =expendituresList;
    }
    @NonNull
    @Override
    public HomeDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_day,parent,false);
        HomeDayViewHolder viewHolder = new HomeDayViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeDayViewHolder holder, int position) {
        Expenditures expenditures = expendituresList.get(position);

        holder.imageView.setBackgroundResource(expenditures.getImage());
        holder.textView.setText(expenditures.getNameGroup());

    }
    public void setExpendituresList(List<Expenditures> expendituresList)
    {
        this.expendituresList = expendituresList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return expendituresList.size();
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
