package com.idyllic.stocks.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idyllic.stocks.data.StocksRepoImpl;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.data.models.StockResponse;

import java.util.List;

public class StocksViewModel extends AndroidViewModel {
    public static final String TAG = "StocksViewModel";


    private StocksRepoImpl repository;

    public StocksViewModel(@NonNull Application application) {
        super(application);
        repository = new StocksRepoImpl(application);
    }

    public LiveData<List<Stock>> getStocks(String value) {
        Log.d(TAG, "onChanged: came");
        repository.getStocks(value);
        return repository.getDbStocks(value);
    }

//    public LiveData<List<Stock>> getMostWatchedStocks() {
//        Log.d(TAG, "onChanged: came");
//        repository.getMostWatchedStocks();
//        return repository.getDbStocks();
//    }

    public LiveData<List<Stock>> getLikedStocks() {
        return repository.getLikedStocks();
    }

    public void like(String symbol) {
        repository.liked(symbol);
    }

    public void dislike(String symbol) {
        repository.disliked(symbol);
    }
}
