package com.idyllic.stocks.data;

import com.idyllic.stocks.data.models.StockResponse;

public interface StocksRepo {
    StockResponse getStocks(String value);
}
