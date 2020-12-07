package com.qlct.mymoney.fragment;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.charts.Resource;
import com.google.android.material.textfield.TextInputEditText;
import com.qlct.mymoney.MyApplication;
import com.qlct.mymoney.R;
import com.qlct.mymoney.adapter.GroupAdapter;
import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.ExpendituresDB;
import com.qlct.mymoney.model.Group;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.model.UserDituresDB;
import com.qlct.mymoney.viewmodel.AddExpendituresViewModel;
import com.qlct.mymoney.viewmodel.AddUserDituresViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;

public class ExpenseFragment extends Fragment {
    private View view;
    private TextView edtCalendar;
    private List<Group> groupList = new ArrayList<>();
    private GroupAdapter groupAdapter;
    private RecyclerView recyclerView;
    private EditText edt_one, edt_two;
    private String nameGroupss;
    private int idImage;
    private TextInputEditText edtMoney, edtNote;
    private Button btnAddExp;
    private AddExpendituresViewModel addExpendituresViewModel;
    private int days, years, months;
    private Expenditures expenditures;
    private  TextView tv_sumss,tv_namess,tv_pinolds;
    private  UserDitures userDituresX = new UserDitures();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_expense, container, false);
        edtCalendar = (TextView) view.findViewById(R.id.edtCalendar);
        init();
        edtMoney = (TextInputEditText) view.findViewById(R.id.tp_money);
        edtNote = (TextInputEditText) view.findViewById(R.id.tp_note);
        recyclerView = (RecyclerView) view.findViewById(R.id.rc_group);
        btnAddExp = (Button) view.findViewById(R.id.btn_add_expense);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        groupAdapter = new GroupAdapter(getActivity(), groupList);
        recyclerView.setAdapter(groupAdapter);
        getDataExpen();
        AddUserDituresViewModel viewModel = ViewModelProviders.of(ExpenseFragment.this).get(AddUserDituresViewModel.class);
        viewModel.getUserDitures().observe(getActivity(), new Observer<UserDitures>() {
            @Override
            public void onChanged(UserDitures userDitures) {
                if(userDitures!=null)
                {
                    userDituresX.setWallet(userDitures.getWallet());
                    userDituresX.setUsername(userDitures.getUsername());
                    userDituresX.setId(userDitures.getId());
                    userDituresX.setPassword(userDitures.getPassword());

                }


            }
        });

        // add to db


        addExpendituresViewModel = ViewModelProviders.of(ExpenseFragment.this).get(AddExpendituresViewModel.class);


        return view;
    }

    public void getDataExpen() {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                nameGroupss = intent.getStringExtra("nameGroup");
                idImage = intent.getIntExtra("sss", 10);
                //Toast.makeText(getContext(),idImage, Toast.LENGTH_SHORT).show();
                Log.d("iddd", idImage + "");
                expenditures = new Expenditures(edtMoney.getText().toString(), edtNote.getText().toString(), idImage, nameGroupss, months, days, years);
                expenditures.setImage(idImage);

            }
        };
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver,
                new IntentFilter("sendata"));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tv_sumss =(TextView) view.findViewById(R.id.tv_sumwallet);
        tv_namess =(TextView) view.findViewById(R.id.tv_namess);
        tv_pinolds =(TextView) view.findViewById(R.id.tv_pinolds);
        btnAddExp.setOnClickListener(new View.OnClickListener() {
            UserDitures userDituress = new UserDitures();
            @Override
            public void onClick(View view) {
                Log.d("xxxxx", years + " " + months + " " + days);
                if (expenditures != null) {
                    new AddExpendituresTask(expenditures).execute();
                    int a = userDituresX.getWallet() -Integer.parseInt(expenditures.getMoney());
                    userDituresX.setWallet(a);
                   new updateUserAsyncTasks(userDituresX,view.getContext()).execute();



                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getContext(),"vui long nhap day du thong tin",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    private class updateUserAsyncTasks extends AsyncTask<Void, Void, Void> {
        UserDitures userDitures;
        Context context;

        public updateUserAsyncTasks(UserDitures userDitures,Context context) {
            this.userDitures = userDitures;
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDituresDB.getUserDituresDB(context.getApplicationContext()).getUserDituresDao().update(userDitures);
            return null;
        }
    }

    private class AddExpendituresTask extends AsyncTask<Void, Void, Void> {
        Expenditures expenditures;

        public AddExpendituresTask(Expenditures expenditures) {
            this.expenditures = expenditures;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ExpendituresDB.getExpendituresDB(getActivity().getApplication()).getExpendituresDao().insert(expenditures);
            return null;
        }
    }

    private void init() {

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edtCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        edtCalendar.setText(date);
                        years = year;
                        days =day;
                        months = month;
                        expenditures.setDay(day);
                        expenditures.setMonth(months);
                        expenditures.setYear(years);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        Log.d("uess", years + months + days + "");

        groupList.add(new Group("Mua sắm", R.drawable.shopping));
        groupList.add(new Group("Nhà hàng", R.drawable.food));
        groupList.add(new Group("Giải trí", R.drawable.entertainmence));
        groupList.add(new Group("Giáo dục", R.drawable.education));
        groupList.add(new Group("Gia đình", R.drawable.residence));
        groupList.add(new Group("Phương tiện", R.drawable.transportation));
        groupList.add(new Group("Du lịch", R.drawable.travel));
        groupList.add(new Group("Hóa đơn", R.drawable.bills));


    }
}