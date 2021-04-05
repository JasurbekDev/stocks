package com.idyllic.stocks.ui.adapters;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.ui.layouts.ChartFragment;
import com.idyllic.stocks.ui.layouts.HomeStockAdapterListener;
import com.idyllic.stocks.ui.layouts.StocksFragment;
import com.idyllic.stocks.ui.layouts.SummaryFragment;
import com.idyllic.stocks.utils.Utils;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private List<Utils.StockValues> stockValues;
    private HomeStockAdapterListener homeStockAdapterListener;
    private Stock stock;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Utils.StockValues> stockValues) {
        super(fragmentActivity);
        this.stockValues = stockValues;
    }

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Utils.StockValues> stockValues, Stock stock) {
        super(fragmentActivity);
        this.stockValues = stockValues;
        this.stock = stock;
    }

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Utils.StockValues> stockValues, HomeStockAdapterListener homeStockAdapterListener) {
        super(fragmentActivity);
        this.stockValues = stockValues;
        this.homeStockAdapterListener = homeStockAdapterListener;
    }

    @Override
    public int getItemCount() {
        return stockValues.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Utils.StockValues stockValue = this.stockValues.get(position);
        switch (stockValue) {
            case CHART:
                return ChartFragment.getInstance(stock);
            case SUMMARY:
                return SummaryFragment.getInstance(stock);
            case NEWS:

                break;
        }
        return StocksFragment.getInstance(stockValue == Utils.StockValues.FAVOURITES, Utils.StockValues.values()[position], homeStockAdapterListener);
    }
}
