package com.idyllic.stocks.ui.layouts;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.idyllic.stocks.R;

public class CustomMarkerView extends MarkerView {

    private TextView tvContent;
    private String currency;

    public CustomMarkerView(Context context, int layoutResource, String currency) {
        super(context, layoutResource);

        tvContent = (TextView) findViewById(R.id.tvContent);
        this.currency = currency;
    }


    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(e.getVal() + " " + currency);
    }

    @Override
    public int getXOffset(float xpos) {

        return -(getWidth() / 2);
    }

    @Override
    public int getYOffset(float ypos) {

        return -getHeight() - 10;
    }
}