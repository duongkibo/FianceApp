package com.qlct.mymoney.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.qlct.mymoney.R;
import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.ExpendituresDB;
import com.qlct.mymoney.viewmodel.AddExpendituresViewModel;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.HomeDayViewHolder> {
    private List<Expenditures> expendituresList;
    private  AddExpendituresViewModel viewModel;
    private  Context context;

    public ExpenseAdapter(List<Expenditures> expendituresList, Context context) {
        this.expendituresList = expendituresList;
        this.context = context;

    }


    @NonNull
    @Override
    public HomeDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_day, parent, false);
        HomeDayViewHolder viewHolder = new HomeDayViewHolder(view);


        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull HomeDayViewHolder holder, int position) {
        Expenditures expenditures = expendituresList.get(position);

        holder.imageView.setImageResource(expenditures.getImage());
        holder.textView.setText(expenditures.getNameGroup());
        holder.cost.setTextColor(Color.RED);
        holder.cost.setText(expenditures.getMoney() + " đ");
       holder.btnDelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               expendituresList.remove(expenditures);
               class  DeleteEx extends AsyncTask<Void, Void, Void>
               {
                   Expenditures expenditures;
                   public  DeleteEx(Expenditures expenditures)
                   {
                       this.expenditures = expenditures;
                   }

                   @Override
                   protected Void doInBackground(Void... voids) {
                       ExpendituresDB.getExpendituresDB(v.getContext().getApplicationContext()).getExpendituresDao().delete(expenditures);
                       return null;
                   }
               }
               new DeleteEx(expenditures).execute();

               notifyDataSetChanged();

           }
       });


    }

    public void setExpendituresList(List<Expenditures> expendituresList) {
        this.expendituresList = expendituresList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return expendituresList.size();
    }

    public class HomeDayViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView cost;
        ImageButton btnDelete;

        HomeDayViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_group_day);
            textView = itemView.findViewById(R.id.tv_name_group_day);
            cost = itemView.findViewById(R.id.tv_cost);
            btnDelete = itemView.findViewById(R.id.img_btn_delete);

        }
    }
}
