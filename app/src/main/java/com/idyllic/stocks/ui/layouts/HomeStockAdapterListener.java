package com.idyllic.stocks.ui.layouts;

import android.os.Parcelable;

import com.idyllic.stocks.data.models.Stock;

public interface HomeStockAdapterListener {
    void onCardClick(Stock stock);
}
