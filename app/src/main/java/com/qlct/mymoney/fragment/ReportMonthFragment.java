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
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.qlct.mymoney.R;
import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.IncomeDitures;
import com.qlct.mymoney.viewmodel.AddExpendituresViewModel;
import com.qlct.mymoney.viewmodel.AddIncomeDituresViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReportMonthFragment extends Fragment {

    private View view;
    private ProgressBar progressBar;
    private BarChart barChartDay;
    private PieChart pieChartOnes, pieChartTwos;
    ArrayList<BarEntry> expenditus = new ArrayList<>();
    ArrayList<BarEntry> inditous = new ArrayList<>();
    int sum =0;
    int sum1 =0;
    int sum2 =0;
    int sum3 =0;
    int sum4 =0;
    int sum5 =0;
    int sum6 =0;
    int sum7=0;
    int sum8 =0;
    int sum9 =0;
    int sum10 =0;
    int sum11 =0;
    int sum0 =0;
    int sum101 =0;
    int sum20 =0;
    int sum30 =0;
    int sum40 =0;
    int sum50 =0;
    int sum60 =0;
    int sum70=0;
    int sum80 =0;
    int sum90 =0;
    int sum100 =0;
    int sum110 =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_report_month, container, false);
        barChartDay = view.findViewById(R.id.barChart_month);
        float groupSpace = 0.02f;
        float barSpace = 0.03f; // x4 DataSet


        expenditus.add(new BarEntry(1, 000000));
        expenditus.add(new BarEntry(2, 000000));
        expenditus.add(new BarEntry(3, 000000));
        expenditus.add(new BarEntry(4, 000000));
        expenditus.add(new BarEntry(5, 000000));
        expenditus.add(new BarEntry(6, 000000));
        expenditus.add(new BarEntry(7, 00000));
        expenditus.add(new BarEntry(8, 000000));
        expenditus.add(new BarEntry(9, 00000));
        expenditus.add(new BarEntry(10, 000000));
        expenditus.add(new BarEntry(11, 000000));
        expenditus.add(new BarEntry(12, 0));
        inditous.add(new BarEntry(1.5f, 000000));
        inditous.add(new BarEntry(2.5f, 000000));
        inditous.add(new BarEntry(3.5f, 000000));
        inditous.add(new BarEntry(4.5f, 000000));
        inditous.add(new BarEntry(5.5f, 000000));
        inditous.add(new BarEntry(6.5f, 0));
        inditous.add(new BarEntry(7.5f, 000000));
        inditous.add(new BarEntry(8.5f, 000000));
        inditous.add(new BarEntry(9.5f, 0));
        inditous.add(new BarEntry(10.5f, 000000));
        inditous.add(new BarEntry(11.5f, 0));
        inditous.add(new BarEntry(12.5f, 0));

        AddExpendituresViewModel viewModel = ViewModelProviders.of(ReportMonthFragment.this).get(AddExpendituresViewModel.class);
         viewModel.getExpanddituresMonths(1).observe(getActivity(), new Observer<List<Expenditures>>() {
             @Override
             public void onChanged(List<Expenditures> expenditures) {
                 if(expenditures!=null)
                 {
                     for(int i=0;i<expenditures.size();i++)
                     {
                         sum+=Long.valueOf(expenditures.get(i).getMoney());
                     }
                     expenditus.get(0).setY(sum);
                 }
                 expenditus.get(0).setY(0);
             }
         });
        AddExpendituresViewModel viewModel1 = ViewModelProviders.of(ReportMonthFragment.this).get(AddExpendituresViewModel.class);
        viewModel1.getExpanddituresMonths(2).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null)
                {
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sum1+=Long.valueOf(expenditures.get(i).getMoney());
                    }
                    expenditus.get(1).setY(sum1);
                }

            }
        });
        AddExpendituresViewModel viewModel2 = ViewModelProviders.of(ReportMonthFragment.this).get(AddExpendituresViewModel.class);
        viewModel2.getExpanddituresMonths(3).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null)
                {
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sum2+=Long.valueOf(expenditures.get(i).getMoney());
                    }
                    expenditus.get(2).setY(sum2);
                }

            }
        });
        AddExpendituresViewModel viewModel3 = ViewModelProviders.of(ReportMonthFragment.this).get(AddExpendituresViewModel.class);
        viewModel3.getExpanddituresMonths(4).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null)
                {
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sum3+=Long.valueOf(expenditures.get(i).getMoney());
                    }
                    expenditus.get(3).setY(sum3);
                }

            }
        });
        AddExpendituresViewModel viewModel4 = ViewModelProviders.of(ReportMonthFragment.this).get(AddExpendituresViewModel.class);
        viewModel4.getExpanddituresMonths(5).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null)
                {
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sum4+=Long.valueOf(expenditures.get(i).getMoney());
                    }
                    expenditus.get(4).setY(sum4);
                }

            }
        });
        AddExpendituresViewModel viewModel5 = ViewModelProviders.of(ReportMonthFragment.this).get(AddExpendituresViewModel.class);
        viewModel5.getExpanddituresMonths(6).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null)
                {
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sum5+=Long.valueOf(expenditures.get(i).getMoney());
                    }
                    expenditus.get(5).setY(sum5);
                }

            }
        });
        AddExpendituresViewModel viewModel6 = ViewModelProviders.of(ReportMonthFragment.this).get(AddExpendituresViewModel.class);
        viewModel6.getExpanddituresMonths(7).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null)
                {
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sum6+=Long.valueOf(expenditures.get(i).getMoney());
                    }
                    expenditus.get(6).setY(sum6);
                }

            }
        });
        AddExpendituresViewModel viewModel7 = ViewModelProviders.of(ReportMonthFragment.this).get(AddExpendituresViewModel.class);
        viewModel7.getExpanddituresMonths(8).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null)
                {
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sum7+=Long.valueOf(expenditures.get(i).getMoney());
                    }
                    expenditus.get(7).setY(sum7);
                }

            }
        });
        AddExpendituresViewModel viewModel8 = ViewModelProviders.of(ReportMonthFragment.this).get(AddExpendituresViewModel.class);
        viewModel8.getExpanddituresMonths(9).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null)
                {
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sum8+=Long.valueOf(expenditures.get(i).getMoney());
                    }
                    expenditus.get(8).setY(sum8);
                }

            }
        });
        AddExpendituresViewModel viewModel9 = ViewModelProviders.of(ReportMonthFragment.this).get(AddExpendituresViewModel.class);
        viewModel9.getExpanddituresMonths(10).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null)
                {
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sum9+=Long.valueOf(expenditures.get(i).getMoney());
                    }
                    expenditus.get(9).setY(sum9);
                }

            }
        });
        AddExpendituresViewModel viewModel10 = ViewModelProviders.of(ReportMonthFragment.this).get(AddExpendituresViewModel.class);
        viewModel10.getExpanddituresMonths(11).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null)
                {
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sum10+=Long.valueOf(expenditures.get(i).getMoney());
                    }
                    expenditus.get(10).setY(sum10);
                }

            }
        });
        AddExpendituresViewModel viewModel11 = ViewModelProviders.of(ReportMonthFragment.this).get(AddExpendituresViewModel.class);
        viewModel11.getExpanddituresMonths(12).observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if(expenditures!=null)
                {
                    for(int i=0;i<expenditures.size();i++)
                    {
                        sum11+=Long.valueOf(expenditures.get(i).getMoney());
                    }
                    expenditus.get(11).setY(sum11);
                }

            }
        });
        AddIncomeDituresViewModel viewModel12 = ViewModelProviders.of(ReportMonthFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel12.getIncomeDituresMonths(1).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sum0+=Long.valueOf(incomeDitures.get(i).getMoney());
                    }
                    inditous.get(0).setY(sum0);
                }
            }
        });
        AddIncomeDituresViewModel viewModel13 = ViewModelProviders.of(ReportMonthFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel13.getIncomeDituresMonths(2).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sum101+=Long.valueOf(incomeDitures.get(i).getMoney());
                    }
                    inditous.get(1).setY(sum101);
                }
            }
        });
        AddIncomeDituresViewModel viewModel14 = ViewModelProviders.of(ReportMonthFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel14.getIncomeDituresMonths(3).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sum20+=Long.valueOf(incomeDitures.get(i).getMoney());
                    }
                    inditous.get(2).setY(sum20);
                }
            }
        });
        AddIncomeDituresViewModel viewModel15 = ViewModelProviders.of(ReportMonthFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel15.getIncomeDituresMonths(4).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sum30+=Long.valueOf(incomeDitures.get(i).getMoney());
                    }
                    inditous.get(3).setY(sum30);
                }
            }
        });
        AddIncomeDituresViewModel viewModel16 = ViewModelProviders.of(ReportMonthFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel16.getIncomeDituresMonths(5).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sum40+=Long.valueOf(incomeDitures.get(i).getMoney());
                    }
                    inditous.get(4).setY(sum40);
                }
            }
        });

        AddIncomeDituresViewModel viewModel17 = ViewModelProviders.of(ReportMonthFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel17.getIncomeDituresMonths(6).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sum50+=Long.valueOf(incomeDitures.get(i).getMoney());
                    }
                    inditous.get(5).setY(sum50);
                }
            }
        });
        AddIncomeDituresViewModel viewModel18 = ViewModelProviders.of(ReportMonthFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel18.getIncomeDituresMonths(7).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sum60+=Long.valueOf(incomeDitures.get(i).getMoney());
                    }
                    inditous.get(6).setY(sum60);
                }
            }
        });
        AddIncomeDituresViewModel viewModel19 = ViewModelProviders.of(ReportMonthFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel19.getIncomeDituresMonths(8).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sum70+=Long.valueOf(incomeDitures.get(i).getMoney());
                    }
                    inditous.get(7).setY(sum70);
                }
            }
        });
        AddIncomeDituresViewModel viewModel20 = ViewModelProviders.of(ReportMonthFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel20.getIncomeDituresMonths(9).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sum80+=Long.valueOf(incomeDitures.get(i).getMoney());
                    }
                    inditous.get(8).setY(sum80);
                }
            }
        });
        AddIncomeDituresViewModel viewModel21 = ViewModelProviders.of(ReportMonthFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel21.getIncomeDituresMonths(10).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sum90+=Long.valueOf(incomeDitures.get(i).getMoney());
                    }
                    inditous.get(9).setY(sum90);
                }
            }
        });
        AddIncomeDituresViewModel viewModel22 = ViewModelProviders.of(ReportMonthFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel22.getIncomeDituresMonths(11).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sum100+=Long.valueOf(incomeDitures.get(i).getMoney());
                    }
                    inditous.get(10).setY(sum100);
                }
            }
        });
        AddIncomeDituresViewModel viewModel23 = ViewModelProviders.of(ReportMonthFragment.this).get(AddIncomeDituresViewModel.class);
        viewModel23.getIncomeDituresMonths(12).observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if(incomeDitures!=null)
                {
                    for(int i=0;i<incomeDitures.size();i++)
                    {
                        sum110+=Long.valueOf(incomeDitures.get(i).getMoney());
                    }
                    inditous.get(11).setY(sum110);
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
        float barWidth = 0.5f;
        BarDataSet set1, set2;
        set1 = new BarDataSet(inditous, "Khoản thu");
        set1.setColor(Color.rgb(242, 247, 158));
        set2 = new BarDataSet(expenditus, "Khoản chi");
        set1.setColor(Color.rgb(255, 102, 0));
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTextSize(4f);

        // this replaces setStartAtZero(true)
        barChartDay.setPinchZoom(true);
        YAxis leftAxis = barChartDay.getAxisLeft();

        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(20000000000f);
        leftAxis.setAxisMinimum(0f);

        barChartDay.setDrawBarShadow(false);
        barChartDay.getXAxis().setAxisMinimum(1.0f);
        barChartDay.setDrawGridBackground(false);
        barChartDay.getAxisRight().setEnabled(false);
        barChartDay.setData(data);
        barChartDay.setFitBars(false);

        barChartDay.getXAxis().setAxisMaximum(12.9f);
        barChartDay.getBarData().setBarWidth(barWidth);

        barChartDay.animateY(1000);

    }


}