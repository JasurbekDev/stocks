package com.idyllic.stocks.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    @SerializedName("item")
    private List<StockNews> stockNewsList;
    @SerializedName("language")
    private String language;
    @SerializedName("lastBuildDate")
    private String lastBuildDate;

    public List<StockNews> getStockNewsList() {
        return stockNewsList;
    }

    public String getLanguage() {
        return language;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }
}
