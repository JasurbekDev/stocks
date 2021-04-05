package com.idyllic.stocks.data.models;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "stocks_table")
public class Stock implements Serializable {

	public static final DiffUtil.ItemCallback<Stock> DIFF_CALLBACK = new DiffUtil.ItemCallback<Stock>() {
		@Override
		public boolean areItemsTheSame(@NonNull Stock oldItem, @NonNull Stock newItem) {
			return oldItem.symbol.equals(newItem.symbol);
		}

		@Override
		public boolean areContentsTheSame(@NonNull Stock oldItem, @NonNull Stock newItem) {
			return oldItem.symbol.equals(newItem.symbol) &&
					oldItem.isLiked == newItem.isLiked;
		}

		@Nullable
		@Override
		public Object getChangePayload(@NonNull Stock oldItem, @NonNull Stock newItem) {
			return false;
		}
	};

	@PrimaryKey
	@NonNull
	@SerializedName("symbol")
	private String symbol;

	private boolean isLiked = false;

	private String stockValue;

	private String website;

	@SerializedName("twoHundredDayAverageChangePercent")
	private double twoHundredDayAverageChangePercent;

	@SerializedName("fiftyTwoWeekLowChangePercent")
	private double fiftyTwoWeekLowChangePercent;

	@SerializedName("language")
	private String language;

	@SerializedName("regularMarketDayRange")
	private String regularMarketDayRange;

	@SerializedName("regularMarketDayHigh")
	private double regularMarketDayHigh;

	@SerializedName("twoHundredDayAverageChange")
	private double twoHundredDayAverageChange;

	@SerializedName("askSize")
	private int askSize;

	@SerializedName("twoHundredDayAverage")
	private double twoHundredDayAverage;

	@SerializedName("fiftyTwoWeekHighChange")
	private double fiftyTwoWeekHighChange;

	@SerializedName("marketCap")
	private long marketCap;

	@SerializedName("ipoExpectedDate")
	private String ipoExpectedDate;

	@SerializedName("esgPopulated")
	private boolean esgPopulated;

	@SerializedName("fiftyTwoWeekRange")
	private String fiftyTwoWeekRange;

	@SerializedName("fiftyDayAverageChange")
	private double fiftyDayAverageChange;

	@SerializedName("firstTradeDateMilliseconds")
	private long firstTradeDateMilliseconds;

	@SerializedName("averageDailyVolume3Month")
	private int averageDailyVolume3Month;

	@SerializedName("exchangeDataDelayedBy")
	private int exchangeDataDelayedBy;

	@SerializedName("fiftyTwoWeekLow")
	private double fiftyTwoWeekLow;

	@SerializedName("regularMarketVolume")
	private int regularMarketVolume;

	@SerializedName("market")
	private String market;

	@SerializedName("postMarketPrice")
	private double postMarketPrice;

	@SerializedName("quoteSourceName")
	private String quoteSourceName;

	@SerializedName("messageBoardId")
	private String messageBoardId;

	@SerializedName("priceHint")
	private int priceHint;

	@SerializedName("regularMarketDayLow")
	private double regularMarketDayLow;

	@SerializedName("exchange")
	private String exchange;

	@SerializedName("sourceInterval")
	private int sourceInterval;

	@SerializedName("region")
	private String region;

	@SerializedName("shortName")
	@Nullable
	private String shortName;

	@SerializedName("fiftyDayAverageChangePercent")
	private double fiftyDayAverageChangePercent;

	@SerializedName("fullExchangeName")
	private String fullExchangeName;

	@SerializedName("financialCurrency")
	private String financialCurrency;

	@SerializedName("gmtOffSetMilliseconds")
	private int gmtOffSetMilliseconds;

	@SerializedName("regularMarketOpen")
	private double regularMarketOpen;

	@SerializedName("regularMarketTime")
	private int regularMarketTime;

	@SerializedName("regularMarketChangePercent")
	private double regularMarketChangePercent;

	@SerializedName("quoteType")
	private String quoteType;

	@SerializedName("averageDailyVolume10Day")
	private int averageDailyVolume10Day;

	@SerializedName("fiftyTwoWeekLowChange")
	private double fiftyTwoWeekLowChange;

	@SerializedName("fiftyTwoWeekHighChangePercent")
	private double fiftyTwoWeekHighChangePercent;

	@SerializedName("tradeable")
	private boolean tradeable;

	@SerializedName("postMarketTime")
	private int postMarketTime;

	@SerializedName("currency")
	private String currency;

	@SerializedName("sharesOutstanding")
	private long sharesOutstanding;

	@SerializedName("regularMarketPreviousClose")
	private double regularMarketPreviousClose;

	@SerializedName("fiftyTwoWeekHigh")
	private double fiftyTwoWeekHigh;

	@SerializedName("postMarketChangePercent")
	private double postMarketChangePercent;

	@SerializedName("exchangeTimezoneName")
	private String exchangeTimezoneName;

	@SerializedName("regularMarketChange")
	private double regularMarketChange;

	@SerializedName("bidSize")
	private int bidSize;

	@SerializedName("fiftyDayAverage")
	private double fiftyDayAverage;

	@SerializedName("exchangeTimezoneShortName")
	private String exchangeTimezoneShortName;

	@SerializedName("regularMarketPrice")
	private double regularMarketPrice;

	@SerializedName("marketState")
	private String marketState;

	@SerializedName("postMarketChange")
	private double postMarketChange;

	@SerializedName("ask")
	private double ask;

	@SerializedName("bid")
	private double bid;

	@SerializedName("triggerable")
	private boolean triggerable;

	@SerializedName("longName")
	private String longName;

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getSymbol(){
		return symbol;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean liked) {
		isLiked = liked;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getStockValue() {
		return stockValue;
	}

	public void setStockValue(String stockValue) {
		this.stockValue = stockValue;
	}

	public void setTwoHundredDayAverageChangePercent(double twoHundredDayAverageChangePercent){
		this.twoHundredDayAverageChangePercent = twoHundredDayAverageChangePercent;
	}

	public double getTwoHundredDayAverageChangePercent(){
		return twoHundredDayAverageChangePercent;
	}

	public void setFiftyTwoWeekLowChangePercent(double fiftyTwoWeekLowChangePercent){
		this.fiftyTwoWeekLowChangePercent = fiftyTwoWeekLowChangePercent;
	}

	public double getFiftyTwoWeekLowChangePercent(){
		return fiftyTwoWeekLowChangePercent;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public String getLanguage(){
		return language;
	}

	public void setRegularMarketDayRange(String regularMarketDayRange){
		this.regularMarketDayRange = regularMarketDayRange;
	}

	public String getRegularMarketDayRange(){
		return regularMarketDayRange;
	}

	public void setRegularMarketDayHigh(double regularMarketDayHigh){
		this.regularMarketDayHigh = regularMarketDayHigh;
	}

	public double getRegularMarketDayHigh(){
		return regularMarketDayHigh;
	}

	public void setTwoHundredDayAverageChange(double twoHundredDayAverageChange){
		this.twoHundredDayAverageChange = twoHundredDayAverageChange;
	}

	public double getTwoHundredDayAverageChange(){
		return twoHundredDayAverageChange;
	}

	public void setAskSize(int askSize){
		this.askSize = askSize;
	}

	public int getAskSize(){
		return askSize;
	}

	public void setTwoHundredDayAverage(double twoHundredDayAverage){
		this.twoHundredDayAverage = twoHundredDayAverage;
	}

	public double getTwoHundredDayAverage(){
		return twoHundredDayAverage;
	}

	public void setFiftyTwoWeekHighChange(double fiftyTwoWeekHighChange){
		this.fiftyTwoWeekHighChange = fiftyTwoWeekHighChange;
	}

	public double getFiftyTwoWeekHighChange(){
		return fiftyTwoWeekHighChange;
	}

	public void setMarketCap(long marketCap){
		this.marketCap = marketCap;
	}

	public long getMarketCap(){
		return marketCap;
	}

	public void setIpoExpectedDate(String ipoExpectedDate){
		this.ipoExpectedDate = ipoExpectedDate;
	}

	public String getIpoExpectedDate(){
		return ipoExpectedDate;
	}

	public void setEsgPopulated(boolean esgPopulated){
		this.esgPopulated = esgPopulated;
	}

	public boolean isEsgPopulated(){
		return esgPopulated;
	}

	public void setFiftyTwoWeekRange(String fiftyTwoWeekRange){
		this.fiftyTwoWeekRange = fiftyTwoWeekRange;
	}

	public String getFiftyTwoWeekRange(){
		return fiftyTwoWeekRange;
	}

	public void setFiftyDayAverageChange(double fiftyDayAverageChange){
		this.fiftyDayAverageChange = fiftyDayAverageChange;
	}

	public double getFiftyDayAverageChange(){
		return fiftyDayAverageChange;
	}

	public void setFirstTradeDateMilliseconds(long firstTradeDateMilliseconds){
		this.firstTradeDateMilliseconds = firstTradeDateMilliseconds;
	}

	public long getFirstTradeDateMilliseconds(){
		return firstTradeDateMilliseconds;
	}

	public void setAverageDailyVolume3Month(int averageDailyVolume3Month){
		this.averageDailyVolume3Month = averageDailyVolume3Month;
	}

	public int getAverageDailyVolume3Month(){
		return averageDailyVolume3Month;
	}

	public void setExchangeDataDelayedBy(int exchangeDataDelayedBy){
		this.exchangeDataDelayedBy = exchangeDataDelayedBy;
	}

	public int getExchangeDataDelayedBy(){
		return exchangeDataDelayedBy;
	}

	public void setFiftyTwoWeekLow(double fiftyTwoWeekLow){
		this.fiftyTwoWeekLow = fiftyTwoWeekLow;
	}

	public double getFiftyTwoWeekLow(){
		return fiftyTwoWeekLow;
	}

	public void setRegularMarketVolume(int regularMarketVolume){
		this.regularMarketVolume = regularMarketVolume;
	}

	public int getRegularMarketVolume(){
		return regularMarketVolume;
	}

	public void setMarket(String market){
		this.market = market;
	}

	public String getMarket(){
		return market;
	}

	public void setPostMarketPrice(double postMarketPrice){
		this.postMarketPrice = postMarketPrice;
	}

	public double getPostMarketPrice(){
		return postMarketPrice;
	}

	public void setQuoteSourceName(String quoteSourceName){
		this.quoteSourceName = quoteSourceName;
	}

	public String getQuoteSourceName(){
		return quoteSourceName;
	}

	public void setMessageBoardId(String messageBoardId){
		this.messageBoardId = messageBoardId;
	}

	public String getMessageBoardId(){
		return messageBoardId;
	}

	public void setPriceHint(int priceHint){
		this.priceHint = priceHint;
	}

	public int getPriceHint(){
		return priceHint;
	}

	public void setRegularMarketDayLow(double regularMarketDayLow){
		this.regularMarketDayLow = regularMarketDayLow;
	}

	public double getRegularMarketDayLow(){
		return regularMarketDayLow;
	}

	public void setExchange(String exchange){
		this.exchange = exchange;
	}

	public String getExchange(){
		return exchange;
	}

	public void setSourceInterval(int sourceInterval){
		this.sourceInterval = sourceInterval;
	}

	public int getSourceInterval(){
		return sourceInterval;
	}

	public void setRegion(String region){
		this.region = region;
	}

	public String getRegion(){
		return region;
	}

	public void setShortName(String shortName){
		this.shortName = shortName;
	}

	public String getShortName(){
		return shortName;
	}

	public void setFiftyDayAverageChangePercent(double fiftyDayAverageChangePercent){
		this.fiftyDayAverageChangePercent = fiftyDayAverageChangePercent;
	}

	public double getFiftyDayAverageChangePercent(){
		return fiftyDayAverageChangePercent;
	}

	public void setFullExchangeName(String fullExchangeName){
		this.fullExchangeName = fullExchangeName;
	}

	public String getFullExchangeName(){
		return fullExchangeName;
	}

	public void setFinancialCurrency(String financialCurrency){
		this.financialCurrency = financialCurrency;
	}

	public String getFinancialCurrency(){
		return financialCurrency;
	}

	public void setGmtOffSetMilliseconds(int gmtOffSetMilliseconds){
		this.gmtOffSetMilliseconds = gmtOffSetMilliseconds;
	}

	public int getGmtOffSetMilliseconds(){
		return gmtOffSetMilliseconds;
	}

	public void setRegularMarketOpen(double regularMarketOpen){
		this.regularMarketOpen = regularMarketOpen;
	}

	public double getRegularMarketOpen(){
		return regularMarketOpen;
	}

	public void setRegularMarketTime(int regularMarketTime){
		this.regularMarketTime = regularMarketTime;
	}

	public int getRegularMarketTime(){
		return regularMarketTime;
	}

	public void setRegularMarketChangePercent(double regularMarketChangePercent){
		this.regularMarketChangePercent = regularMarketChangePercent;
	}

	public double getRegularMarketChangePercent(){
		return regularMarketChangePercent;
	}

	public void setQuoteType(String quoteType){
		this.quoteType = quoteType;
	}

	public String getQuoteType(){
		return quoteType;
	}

	public void setAverageDailyVolume10Day(int averageDailyVolume10Day){
		this.averageDailyVolume10Day = averageDailyVolume10Day;
	}

	public int getAverageDailyVolume10Day(){
		return averageDailyVolume10Day;
	}

	public void setFiftyTwoWeekLowChange(double fiftyTwoWeekLowChange){
		this.fiftyTwoWeekLowChange = fiftyTwoWeekLowChange;
	}

	public double getFiftyTwoWeekLowChange(){
		return fiftyTwoWeekLowChange;
	}

	public void setFiftyTwoWeekHighChangePercent(double fiftyTwoWeekHighChangePercent){
		this.fiftyTwoWeekHighChangePercent = fiftyTwoWeekHighChangePercent;
	}

	public double getFiftyTwoWeekHighChangePercent(){
		return fiftyTwoWeekHighChangePercent;
	}

	public void setTradeable(boolean tradeable){
		this.tradeable = tradeable;
	}

	public boolean isTradeable(){
		return tradeable;
	}

	public void setPostMarketTime(int postMarketTime){
		this.postMarketTime = postMarketTime;
	}

	public int getPostMarketTime(){
		return postMarketTime;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setSharesOutstanding(long sharesOutstanding){
		this.sharesOutstanding = sharesOutstanding;
	}

	public long getSharesOutstanding(){
		return sharesOutstanding;
	}

	public void setRegularMarketPreviousClose(double regularMarketPreviousClose){
		this.regularMarketPreviousClose = regularMarketPreviousClose;
	}

	public double getRegularMarketPreviousClose(){
		return regularMarketPreviousClose;
	}

	public void setFiftyTwoWeekHigh(double fiftyTwoWeekHigh){
		this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
	}

	public double getFiftyTwoWeekHigh(){
		return fiftyTwoWeekHigh;
	}

	public void setPostMarketChangePercent(double postMarketChangePercent){
		this.postMarketChangePercent = postMarketChangePercent;
	}

	public double getPostMarketChangePercent(){
		return postMarketChangePercent;
	}

	public void setExchangeTimezoneName(String exchangeTimezoneName){
		this.exchangeTimezoneName = exchangeTimezoneName;
	}

	public String getExchangeTimezoneName(){
		return exchangeTimezoneName;
	}

	public void setRegularMarketChange(double regularMarketChange){
		this.regularMarketChange = regularMarketChange;
	}

	public double getRegularMarketChange(){
		return regularMarketChange;
	}

	public void setBidSize(int bidSize){
		this.bidSize = bidSize;
	}

	public int getBidSize(){
		return bidSize;
	}

	public void setFiftyDayAverage(double fiftyDayAverage){
		this.fiftyDayAverage = fiftyDayAverage;
	}

	public double getFiftyDayAverage(){
		return fiftyDayAverage;
	}

	public void setExchangeTimezoneShortName(String exchangeTimezoneShortName){
		this.exchangeTimezoneShortName = exchangeTimezoneShortName;
	}

	public String getExchangeTimezoneShortName(){
		return exchangeTimezoneShortName;
	}

	public void setRegularMarketPrice(double regularMarketPrice){
		this.regularMarketPrice = regularMarketPrice;
	}

	public double getRegularMarketPrice(){
		return regularMarketPrice;
	}

	public void setMarketState(String marketState){
		this.marketState = marketState;
	}

	public String getMarketState(){
		return marketState;
	}

	public void setPostMarketChange(double postMarketChange){
		this.postMarketChange = postMarketChange;
	}

	public double getPostMarketChange(){
		return postMarketChange;
	}

	public void setAsk(double ask){
		this.ask = ask;
	}

	public double getAsk(){
		return ask;
	}

	public void setBid(double bid){
		this.bid = bid;
	}

	public double getBid(){
		return bid;
	}

	public void setTriggerable(boolean triggerable){
		this.triggerable = triggerable;
	}

	public boolean isTriggerable(){
		return triggerable;
	}

	public void setLongName(String longName){
		this.longName = longName;
	}

	public String getLongName(){
		return longName;
	}
}