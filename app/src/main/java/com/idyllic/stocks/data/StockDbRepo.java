package com.idyllic.stocks.data;

import androidx.lifecycle.LiveData;

import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.data.models.StockResponse;

import java.util.List;

public interface StockDbRepo {
    LiveData<List<Stock>> getDbStocks(String stockValue);
    void insertStock(Stock stock);
    LiveData<List<Stock>> getLikedStocks();
    void liked(String symbol);
    void disliked(String symbol);
}
