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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
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
import com.qlct.mymoney.viewmodel.AddExpendituresViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

public class ExpenseFragment extends Fragment {
    private View view;
    private TextView edtCalendar;
    private  List<Group> groupList = new ArrayList<>();
    private GroupAdapter groupAdapter;
    private  RecyclerView recyclerView;
    private EditText edt_one,edt_two;
    private  String nameGroupss;
    private  int idImage;
    private  TextInputEditText edtMoney, edtNote;
    private Button btnAddExp;
    private AddExpendituresViewModel addExpendituresViewModel;
    private  int days,years,months;
    private Expenditures expenditures ;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_expense, container, false);
        edtCalendar =(TextView) view.findViewById(R.id.edtCalendar);
        init();

        edtMoney = (TextInputEditText)  view.findViewById(R.id.tp_money);
        edtNote = (TextInputEditText) view.findViewById(R.id.tp_note);
        recyclerView = (RecyclerView) view.findViewById(R.id.rc_group);
        btnAddExp = (Button) view.findViewById(R.id.btn_add_expense);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 5, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        groupAdapter  = new GroupAdapter(getActivity(),groupList);
        recyclerView.setAdapter(groupAdapter);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                nameGroupss = intent.getStringExtra("nameGroup");
                idImage = intent.getIntExtra("sss",10);
                //Toast.makeText(getContext(),idImage, Toast.LENGTH_SHORT).show();
                expenditures = new Expenditures(edtMoney.getText().toString(),edtNote.getText().toString(),idImage,nameGroupss,months,days,years);

            }
        };
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver,
                new IntentFilter("sendata"));
        // add to db
        btnAddExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("xxxxx",years+" "+months+" "+days);
                if(expenditures!= null)
                {
                    new AddExpendituresTask(expenditures).execute();

                }

            }
        });
        addExpendituresViewModel = ViewModelProviders.of(ExpenseFragment.this).get(AddExpendituresViewModel.class);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

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

    private void init(){
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
                        days = day;
                        months = month;
                        years = year;
                        Log.d("datexxxx",years+" "+months+" "+days);


                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });



        groupList.add(new Group("Shopping",R.drawable.shopping));
        groupList.add(new Group("Food",R.drawable.food));
        groupList.add(new Group("Entertaiment",R.drawable.entertainmence));
        groupList.add(new Group("Education",R.drawable.education));
        groupList.add(new Group("Residence",R.drawable.residence));
        groupList.add(new Group("Salary",R.drawable.salary));
        groupList.add(new Group("Transpostaion",R.drawable.transportation));
        groupList.add(new Group("Travel",R.drawable.travel));
        groupList.add(new Group("Bills",R.drawable.bills));
        groupList.add(new Group("Purchase",R.drawable.purchases));


    }
}