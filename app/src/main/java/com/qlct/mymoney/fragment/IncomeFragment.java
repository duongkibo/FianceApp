package com.qlct.mymoney.fragment;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import com.google.android.material.textfield.TextInputEditText;
import com.qlct.mymoney.R;
import com.qlct.mymoney.adapter.GroupAdapter;
import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.ExpendituresDB;
import com.qlct.mymoney.model.Group;
import com.qlct.mymoney.model.IncomeDitures;
import com.qlct.mymoney.model.IncomeDituresDB;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.model.UserDituresDB;
import com.qlct.mymoney.viewmodel.AddExpendituresViewModel;
import com.qlct.mymoney.viewmodel.AddIncomeDituresViewModel;
import com.qlct.mymoney.viewmodel.AddUserDituresViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IncomeFragment extends Fragment {
    private TextView edtCalendarIncome;
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
    private AddIncomeDituresViewModel addIncomeDituresViewModel;
    private int days, years, months;
    private IncomeDitures incomeDitures;
    private UserDitures userDituresXx = new UserDitures();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_income, container, false);
        edtCalendarIncome = view.findViewById(R.id.edtCalendar);
        init();
        edtMoney = (TextInputEditText) view.findViewById(R.id.tp_money);
        edtNote = (TextInputEditText) view.findViewById(R.id.tp_note);
        recyclerView = (RecyclerView) view.findViewById(R.id.rc_group);
        btnAddExp = (Button) view.findViewById(R.id.btn_add_expense);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        groupAdapter = new GroupAdapter(getActivity(), groupList);
        recyclerView.setAdapter(groupAdapter);
        AddUserDituresViewModel viewModel = ViewModelProviders.of(IncomeFragment.this).get(AddUserDituresViewModel.class);
        viewModel.getUserDitures().observe(getActivity(), new Observer<UserDitures>() {
            @Override
            public void onChanged(UserDitures userDitures) {
                if(userDitures!=null)
                {
                    userDituresXx.setWallet(userDitures.getWallet());
                    userDituresXx.setUsername(userDitures.getUsername());
                    userDituresXx.setId(userDitures.getId());
                    userDituresXx.setPassword(userDitures.getPassword());

                }


            }
        });
        getDataExpen();
        // add to db


        addIncomeDituresViewModel = ViewModelProviders.of(IncomeFragment.this).get(AddIncomeDituresViewModel.class);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        btnAddExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("xxxxx", years + " " + months + " " + days);
                if (incomeDitures != null) {
                    new AddIncomeTask(incomeDitures,view.getContext()).execute();
                    int a = userDituresXx.getWallet() + Integer.valueOf(incomeDitures.getMoney().trim());
                    userDituresXx.setWallet(a);
                    new updateUserAsyncTaskss(userDituresXx,view.getContext()).execute();
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getContext(),"vui long nhap day du thong tin",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private class updateUserAsyncTaskss extends AsyncTask<Void, Void, Void> {
        UserDitures userDitures;
        Context context;

        public updateUserAsyncTaskss(UserDitures userDitures,Context context) {
            this.userDitures = userDitures;
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDituresDB.getUserDituresDB(context.getApplicationContext()).getUserDituresDao().update(userDitures);
            return null;
        }
    }

    public void getDataExpen() {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                nameGroupss = intent.getStringExtra("nameGroup");
                idImage = intent.getIntExtra("sss", 10);
                //Toast.makeText(getContext(),idImage, Toast.LENGTH_SHORT).show();
                incomeDitures = new IncomeDitures(edtMoney.getText().toString(), edtNote.getText().toString(), idImage, nameGroupss, months, days, years);
                incomeDitures.setImage(idImage);

            }
        };
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver,
                new IntentFilter("sendata"));

    }

    private class AddIncomeTask extends AsyncTask<Void, Void, Void> {
        IncomeDitures incomeDitures;
        Context context;

        public AddIncomeTask(IncomeDitures incomeDitures,Context context) {
            this.incomeDitures = incomeDitures;
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            IncomeDituresDB.getIncomeDituresBD(context.getApplicationContext()).getIncomeDituresDao().insert(incomeDitures);
            return null;
        }
    }


    private void init() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        edtCalendarIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        edtCalendarIncome.setText(date);
                        years = year;
                        months = month;
                        incomeDitures.setDay(day);
                        incomeDitures.setMonth(months);
                        incomeDitures.setYear(years);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        Log.d("uess", years + months + days + "");

        groupList.add(new Group("Lương", R.drawable.salary));
        groupList.add(new Group("Tiền thưởng", R.drawable.award));
        groupList.add(new Group("Bán hàng", R.drawable.purchases));
        groupList.add(new Group("Khoản khác", R.drawable.dollar));

    }

}