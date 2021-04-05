package com.idyllic.stocks.utils;

public class Utils {
    public static final String MBOUM_BASE_URL = "https://mboum.com/api/v1/";
    public static final String FINNHUB_BASE_URL = "https://finnhub.io/api/v1/";
    public static final String MBOUM_API_KEY = "demo";
    public static final String FINNHUB_API_KEY = "c1l6gla37fko6in51s40";
    public static final String FINNHUB_STOCK_LOGO_URL = "https://finnhub.io/api/logo?symbol=";

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
