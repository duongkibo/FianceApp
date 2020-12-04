package com.qlct.mymoney.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qlct.mymoney.R;
import com.qlct.mymoney.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {
    private List<Group> groupList;
    private LayoutInflater mInflater;
    private static int checkedPosition = 0;

    public GroupAdapter(Context context, List<Group> groupList) {
        this.mInflater = LayoutInflater.from(context);
        this.groupList = groupList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_group, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupViewHolder holder, int position) {
        final Group group = groupList.get(position);
        holder.bind(group);
        holder.textView.setText(group.getName());
        holder.imageView.setImageResource(group.getImage());
//        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent("sendata");
//
//                int id = group.getImage();
//
//                intent.putExtra("sss", id);
//                intent.putExtra("nameGroup", holder.textView.getText().toString().trim());
//
//
//                LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(intent);
//                Toast.makeText(view.getContext(), "Bạn đã chọn" + holder.textView.getText().toString() + "", Toast.LENGTH_LONG).show();
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        ConstraintLayout constraintLayout;
        LinearLayout linearLayout;


        GroupViewHolder(View itemview) {
            super(itemview);
            linearLayout = itemview.findViewById(R.id.linear_bg);
            imageView = itemView.findViewById(R.id.image_group);
            textView = itemview.findViewById(R.id.tv_name_group);
            constraintLayout = itemview.findViewById(R.id.itemclick);

        }
        void bind(final Group group)
        {
            if (checkedPosition == -1) {
                linearLayout.setBackgroundColor(Color.WHITE);
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    linearLayout.setBackgroundColor(Color.parseColor("#80DEEA"));
                } else {
                    linearLayout.setBackgroundColor(Color.WHITE);
                }
            }
            textView.setText(group.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    linearLayout.setBackgroundColor(Color.parseColor("#80DEEA"));
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                        Intent intent = new Intent("sendata");
//
                int id = group.getImage();

                intent.putExtra("sss", id);
                intent.putExtra("nameGroup", textView.getText().toString().trim());


                LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(intent);
                Toast.makeText(view.getContext(), "Bạn đã chọn" + textView.getText().toString() + "", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
    public Group getSelected() {
        if (checkedPosition != -1) {
            return groupList.get(checkedPosition);
        }
        return null;
    }



}
