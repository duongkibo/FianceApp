package com.qlct.mymoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qlct.mymoney.R;
import com.qlct.mymoney.model.Year;

import java.util.List;

public class YearAdapter extends RecyclerView.Adapter<YearAdapter.myViewHoder> {
    private List<Year> years;
    private Context context;

    public YearAdapter(List<Year> years, Context context) {
        this.years = years;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_year, parent, false);
        myViewHoder viewHolder = new myViewHoder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHoder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return years.size();
    }

    class myViewHoder extends RecyclerView.ViewHolder {


        public myViewHoder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
