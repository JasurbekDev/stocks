package com.idyllic.stocks.utils;

public class Utils {
    public static final String BASE_URL = "https://mboum.com/api/v1/";
    public static final String API_KEY = "2v8m3Jpzpz9NvEbrOLKx2kkag1PTZcuK1kRuo3jxEyyW4slM5Ff3PSVhif3U";

    public enum StockValues {
        DAY_GAINERS,
        FAVOURITES,
        MOST_ACTIVES,
        DAY_LOSERS,
        CHART,
        SUMMARY,
        NEWS
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
