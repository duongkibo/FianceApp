package com.qlct.mymoney.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qlct.mymoney.R;
import com.qlct.mymoney.adapter.ExpenseAdapter;
import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.IncomeDitures;
import com.qlct.mymoney.viewmodel.AddExpendituresViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class HomeYearFragment extends Fragment {
    private HorizontalCalendar horizontalCalendar;
    private RecyclerView rcExpend;
    private RecyclerView rcIncome;
    private List<Expenditures> expenditures = new ArrayList<>();
    private List<IncomeDitures> incomeDitures = new ArrayList<>();
    ExpenseAdapter expenseAdapter = new ExpenseAdapter(expenditures,getActivity());


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home_year, container, false);

        /* start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.YEAR, 0);

        /* end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.YEAR, 12);

        horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
                .mode(HorizontalCalendar.Mode.YEARS)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .configure()
                .formatTopText("MMM")
                .formatMiddleText("yyyy")
                .formatBottomText("EEE")
                .textSize(14f, 24f, 14f)
                .showTopText(false)
                .showBottomText(false)
                .textColor(Color.LTGRAY, Color.WHITE)
                .end()
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Toast.makeText(getContext(), DateFormat.format("EEE, MMM d, yyyy", date) + " is selected!", Toast.LENGTH_SHORT).show();
                int year = Integer.valueOf(DateFormat.format("yyyy",date).toString().trim());
                Log.d("month", year+"");
                rcExpend = (RecyclerView) rootView.findViewById(R.id.recyclerViewYear);
                rcExpend.setLayoutManager(new LinearLayoutManager(getActivity()));
                rcExpend.setAdapter(expenseAdapter);
                AddExpendituresViewModel viewModel = ViewModelProviders.of(HomeYearFragment.this).get(AddExpendituresViewModel.class);
                viewModel.getExpanddituresYears(year).observe(getActivity(),expenseAdapter::setExpendituresList);
            }

        });

        return rootView;
    }
}