package com.idyllic.stocks.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.idyllic.stocks.data.models.Stock;

@Database(entities = {Stock.class}, version = 1)
public abstract class StockDatabase extends RoomDatabase {
    private static StockDatabase instance;

    public abstract StockDao stockDao();

    public static synchronized StockDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), StockDatabase.class, "stock_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
