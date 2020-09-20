package com.qlct.mymoney.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Orientation;
import com.anychart.enums.Position;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.scales.Linear;
import com.qlct.mymoney.R;

import java.util.ArrayList;
import java.util.List;

public class ReportDayFragment extends Fragment {

    private View view;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_report_day, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        creatChart();
    }

    private void initView() {
        progressBar = view.findViewById(R.id.progress_bar);
    }

    private void creatChart() {
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

        //set report colum
        AnyChartView anyChartView2 = view.findViewById(R.id.any_chart_view_colum);
        anyChartView2.setProgressBar(progressBar);
        APIlib.getInstance().setActiveAnyChartView(anyChartView2);

        /*Cartesian cartesian = AnyChart.column();

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

        cartesian.title("");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("đ{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Tháng");
        // cartesian.yAxis(0).title("Revenue");

        anyChartView2.setChart(cartesian);*/

        Cartesian cartesian = AnyChart.cartesian();

        cartesian.animation(true);

        cartesian.title("Combination of Stacked Column and Line Chart (Dual Y-Axis)");

        cartesian.yScale().stackMode(ScaleStackMode.VALUE);

        Linear scalesLinear = Linear.instantiate();
        scalesLinear.minimum(0d);
        scalesLinear.maximum(100d);
        scalesLinear.ticks("{ interval: 20 }");

        com.anychart.core.axes.Linear extraYAxis = cartesian.yAxis(1d);
        extraYAxis.orientation(Orientation.RIGHT)
                .scale(scalesLinear);
        extraYAxis.labels()
                .padding(0d, 0d, 0d, 5d)
                .format("{%Value}%");

        List<DataEntry> data2 = new ArrayList<>();
        data2.add(new CustomDataEntry("P1", 96.5, 2040, 0, 0));
        data2.add(new CustomDataEntry("P2", 77.1, 1794, 0, 0));
        data2.add(new CustomDataEntry("P3", 73.2, 2026, 0, 0));
        data2.add(new CustomDataEntry("P4", 61.1, 2341, 921, 1621));
        data2.add(new CustomDataEntry("P5", 70.0, 1800, 1500, 1700));
        data2.add(new CustomDataEntry("P6", 60.7, 1507, 1007, 1907));
        data2.add(new CustomDataEntry("P7", 62.1, 2701, 921, 1821));
        data2.add(new CustomDataEntry("P8", 75.1, 1671, 971, 1671));
        data2.add(new CustomDataEntry("P9", 80.0, 1980, 1080, 1880));
        data2.add(new CustomDataEntry("P10", 54.1, 1041, 1041, 1641));
        data2.add(new CustomDataEntry("P11", 51.3, 813, 1113, 1913));
        data2.add(new CustomDataEntry("P12", 59.1, 691, 1091, 1691));

        Set set = Set.instantiate();
        set.data(data2);
      //  Mapping lineData = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping column1Data = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping column2Data = set.mapAs("{ x: 'x', value: 'value3' }");
        Mapping column3Data = set.mapAs("{ x: 'x', value: 'value4' }");

        cartesian.column(column1Data);
        cartesian.crosshair(true);

       // Line line = cartesian.line(lineData);
       // line.yScale(scalesLinear);

        cartesian.column(column2Data);

        cartesian.column(column3Data);

        anyChartView2.setChart(cartesian);


    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2, Number value3, Number value4) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
            setValue("value4", value4);
        }
    }

}