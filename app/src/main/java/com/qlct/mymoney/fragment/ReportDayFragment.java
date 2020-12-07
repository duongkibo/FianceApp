package com.qlct.mymoney.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
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
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.qlct.mymoney.R;
import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.IncomeDitures;
import com.qlct.mymoney.model.Positions;
import com.qlct.mymoney.viewmodel.AddExpendituresViewModel;
import com.qlct.mymoney.viewmodel.AddIncomeDituresViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static android.os.Build.VERSION_CODES.M;

public class ReportDayFragment extends Fragment {

    private View view;
    private ProgressBar progressBar;
    private BarChart barChartDay;
    private PieChart pieChartOnes, pieChartTwos;
    ArrayList<BarEntry> expendituss = new ArrayList<>();
    ArrayList<BarEntry> inditouss = new ArrayList<>();
    List<Positions> positions = new ArrayList<>();
    List<Positions> positionss = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_report_day, container, false);
        barChartDay = view.findViewById(R.id.barChart_day);
        inditouss.add(new BarEntry(2.8f, 0));
        inditouss.add(new BarEntry(3.8f, 000000));
        inditouss.add(new BarEntry(4.8f, 000000));
        inditouss.add(new BarEntry(5.8f, 000000));
        inditouss.add(new BarEntry(6.8f,0 ));
        inditouss.add(new BarEntry(7.8f, 000000));
        inditouss.add(new BarEntry(8.6f, 000000));
        expendituss.add(new BarEntry(2.4f, 000000));
        expendituss.add(new BarEntry(3.4f, 000000));
        expendituss.add(new BarEntry(4.4f, 000000));
        expendituss.add(new BarEntry(5.4f, 000000));
        expendituss.add(new BarEntry(6.4f, 000000));
        expendituss.add(new BarEntry(7.4f, 000000));
        expendituss.add(new BarEntry(8.2f, 000000));
        int size = expendituss.size();
        int sizeb = inditouss.size();
        AddExpendituresViewModel viewModel = ViewModelProviders.of(ReportDayFragment.this).get(AddExpendituresViewModel.class);
        viewModel.getExpendiures().observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                Log.d("size of expen", expenditures.size() + "");

                if (expenditures.size() > 0) {
                    for (int i = 0; i < expenditures.size(); i++) {
                        for (int j =0;j<size;j++)
                        {
                            if(j==i)
                            {
                                expendituss.get(j).setY(Float.parseFloat(expenditures.get(j).getMoney()));
                            }


                        }
                    }
                }
                Log.d("ssize of postion",positions.size()+"");
            }
        });
        AddIncomeDituresViewModel viewModel1 = ViewModelProviders.of(ReportDayFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel1.getIncome().observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {

                if (incomeDitures.size() > 0) {
                    for (int i = 0; i < incomeDitures.size(); i++) {
                        for (int j =0;j<sizeb;j++)
                        {
                            if(j==i)
                            {
                                inditouss.get(j).setY(Float.parseFloat(incomeDitures.get(j).getMoney().trim()));
                            }


                        }
                    }

                }
            }
        });

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
        BarDataSet set1, set2;
        set1 = new BarDataSet(inditouss, "Khoản thu");
        set1.setColor(Color.rgb(242, 247, 158));
        set2 = new BarDataSet(expendituss, "Khoản chi");
        set1.setColor(Color.rgb(255, 102, 0));
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTextSize(10f);
        Legend l = barChartDay.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setYOffset(100f);
        l.setXOffset(10f);
        l.setYEntrySpace(100f);
        l.setTextSize(8f);
        YAxis leftAxis = barChartDay.getAxisLeft();

        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        barChartDay.getAxisRight().setEnabled(false);





       // this replaces setStartAtZero(true)


        barChartDay.setDrawBarShadow(false);
        barChartDay.invalidate();
      YAxis yAxis = barChartDay.getAxisLeft();

        barChartDay.setData(data);

        barChartDay.setPinchZoom(true);

        barChartDay.setDrawBarShadow(false);
        barChartDay.setDrawGridBackground(false);
        barChartDay.getXAxis().setAxisMaximum(8.5f);
        barChartDay.getBarData().setBarWidth(0.4f);
        pieChartOnes = view.findViewById(R.id.pieChart_one);
        barChartDay.animateY(1000);
        ArrayList<PieEntry> pieEntriess = new ArrayList<>();
        pieEntriess.add(new PieEntry(500000, "Mua sắm"));
        pieEntriess.add(new PieEntry(300000, "Nhà Hàng"));
        pieEntriess.add(new PieEntry(200000, "Giải trí"));
        pieEntriess.add(new PieEntry(521000, "Giáo dục"));
        pieEntriess.add(new PieEntry(249000, "Phương tiện"));

        PieDataSet pieDataSet = new PieDataSet(pieEntriess, "Khoản Thu");
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(8f);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieDatas = new PieData(pieDataSet);
        pieChartOnes.setData(pieDatas);
        pieChartOnes.animate();
        pieChartOnes.setEntryLabelTextSize(8f);
        pieChartOnes.setCenterText("Khoản thu");
        pieChartOnes.getDescription().setEnabled(false);
        pieChartOnes.getDescription().setEnabled(false);
        pieChartOnes.setHoleColor(Color.WHITE);

        pieChartOnes.setTransparentCircleColor(Color.WHITE);
        pieChartOnes.setTransparentCircleAlpha(110);

        pieChartOnes.setHoleRadius(58f);
        pieChartOnes.setTransparentCircleRadius(61f);

        pieChartOnes.setDrawCenterText(true);

        pieChartOnes.setRotationAngle(0);

        //--------------------------------------------------------------------------

        pieChartTwos = view.findViewById(R.id.pieChart);
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(2000000, "Lương"));
        pieEntries.add(new PieEntry(1000000, "Tiền thưởng"));
        pieEntries.add(new PieEntry(2500000, "Bán hàng"));
        pieEntries.add(new PieEntry(800000, "Khoản khác"));

        PieDataSet pieDataSett = new PieDataSet(pieEntries, "Khoản Chi");
        pieDataSett.setValueTextColor(Color.WHITE);
        pieDataSett.setValueTextSize(8f);
        pieDataSett.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieDataa = new PieData(pieDataSett);
        pieChartTwos.setData(pieDataa);
        pieChartTwos.animate();
        pieChartTwos.setEntryLabelTextSize(8f);
        pieChartTwos.setCenterText("Khoản chi");
        pieChartTwos.getDescription().setEnabled(false);
        pieChartTwos.setTransparentCircleColor(Color.WHITE);
        pieChartTwos.setTransparentCircleAlpha(110);

        pieChartTwos.setHoleRadius(58f);
        pieChartTwos.setTransparentCircleRadius(61f);

        pieChartTwos.setDrawCenterText(true);

        pieChartTwos.setRotationAngle(0);

    }


}