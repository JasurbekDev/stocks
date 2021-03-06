package com.idyllic.stocks.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idyllic.stocks.data.StocksRepoImpl;
import com.idyllic.stocks.data.models.SearchResult;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.data.models.StockNews;
import com.idyllic.stocks.data.models.StockResponse;

import java.util.List;

public class StocksViewModel extends AndroidViewModel {
    public static final String TAG = "StocksViewModel";


    private StocksRepoImpl repository;


    public void insertStocks(List<Stock> stocks) {
        repository.insertStocks(stocks);
    }

    public void insertStock(Stock stock) {
        repository.insertStock(stock);
    }

    public void updateStock(Stock stock) {
        repository.updateStock(stock);
    }

    public StocksViewModel(@NonNull Application application) {
        super(application);
        repository = new StocksRepoImpl(application);
    }

    public LiveData<List<Stock>> getDbStocks(String value) {
        Log.d(TAG, "onChanged: came");
        return repository.getDbStocks(value);
    }

    public LiveData<List<Stock>> getRemoteStocks(String value, int pageNum) {
        return repository.getRemoteStocks(value, pageNum);
    }

    public LiveData<StockResponse> getStockResponse() {
        return repository.getStockResponse();
    }


    public LiveData<List<Stock>> getLikedStocks() {
        return repository.getLikedStocks();
    }

    public void like(String symbol) {
        repository.liked(symbol);
    }

    public void dislike(String symbol) {
        repository.disliked(symbol);
    }

    public LiveData<List<SearchResult>> searchStocks(String query) {
        return repository.searchStocks(query);
    }

    public LiveData<List<StockNews>> getStockNews(String symbol) {
        return repository.getStockNews(symbol);
    }

    public LiveData<Boolean> isStockNewsLoading() {
        return repository.getIsStockNewsLoading();
    }


}
