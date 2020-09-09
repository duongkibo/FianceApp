package com.qlct.mymoney.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.qlct.mymoney.R;
import com.qlct.mymoney.adapter.MonthAdapter;
import com.qlct.mymoney.adapter.YearAdapter;
import com.qlct.mymoney.model.Month;
import com.qlct.mymoney.model.Year;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class HomeMonthFragment extends Fragment {
    private HorizontalCalendar horizontalCalendar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home_month, container, false);

        /* start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -8);

        /* end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 8);

        horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
                .mode(HorizontalCalendar.Mode.MONTHS)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .configure()
                .formatTopText("MMM")
                .formatMiddleText("MM")
                .formatBottomText("yyyy")
                .textSize(14f, 24f, 14f)
                .showTopText(false)
                .showBottomText(false)
                .textColor(Color.LTGRAY, Color.WHITE)
                .end()
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Toast.makeText(getContext(), DateFormat.format(" MMM d", date) + " is selected!", Toast.LENGTH_SHORT).show();
            }

        });

        return rootView;
    }
}