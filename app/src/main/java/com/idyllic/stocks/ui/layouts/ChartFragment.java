package com.idyllic.stocks.ui.layouts;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.idyllic.stocks.R;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.idyllic.stocks.utils.Utils.round;

public class ChartFragment extends Fragment {

    private Stock stock;
    private TextView regularMarketPriceTv;
    private TextView regularMarketChangeTv;
    private LineChart lineChart;

    public static ChartFragment getInstance(Stock stock) {
        ChartFragment chartFragment = new ChartFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("stock", stock);
        chartFragment.setArguments(bundle);
        return chartFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        stock = (Stock) getArguments().getSerializable("stock");

        initLayout(view);

        return view;
    }

    private void initLayout(View view) {
        regularMarketPriceTv = view.findViewById(R.id.card_regular_market_price);
        regularMarketChangeTv = view.findViewById(R.id.card_regular_market_change);
        lineChart = view.findViewById(R.id.line_chart);

        setupLineChart(stock, lineChart);

        double regularMarketChange = stock.getRegularMarketChange();

        if (regularMarketChange < 0) {
            regularMarketChangeTv.setTextColor(view.getContext().getResources().getColor(R.color.red));
        } else {
            regularMarketChangeTv.setTextColor(view.getContext().getResources().getColor(R.color.green));
        }
        regularMarketPriceTv.setText(stock.getRegularMarketPrice() + " " + stock.getCurrency());
        regularMarketChangeTv.setText(((regularMarketChange >= 0) ? "+" : "") + round(regularMarketChange, 2) + " " + stock.getCurrency() + " (" + round(stock.getRegularMarketChangePercent(), 2) + "%)");
    }

    private void setupLineChart(Stock stock, LineChart lineChart) {
        ArrayList<String> xValues = new ArrayList<>();
//        xValues.add("Closed");
        xValues.add("Open");
        xValues.add("Low");
        xValues.add("Day high");
        xValues.add("Now");

        setMarkerView(lineChart, stock.getCurrency());

        LineDataSet lineDataSet = new LineDataSet(getEntries(stock), "24 hours");

        lineChart.setDrawMarkerViews(true);

        lineDataSet.setDrawCubic(true);
        lineDataSet.setCubicIntensity(0.13f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setLineWidth(2.5f);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setCircleRadius(5f);
        lineDataSet.setCircleColor(Color.BLACK);
        lineDataSet.setHighLightColor(Color.BLACK);
        lineDataSet.setColor(getResources().getColor(R.color.black));
        Drawable fillGradient = ContextCompat.getDrawable(getContext(), R.drawable.chart_gradient_bg);
        lineDataSet.setFillDrawable(fillGradient);
        LineData data = new LineData(xValues, lineDataSet);
        lineChart.animateX(1000, Easing.EasingOption.EaseOutQuad);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.setData(data);
    }

//    private void setGestureListener(LineChart lineChart, LineDataSet lineDataSet) {
//        lineChart.setOnChartGestureListener(new OnChartGestureListener() {
//            @Override
//            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
//
//            }
//
//            @Override
//            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
//
//            }
//
//            @Override
//            public void onChartLongPressed(MotionEvent me) {
//
//            }
//
//            @Override
//            public void onChartDoubleTapped(MotionEvent me) {
//
//            }
//
//            @Override
//            public void onChartSingleTapped(MotionEvent me) {
//
////                if(lineChart.getMarkerView().isEnabled())
////                {
////                    lineDataSet.setDrawCircles(true);
////                }
////                else
////                {
////                    lineDataSet.setDrawCircles(false);
////                }
//            }
//
//            @Override
//            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
//
//            }
//
//            @Override
//            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
//
//            }
//
//            @Override
//            public void onChartTranslate(MotionEvent me, float dX, float dY) {
//
//            }
//        });
//    }

    private void setMarkerView(LineChart lineChart, String currency) {
        CustomMarkerView mv = new CustomMarkerView(getContext(), R.layout.chart_marker_view, currency);
        lineChart.setMarkerView(mv);
    }


    private ArrayList<Entry> getEntries(Stock stock) {

        float previousClose = (float) Utils.round(stock.getRegularMarketPreviousClose(), 2);
        float regularMarketOpen = (float) Utils.round(stock.getRegularMarketOpen(), 2);
        float dayHigh = (float) Utils.round(stock.getRegularMarketDayHigh(), 2);
        float dayLow = (float) Utils.round(stock.getRegularMarketDayLow(), 2);
        float now = (float) Utils.round(stock.getRegularMarketPrice(), 2);


        ArrayList<Entry> entries = new ArrayList<>();
//        entries.add(new Entry(previousClose, 0));
        entries.add(new Entry(regularMarketOpen, 0));
        entries.add(new Entry(dayLow, 1));
        entries.add(new Entry(dayHigh, 2));
        entries.add(new Entry(now, 3));
        return entries;
    }
}
