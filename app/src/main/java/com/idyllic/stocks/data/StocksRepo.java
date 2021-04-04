package com.idyllic.stocks.data;

import androidx.lifecycle.LiveData;

import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.data.models.StockResponse;

import java.util.List;

public interface StocksRepo {
    LiveData<List<Stock>> getRemoteStocks(String value);
}
