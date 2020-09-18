package com.qlct.mymoney.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qlct.mymoney.R;
import com.qlct.mymoney.adapter.HomeDayAdapter;
import com.qlct.mymoney.model.DatabaseIntalizer;
import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.ExpendituresDB;
import com.qlct.mymoney.viewmodel.AddExpendituresViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class HomeDayFragment extends Fragment {
    private HorizontalCalendar horizontalCalendar;
    private RecyclerView rcExpend;
    private List<Expenditures> expenditures = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home_day, container, false);
        DatabaseIntalizer.populateAsync(ExpendituresDB.getExpendituresDB(getContext()));
        /* start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -2);

        /* end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 2);

        horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
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
            }

        });
        rcExpend = (RecyclerView) rootView.findViewById(R.id.recyclerViewDay);
        HomeDayAdapter homeDayAdapter = new HomeDayAdapter(expenditures);
        rcExpend.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcExpend.setAdapter(homeDayAdapter);
        AddExpendituresViewModel viewModel = ViewModelProviders.of(this).get(AddExpendituresViewModel.class);
        viewModel.getExpendiures().observe(getActivity(),homeDayAdapter::setExpendituresList);



        return rootView;
    }

}