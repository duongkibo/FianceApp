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
import android.text.format.DateFormat;
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

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    int sumday1 = 0;
    int sumday2 = 0;
    int sumday3 = 0;
    int sumday4 = 0;
    int sumday5 = 0;
    int sumday6 = 0;
    int sumday7 = 0;
    int sumday8 = 0;
    int sumday9 = 0;
    int sumday10 = 0;
    int sumday11 = 0;
    int sumday12 = 0;
    int sumday13 = 0;
    int sumday14 = 0;



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



             Date todayDate = Calendar.getInstance().getTime();
         SimpleDateFormat format = new SimpleDateFormat("d");

        String monthNumber  = (String) DateFormat.format("MM", todayDate);
        String todayString = format.format(todayDate);
         Calendar calendar = new GregorianCalendar();

         int day = Integer.parseInt(todayString);
         int monthx= Integer.parseInt(monthNumber);
        Log.d("dayss",day+"");


        AddExpendituresViewModel viewModel = ViewModelProviders.of(ReportDayFragment.this).get(AddExpendituresViewModel.class);
        viewModel.getExpanddituresDay(day,monthx).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null){
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sumday1 += Integer.parseInt(expenditures.get(i).getMoney().trim());
                    }
                    expendituss.get(6).setY(sumday1);
                }
                }
        });
        AddExpendituresViewModel viewModel1 = ViewModelProviders.of(ReportDayFragment.this).get(AddExpendituresViewModel.class);
        viewModel1.getExpanddituresDay(day-1,monthx).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null){
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sumday2 += Integer.parseInt(expenditures.get(i).getMoney().trim());
                    }
                    expendituss.get(5).setY(sumday2);
                }
            }
        });
        AddExpendituresViewModel viewModel2 = ViewModelProviders.of(ReportDayFragment.this).get(AddExpendituresViewModel.class);
        viewModel2.getExpanddituresDay(day-2,monthx).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null){
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sumday3 += Integer.parseInt(expenditures.get(i).getMoney().trim());
                    }
                    expendituss.get(4).setY(sumday3);
                }
            }
        });
        AddExpendituresViewModel viewModel3 = ViewModelProviders.of(ReportDayFragment.this).get(AddExpendituresViewModel.class);
        viewModel3.getExpanddituresDay(day-3,monthx).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null){
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sumday4 += Integer.parseInt(expenditures.get(i).getMoney().trim());
                    }
                    expendituss.get(3).setY(sumday4);
                }
            }
        });
        AddExpendituresViewModel viewModel4 = ViewModelProviders.of(ReportDayFragment.this).get(AddExpendituresViewModel.class);
        viewModel4.getExpanddituresDay(day-4,monthx).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null){
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sumday5 += Integer.parseInt(expenditures.get(i).getMoney().trim());
                    }
                    expendituss.get(2).setY(sumday5);
                }
            }
        });
        AddExpendituresViewModel viewModel5 = ViewModelProviders.of(ReportDayFragment.this).get(AddExpendituresViewModel.class);
        viewModel5.getExpanddituresDay(day-5,monthx).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null){
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sumday6 += Integer.parseInt(expenditures.get(i).getMoney().trim());
                    }
                    expendituss.get(1).setY(sumday6);
                }
            }
        });
        AddExpendituresViewModel viewModel6 = ViewModelProviders.of(ReportDayFragment.this).get(AddExpendituresViewModel.class);
        viewModel6.getExpanddituresDay(day-6,monthx).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null){
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sumday7 += Integer.parseInt(expenditures.get(i).getMoney().trim());
                    }
                    expendituss.get(0).setY(sumday7);
                }
            }
        });
        AddIncomeDituresViewModel viewModels = ViewModelProviders.of(ReportDayFragment.this).get(AddIncomeDituresViewModel.class);
        viewModels.getIncomeDituresDay(day, monthx).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {

              if(incomeDitures!=null)
              {
                  for(int i=0;i<incomeDitures.size();i++)
                  {
                      sumday8+=Integer.parseInt(incomeDitures.get(i).getMoney().trim());
                  }
                  inditouss.get(6).setY(sumday8);
              }
            }
        });
        AddIncomeDituresViewModel viewModels1 = ViewModelProviders.of(ReportDayFragment.this).get(AddIncomeDituresViewModel.class);
        viewModels1.getIncomeDituresDay(day-1, monthx).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {

                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sumday9+=Integer.parseInt(incomeDitures.get(i).getMoney().trim());
                    }
                    inditouss.get(5).setY(sumday9);
                }
            }
        });
        AddIncomeDituresViewModel viewModels2 = ViewModelProviders.of(ReportDayFragment.this).get(AddIncomeDituresViewModel.class);
        viewModels2.getIncomeDituresDay(day-2, monthx).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {

                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sumday10+=Integer.parseInt(incomeDitures.get(i).getMoney().trim());
                    }
                    inditouss.get(4).setY(sumday10);
                }
            }
        });
        AddIncomeDituresViewModel viewModels3 = ViewModelProviders.of(ReportDayFragment.this).get(AddIncomeDituresViewModel.class);
        viewModels3.getIncomeDituresDay(day-3, monthx).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {

                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sumday11+=Integer.parseInt(incomeDitures.get(i).getMoney().trim());
                    }
                    inditouss.get(3).setY(sumday11);
                }
            }
        });
        AddIncomeDituresViewModel viewModels4 = ViewModelProviders.of(ReportDayFragment.this).get(AddIncomeDituresViewModel.class);
        viewModels4.getIncomeDituresDay(day-4, monthx).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {

                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sumday12+=Integer.parseInt(incomeDitures.get(i).getMoney().trim());
                    }
                    inditouss.get(2).setY(sumday12);
                }
            }
        });
        AddIncomeDituresViewModel viewModels5 = ViewModelProviders.of(ReportDayFragment.this).get(AddIncomeDituresViewModel.class);
        viewModels5.getIncomeDituresDay(day-5, monthx).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {

                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sumday13+=Integer.parseInt(incomeDitures.get(i).getMoney().trim());
                    }
                    inditouss.get(1).setY(sumday13);
                }
            }
        });
        AddIncomeDituresViewModel viewModels6 = ViewModelProviders.of(ReportDayFragment.this).get(AddIncomeDituresViewModel.class);
        viewModels6.getIncomeDituresDay(day-6, monthx).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {

                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sumday14+=Integer.parseInt(incomeDitures.get(i).getMoney().trim());
                    }
                    inditouss.get(0).setY(sumday14);
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
        YAxis leftAxis = barChartDay.getAxisLeft();

        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(2000000000f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)


        barChartDay.getAxisRight().setEnabled(false);





       // this replaces setStartAtZero(true)


        barChartDay.setDrawBarShadow(false);
        barChartDay.invalidate();
     XAxis  xAxis = barChartDay.getXAxis();
        xAxis.setGranularity(1f);

        barChartDay.setData(data);

        barChartDay.setPinchZoom(true);

        barChartDay.setDrawBarShadow(false);
        barChartDay.setDrawGridBackground(false);
        barChartDay.getXAxis().setAxisMaximum(8.5f);
        barChartDay.getBarData().setBarWidth(0.4f);

        barChartDay.animateY(1000);





    }


}