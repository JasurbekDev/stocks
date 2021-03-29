package com.idyllic.stocks.ui.adapters;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.idyllic.stocks.ui.layouts.StocksFragment;
import com.idyllic.stocks.utils.Constants;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private List<Constants.StockValues> stockValues;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Constants.StockValues> stockValues) {
        super(fragmentActivity);
        this.stockValues = stockValues;
    }

    @Override
    public int getItemCount() {
        return stockValues.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Constants.StockValues stockValues = this.stockValues.get(position);
        return StocksFragment.getInstance(stockValues == Constants.StockValues.FAVOURITES, Constants.StockValues.values()[position]);
    }
}
