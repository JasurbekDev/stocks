package com.idyllic.stocks.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idyllic.stocks.data.models.Stock;

import java.util.List;

@Dao
public interface StockDao {

    @Query("SELECT * FROM stocks_table WHERE stockValue=:stockValue")
    LiveData<List<Stock>> getStocks(String stockValue);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStock(Stock stock);

    @Query("SELECT * FROM stocks_table WHERE isLiked=1")
    LiveData<List<Stock>> getLikedStocks();

    @Query("UPDATE stocks_table SET isLiked=1 WHERE symbol=:symbol")
    void liked(String symbol);

    @Query("UPDATE stocks_table SET isLiked=0 WHERE symbol=:symbol")
    void dislike(String symbol);
}
