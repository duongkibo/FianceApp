package com.qlct.mymoney.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.qlct.mymoney.R;

import java.util.ArrayList;
import java.util.List;

public class ReportYearFragment extends Fragment {

    private View view;
    private ProgressBar progressBar;
    private BarChart barChartDay;
    private PieChart pieChartOnes, pieChartTwos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_report_year, container, false);
        barChartDay = view.findViewById(R.id.barChart_year);
        float groupSpace = 0.02f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f;
        ArrayList<BarEntry> expenditus = new ArrayList<>();
        expenditus.add(new BarEntry(2018.4f, 20000000));
        expenditus.add(new BarEntry(2019.4f, 30000000));
        expenditus.add(new BarEntry(2020.4f, 40000000));
        expenditus.add(new BarEntry(2021.4f, 50000000));

        ArrayList<BarEntry> inditous = new ArrayList<>();
        inditous.add(new BarEntry(2018.8f, 30000000));
        inditous.add(new BarEntry(2019.8f, 40000000));
        inditous.add(new BarEntry(2020.8f, 60000000));
        inditous.add(new BarEntry(2021.8f, 60000000));

        BarDataSet set1, set2;
        set1 = new BarDataSet(inditous, "Khoản thu");
        set1.setColor(Color.rgb(242, 247, 158));
        set2 = new BarDataSet(expenditus, "Khoản chi");
        set1.setColor(Color.rgb(255, 102, 0));
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTextSize(10f);

        // this replaces setStartAtZero(true)
        barChartDay.setPinchZoom(false);

        barChartDay.setDrawBarShadow(false);
        barChartDay.invalidate();
        barChartDay.setDrawGridBackground(false);
        barChartDay.getAxisRight().setEnabled(false);
        barChartDay.setData(data);
        int startday = 2;
        barChartDay.getXAxis().setAxisMinimum(2018f);
        barChartDay.getXAxis().setAxisMaximum(2022f);
        barChartDay.getBarData().setBarWidth(0.4F);

        barChartDay.animateY(1000);
//        Legend l = barChartDay.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(true);
//
//        l.setYOffset(0f);
//        l.setXOffset(4f);
//        l.setYEntrySpace(0f);
//        l.setTextSize(8f);


        return view;
    }
    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}