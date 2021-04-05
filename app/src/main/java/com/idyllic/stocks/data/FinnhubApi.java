package com.idyllic.stocks.data;

import com.idyllic.stocks.data.models.SearchResponse;
import com.idyllic.stocks.utils.Utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface FinnhubApi {

    @Headers("X-Finnhub-Token:" + Utils.FINNHUB_API_KEY)
    @GET("search")
    Call<SearchResponse> searchStocks(@Query("q") String query);
}
