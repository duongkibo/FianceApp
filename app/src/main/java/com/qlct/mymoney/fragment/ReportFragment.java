package com.qlct.mymoney.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.qlct.mymoney.R;

import java.util.ArrayList;
import java.util.List;

public class ReportFragment extends Fragment {

    private View view;
    private ChipNavigationBar topNav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_report, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setTopNavigation();
        //creatChart();
    }

    private void initView(){
        topNav = view.findViewById(R.id.top_nav_report);
    }

    private void setTopNavigation(){
        topNav.setItemSelected(R.id.reportDay, true);
        Fragment reportDay = new ReportDayFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container3, reportDay).commit();

        topNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){
                    case R.id.reportDay:
                        fragment = new ReportDayFragment();
                        break;
                    case R.id.reportMonth:
                        fragment = new ReportMonthFragment();
                        break;
                    case R.id.reportYear:
                        fragment = new ReportYearFragment();
                        break;
                }
                if (fragment != null){
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container3, fragment).commit();
                }
            }
        });
    }

/*    private void creatChart(){
        final AnyChartView anyChartView = view.findViewById(R.id.any_chart_view_expense);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);

        final Pie pie = AnyChart.pie();

        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
                Toast.makeText(getContext(), event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Apples", 6371664));
        data.add(new ValueDataEntry("Pears", 789622));
        data.add(new ValueDataEntry("Bananas", 7216301));
        data.add(new ValueDataEntry("Grapes", 1486621));
        data.add(new ValueDataEntry("Oranges", 1200000));

        pie.data(data);

        pie.title("KHoản thu");

        anyChartView.setChart(pie);

        final AnyChartView anyChartView1 = view.findViewById(R.id.any_chart_view_income);
        APIlib.getInstance().setActiveAnyChartView(anyChartView1);

        final Pie pie1 = AnyChart.pie();

        pie1.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
                Toast.makeText(getContext(), event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });

        List<DataEntry> data1 = new ArrayList<>();
        data1.add(new ValueDataEntry("Apples", 6371664));
        data1.add(new ValueDataEntry("Pears", 789622));
        data1.add(new ValueDataEntry("Bananas", 7216301));
        data1.add(new ValueDataEntry("Grapes", 1486621));
        data1.add(new ValueDataEntry("Oranges", 1200000));

        pie1.data(data1);

        pie1.title("Khoản chi");

        anyChartView1.setChart(pie1);

        AnyChartView anyChartView2 = view.findViewById(R.id.any_chart_view_colum);
        APIlib.getInstance().setActiveAnyChartView(anyChartView2);

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data2 = new ArrayList<>();
        data2.add(new ValueDataEntry("1", 351540));
        data2.add(new ValueDataEntry("2", 941910));
        data2.add(new ValueDataEntry("3", 102610));
        data2.add(new ValueDataEntry("4", 110430));
        data2.add(new ValueDataEntry("5", 1280100));
        data2.add(new ValueDataEntry("6", 143760));
        data2.add(new ValueDataEntry("7", 1706170));
        data2.add(new ValueDataEntry("8", 213210));
        data2.add(new ValueDataEntry("9", 2492980));
        data2.add(new ValueDataEntry("10", 2292980));
        data2.add(new ValueDataEntry("11", 259980));
        data2.add(new ValueDataEntry("12", 24080));

        Column column = cartesian.column(data2);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("đ{%Value}{groupsSeparator: }");

        cartesian.title("Top 10 Cosmetic Products by Revenue");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("đ{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Tháng");
       // cartesian.yAxis(0).title("Revenue");

        anyChartView2.setChart(cartesian);


    }*/
}
