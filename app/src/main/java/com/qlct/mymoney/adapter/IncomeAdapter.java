package com.qlct.mymoney.adapter;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qlct.mymoney.R;
import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.ExpendituresDB;
import com.qlct.mymoney.model.IncomeDitures;
import com.qlct.mymoney.model.IncomeDituresDB;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.model.UserDituresDB;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.HomeDayViewHolder> {
    private List<IncomeDitures> incomeDituresList;
    private UserDitures userDitures;

    public IncomeAdapter(List<IncomeDitures> incomeDituresList, UserDitures userDitures) {
        this.incomeDituresList = incomeDituresList;
        this.userDitures = userDitures;
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
        IncomeDitures incomeDitures = incomeDituresList.get(position);

        holder.imageView.setImageResource(incomeDitures.getImage());
        holder.textView.setText(incomeDitures.getNameGroup());
        holder.cost.setTextColor(Color.BLUE);
        holder.cost.setText(incomeDitures.getMoney() + " đ");
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                class DeleteIc extends AsyncTask<Void, Void, Void> {
                    IncomeDitures incomeDitures;

                    public DeleteIc(IncomeDitures incomeDitures) {
                        this.incomeDitures = incomeDitures;
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        IncomeDituresDB.getIncomeDituresBD(v.getContext().getApplicationContext()).getIncomeDituresDao().delete(incomeDitures);
                        return null;
                    }
                }
                class UpdateUsersss extends AsyncTask<Void, Void, Void> {
                    UserDitures userDitures;

                    UpdateUsersss(UserDitures userDitures) {
                        this.userDitures = userDitures;
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        UserDituresDB.getUserDituresDB(v.getContext().getApplicationContext()).getUserDituresDao().update(userDitures);
                        return null;
                    }
                }
                String a = incomeDitures.getMoney();
                int sum = userDitures.getWallet() - Integer.parseInt(a);
                userDitures.setWallet(sum);
                Log.d("id plus",userDitures.getId()+"");
                incomeDituresList.remove(incomeDitures);
                new UpdateUsersss(userDitures).execute();
                new DeleteIc(incomeDitures).execute();
                notifyDataSetChanged();
            }
        });

    }

    public void setIncomeDituresList(List<IncomeDitures> incomeDituresList) {
        this.incomeDituresList = incomeDituresList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return incomeDituresList.size();
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
