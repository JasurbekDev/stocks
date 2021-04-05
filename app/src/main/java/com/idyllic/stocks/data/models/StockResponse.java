package com.idyllic.stocks.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StockResponse {

    @SerializedName("total")
    private int total;

    @SerializedName("start")
    private int start;

    @SerializedName("count")
    private int count;

    @SerializedName("description")
    private String description;

    @SerializedName("quotes")
    private List<Stock> stocks;

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}