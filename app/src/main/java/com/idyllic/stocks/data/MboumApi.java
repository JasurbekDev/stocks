package com.idyllic.stocks.data;

import com.idyllic.stocks.data.models.Profile;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.data.models.StockResponse;
import com.idyllic.stocks.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MboumApi {

    @Headers("X-Mboum-Secret:" + Utils.MBOUM_API_KEY)
    @GET("co/collections")
    Call<StockResponse> getStocks(@Query("list") String value, @Query("start") int pageNum);

    @Headers("X-Mboum-Secret:" + Utils.MBOUM_API_KEY)
    @GET("qu/quote/")
    Call<List<Stock>> getStock(@Query("symbol") String symbol);

    @Headers("X-Mboum-Secret:" + Utils.MBOUM_API_KEY)
    @GET("qu/quote/profile")
    Call<Profile> getProfile(@Query("symbol") String symbol);

}
