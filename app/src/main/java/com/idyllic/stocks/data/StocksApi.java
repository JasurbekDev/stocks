package com.idyllic.stocks.data;

import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.data.models.StockResponse;
import com.idyllic.stocks.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface StocksApi {

    @Headers("X-Mboum-Secret:" + Utils.API_KEY)
    @GET("co/collections/?start=1")
    Call<StockResponse> getStocks(@Query("list") String value);

    @Headers("X-Mboum-Secret:" + Utils.API_KEY)
    @GET("qu/quote/")
    Call<List<Stock>> getStock(@Query("symbol") String symbol);

}
