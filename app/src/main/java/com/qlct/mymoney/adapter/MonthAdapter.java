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
import com.qlct.mymoney.model.Month;

import java.util.List;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.myViewHoder> {

    private List<Month> months;
    private Context context;

    public MonthAdapter(List<Month> months, Context context) {
        this.months = months;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_month, parent, false);
        myViewHoder viewHoder = new myViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHoder holder, int position) {
        final Month month = months.get(position);
        holder.txtYear.setText(month.getMonth());

        holder.txtYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, month.getMonth(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return months.size();
    }

    class myViewHoder extends RecyclerView.ViewHolder {

        private TextView txtYear;

        public myViewHoder(@NonNull View itemView) {
            super(itemView);
            txtYear = itemView.findViewById(R.id.txtMonth);
        }
    }
}
