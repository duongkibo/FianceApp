package com.qlct.mymoney.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.qlct.mymoney.R;
import com.qlct.mymoney.adapter.ExpenseAdapter;
import com.qlct.mymoney.adapter.IncomeAdapter;
import com.qlct.mymoney.model.DataBaseIntalizerIncome;
import com.qlct.mymoney.model.DatabaseIntalizer;
import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.ExpendituresDB;
import com.qlct.mymoney.model.IncomeDitures;
import com.qlct.mymoney.model.IncomeDituresDB;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.viewmodel.AddExpendituresViewModel;
import com.qlct.mymoney.viewmodel.AddIncomeDituresViewModel;
import com.qlct.mymoney.viewmodel.AddUserDituresViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class HomeDayFragment extends Fragment {
    private TextView txtCostExpense;
    private TextView txtCostIncome;
    private TextView totalCost;
    private TextView txt_cost_expense;
    private TextView txt_cost_income;

    private HorizontalCalendar horizontalCalendar;
    private RecyclerView rcExpend;
    private RecyclerView rcIncome;
    private List<Expenditures> expenditures = new ArrayList<>();
    private List<IncomeDitures> incomeDitures = new ArrayList<>();

    UserDitures userDituresS = new UserDitures();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home_day, container, false);

        DatabaseIntalizer.populateAsync(ExpendituresDB.getExpendituresDB(getContext()));
        DataBaseIntalizerIncome.populateAsync(IncomeDituresDB.getIncomeDituresBD(getContext()));

        /* start before 1 month from now */

        /*viewModel2.getIncome().observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDituresList) {
                if(incomeDituresList!=null)
                {
                    for (int i=0;i<incomeDituresList.size();i++)
                    {
                        IncomeDitures incomeDitures = incomeDituresList.get(i);
                        incomeDitures.getDay();
                    }
                }
            }
        });*/
        AddUserDituresViewModel viewModel = ViewModelProviders.of(HomeDayFragment.this).get(AddUserDituresViewModel.class);
        viewModel.getUserDitures().observe(getActivity(), new Observer<UserDitures>() {
            @Override
            public void onChanged(UserDitures userDitures) {
                if(userDitures!=null)
                {
                    userDituresS.setWallet(userDitures.getWallet());
                    userDituresS.setUsername(userDitures.getUsername());
                    userDituresS.setId(userDitures.getId());
                    userDituresS.setPassword(userDitures.getPassword());

                }
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -2);

        /* end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 2);

        horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .configure()
                .formatTopText("MMM")
                .formatMiddleText("dd")
                .formatBottomText("EEE")
                .textSize(14f, 24f, 14f)
                .showTopText(true)
                .showBottomText(true)
                .textColor(Color.LTGRAY, Color.WHITE)
                .end()
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Toast.makeText(getContext(), DateFormat.format("EEE, MMM d, yyyy", date) + " is selected!", Toast.LENGTH_SHORT).show();
                int day = Integer.valueOf(DateFormat.format("d", date).toString().trim());
                int month = Integer.valueOf(DateFormat.format("M",date).toString().trim());
                Log.d("month",month+"");
                ExpenseAdapter expenseAdapter = new ExpenseAdapter(expenditures,getActivity(),userDituresS);
                rcExpend = (RecyclerView) view.findViewById(R.id.recyclerViewDay);
                rcExpend.setLayoutManager(new LinearLayoutManager(getActivity()));
                rcExpend.setAdapter(expenseAdapter);

                AddExpendituresViewModel viewModel = ViewModelProviders.of(HomeDayFragment.this).get(AddExpendituresViewModel.class);
                viewModel.getExpanddituresDay(day,month).observe(getActivity(), expenseAdapter::setExpendituresList);
                Log.d("id xxxSxxx",userDituresS.getId()+"");
                rcIncome = view.findViewById(R.id.recyclerViewDay_1);

                IncomeAdapter incomeAdapter = new IncomeAdapter(incomeDitures,userDituresS);
                rcIncome.setLayoutManager(new LinearLayoutManager(getActivity()));
                rcIncome.setAdapter(incomeAdapter);


                AddIncomeDituresViewModel viewModel2 = ViewModelProviders.of(HomeDayFragment.this).get(AddIncomeDituresViewModel.class);
                viewModel2.getIncomeDituresDay(day,month).observe(getActivity(), incomeAdapter::setIncomeDituresList);
               /* viewModel.getExpanddituresDay(day).observe(getActivity(), new Observer<List<Expenditures>>() {
                    @Override
                    public void onChanged(List<Expenditures> expendituresList) {
                        if (expendituresList != null) {
                            for (int i = 0; i < expendituresList.size(); i++) {
                                Expenditures expenditures = expendituresList.get(i);
                                txtCostExpense.setText(expenditures.getMoney());
                            }
                        }
                    }

                });*/

            }


        });


//        rcIncome = view.findViewById(R.id.recyclerViewDay_1);
//
//
//        IncomeAdapter incomeAdapter = new IncomeAdapter(incomeDitures,userDituresS);
//
//        rcIncome.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rcIncome.setAdapter(incomeAdapter);
//
//
//        AddIncomeDituresViewModel viewModel2 = ViewModelProviders.of(this).get(AddIncomeDituresViewModel.class);
//        viewModel2.getIncome().observe(getActivity(), incomeAdapter::setIncomeDituresList);
    }
}