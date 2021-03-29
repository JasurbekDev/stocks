package com.idyllic.stocks.data;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.idyllic.stocks.data.db.StockDao;
import com.idyllic.stocks.data.db.StockDatabase;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.data.models.StockResponse;
import com.idyllic.stocks.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StocksRepoImpl implements StocksRepo, StockDbRepo {

    private StockDao stockDao;

    public static final String TAG = "StocksrepoImpl";

    private StocksApi stocksApi;
    public MutableLiveData<StockResponse> stockResponse = new MutableLiveData<>();

    public StocksRepoImpl(Application application) {
        stockDao = StockDatabase.getInstance(application).stockDao();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        stocksApi = retrofit.create(StocksApi.class);
    }

    @Override
    public StockResponse getStocks(String value) {
        Call<StockResponse> stocks = stocksApi.getStocks(value);
        stocks.enqueue(new Callback<StockResponse>() {
            @Override
            public void onResponse(Call<StockResponse> call, Response<StockResponse> response) {
                if (response.isSuccessful()) {
                    stockResponse.postValue(response.body());
                    for (Stock stock : response.body().getStocks()) {
                        Log.d(TAG, "onResponse: " + stock.getRegularMarketPrice());
                        Log.d(TAG, "onResponse: " + stock.getSymbol());
                        stock.setStockValue(value);
                        insertStock(stock);
                    }
                }
            }

            @Override
            public void onFailure(Call<StockResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
        return null;
    }

//    public StockResponse getMostWatchedStocks() {
//        Call<List<StockResponse>> stocks = stocksApi.getMostWatchedStocks();
//        stocks.enqueue(new Callback<List<StockResponse>>() {
//            @Override
//            public void onResponse(Call<List<StockResponse>> call, Response<List<StockResponse>> response) {
//                if (response.isSuccessful()) {
//                    stockResponse.postValue(response.body().get(0));
//                    for (Stock stock : response.body().get(0).getStocks()) {
//                        Log.d(TAG, "onResponse: " + stock.getPostMarketChange());
//                        Log.d(TAG, "onResponse: " + stock.getSymbol());
//                        insertStock(stock);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<StockResponse>> call, Throwable t) {
//                Log.e(TAG, "onFailure: ", t);
//            }
//        });
//        return null;
//    }

    @Override
    public LiveData<List<Stock>> getDbStocks(String stockValue) {
        return stockDao.getStocks(stockValue);
    }

    @Override
    public void insertStock(Stock stock) {
        new InsertStockAsyncTask(stockDao).execute(stock);
    }

    @Override
    public LiveData<List<Stock>> getLikedStocks() {
        return stockDao.getLikedStocks();
    }

    @Override
    public void liked(String symbol) {
        new LikeAsyncTask(stockDao, symbol).execute();
    }

    @Override
    public void disliked(String symbol) {
        new DislikeAsyncTask(stockDao, symbol).execute();
    }

    private class InsertStockAsyncTask extends AsyncTask<Stock, Void, Void> {

        private StockDao stockDao;

        public InsertStockAsyncTask(StockDao stockDao) {
            this.stockDao = stockDao;
        }

        @Override
        protected Void doInBackground(Stock... stocks) {
            stockDao.insertStock(stocks[0]);
            return null;
        }
    }

    private class LikeAsyncTask extends AsyncTask<Void, Void, Void> {

        private StockDao stockDao;
        private String symbol;

        public LikeAsyncTask(StockDao stockDao, String symbol) {
            this.stockDao = stockDao;
            this.symbol = symbol;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            stockDao.liked(symbol);
            return null;
        }
    }

    private class DislikeAsyncTask extends AsyncTask<Void, Void, Void> {

        private StockDao stockDao;
        private String symbol;

        public DislikeAsyncTask(StockDao stockDao, String symbol) {
            this.stockDao = stockDao;
            this.symbol = symbol;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            stockDao.dislike(symbol);
            return null;
        }
    }
}
