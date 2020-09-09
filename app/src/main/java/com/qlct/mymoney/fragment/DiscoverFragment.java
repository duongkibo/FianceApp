package com.qlct.mymoney.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;
import com.qlct.mymoney.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {
    private View view;
    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_discover, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        creatChart();
    }

    private void creatChart(){
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Salary", 2000000));
        pie.setData(data);
        AnyChartView anyChartViewIncome = view.findViewById(R.id.any_chart_view_income);
        anyChartViewIncome.setChart(pie);

        Pie pies = AnyChart.pie();
        List<DataEntry> datas = new ArrayList<>();
        datas.add(new ValueDataEntry("Food", 1000000));
        datas.add(new ValueDataEntry("Shopping", 500000));
        datas.add(new ValueDataEntry("Bills", 200000));
        datas.add(new ValueDataEntry("Travel", 300000));
        pies.setData(datas);
        AnyChartView anyChartViewExpense = view.findViewById(R.id.any_chart_view_expense);
        anyChartViewExpense.setChart(pies);

    }
}
