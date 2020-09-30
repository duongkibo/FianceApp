package com.qlct.mymoney.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.qlct.mymoney.R;

import java.util.ArrayList;
import java.util.List;

public class ReportMonthFragment extends Fragment {
    private BarChart barChart;
    private PieChart pieChartOne, pieChartTwo;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_report_month, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        creatChart();
    }

    private void initView() {
    }

    private void creatChart() {
       /* final AnyChartView anyChartView = view.findViewById(R.id.any_chart_view_expense);
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

        cartesian.title("");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("đ{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Tháng");
        // cartesian.yAxis(0).title("Revenue");

        anyChartView2.setChart(cartesian);*/
        barChart = view.findViewById(R.id.barChart);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 200));
        barEntries.add(new BarEntry(2, 400));
        barEntries.add(new BarEntry(3, 455));
        barEntries.add(new BarEntry(4, 245));
        barEntries.add(new BarEntry(5, 454));
        barEntries.add(new BarEntry(6, 289));
        barEntries.add(new BarEntry(7, 913));
        barEntries.add(new BarEntry(8, 723));
        barEntries.add(new BarEntry(9, 821));
        barEntries.add(new BarEntry(10, 618));
        barEntries.add(new BarEntry(11, 589));
        barEntries.add(new BarEntry(12, 697));

        BarDataSet barDataSet = new BarDataSet(barEntries, "BarChart");
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barDataSet.setColor(Color.GREEN);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("ABCCCC");
        barChart.animateY(2000);

        //-----------------------------------------------------------------------

        pieChartOne = view.findViewById(R.id.pieChartOne);
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(500000, "Mua sắm"));
        pieEntries.add(new PieEntry(300000, "Nhà Hàng"));
        pieEntries.add(new PieEntry(200000, "Giải trí"));
        pieEntries.add(new PieEntry(521000, "Giáo dục"));
        pieEntries.add(new PieEntry(249000, "Phương tiện"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Khoản Thu");
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieChartOne.setData(pieData);
        pieChartOne.animate();

        //--------------------------------------------------------------------------

        pieChartTwo = view.findViewById(R.id.pieChartTwo);
        ArrayList<PieEntry> pieEntriess = new ArrayList<>();
        pieEntriess.add(new PieEntry(2000000, "Lương"));
        pieEntriess.add(new PieEntry(1000000, "Tiền thưởng"));
        pieEntriess.add(new PieEntry(2500000, "Bán hàng"));
        pieEntriess.add(new PieEntry(800000, "Khoản khác"));

        PieDataSet pieDataSett = new PieDataSet(pieEntriess, "Khoản Chi");
        pieDataSett.setValueTextColor(Color.BLACK);
        pieDataSett.setValueTextSize(10f);
        pieDataSett.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieDataa = new PieData(pieDataSett);
        pieChartTwo.setData(pieDataa);
        pieChartTwo.animate();

    }

}