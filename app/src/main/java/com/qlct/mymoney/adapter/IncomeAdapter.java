package com.qlct.mymoney.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qlct.mymoney.R;
import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.IncomeDitures;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.HomeDayViewHolder> {
    private List<IncomeDitures> incomeDituresList;

    public IncomeAdapter(List<IncomeDitures> incomeDituresList)
    {
        this.incomeDituresList = incomeDituresList;
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
        IncomeDitures incomeDitures = incomeDituresList.get(position);

        holder.imageView.setImageResource(incomeDitures.getImage());
        holder.textView.setText(incomeDitures.getNameGroup());
        holder.cost.setTextColor(Color.BLUE);
        holder.cost.setText(incomeDitures.getMoney() + " đ");

    }
    public void setIncomeDituresList(List<IncomeDitures> incomeDituresList)
    {
        this.incomeDituresList = incomeDituresList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return incomeDituresList.size();
    }

    public  class HomeDayViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView cost;
        HomeDayViewHolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_group_day);
            textView = itemView.findViewById(R.id.tv_name_group_day);
            cost = itemView.findViewById(R.id.tv_cost);

        }
    }
}
