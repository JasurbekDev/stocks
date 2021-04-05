package com.idyllic.stocks.data;

import androidx.lifecycle.LiveData;

import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.data.models.StockResponse;

import java.util.List;

public interface StockDbRepo {

    LiveData<List<Stock>> getDbStocks(String stockValue);

    Stock getDbStock(String symbol);

    void updateStock(Stock stock);

    void insertStock(Stock stock);

    void insertStocks(List<Stock> stocks);

    LiveData<List<Stock>> getLikedStocks();

    void liked(String symbol);

    void disliked(String symbol);
}
