package com.idyllic.stocks.data;

import com.idyllic.stocks.data.models.StockResponse;
import com.idyllic.stocks.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StocksApi {

    @Headers("X-Mboum-Secret:" + Constants.API_KEY)
    @GET("co/collections/?start=1")
    Call<StockResponse> getStocks(@Query("list") String value);

}
