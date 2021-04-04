package com.idyllic.stocks.ui.layouts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idyllic.stocks.R;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.ui.adapters.StockAdapter;
import com.idyllic.stocks.utils.Utils;
import com.idyllic.stocks.viewmodel.StocksViewModel;

import java.util.ArrayList;
import java.util.List;

public class StocksFragment extends Fragment implements StockAdapter.StockAdapterListener {

    public static final String TAG = "StocksFragment";
    private static final String IS_FAVOURITES = "isFavourites";
    private static final String STOCK_VALUE = "stockValue";

    private RecyclerView recyclerView;
    private StockAdapter adapter;
    private StocksViewModel viewModel;
    private ProgressBar progressBar;
    private boolean isFavourites;
    private String stockValue;
    private List<Stock> dbLikedStocks = new ArrayList<>();
    private List<Stock> dbStocks = new ArrayList<>();
    private StockAdapter.HomeStockAdapterListener homeStockAdapterListener;

    public static StocksFragment getInstance(boolean isFavourites, Utils.StockValues stockValue, StockAdapter.HomeStockAdapterListener homeStockAdapterListener) {
        StocksFragment stocksFragment = new StocksFragment(homeStockAdapterListener);
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_FAVOURITES, isFavourites);
        bundle.putString(STOCK_VALUE, stockValue.toString().toLowerCase());

        stocksFragment.setArguments(bundle);
        return stocksFragment;
    }

    public StocksFragment(StockAdapter.HomeStockAdapterListener homeStockAdapterListener) {
        this.homeStockAdapterListener = homeStockAdapterListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stocks, container, false);

        recyclerView = view.findViewById(R.id.stock_rv);
        progressBar = view.findViewById(R.id.stocks_progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new StockAdapter(this, homeStockAdapterListener);
        recyclerView.setAdapter(adapter);

        isFavourites = getArguments().getBoolean(IS_FAVOURITES);
        stockValue = getArguments().getString(STOCK_VALUE);

        viewModel = new ViewModelProvider(this).get(StocksViewModel.class);

        Log.d(TAG, "onCreateView: PageItem Enum: " + Utils.StockValues.DAY_GAINERS.toString().toLowerCase());

        viewModel.getLikedStocks().observe(getViewLifecycleOwner(), new Observer<List<Stock>>() {
            @Override
            public void onChanged(List<Stock> likedStocks) {
                dbLikedStocks = likedStocks;

            }
        });

        if (isFavourites) {
            viewModel.getLikedStocks().observe(getActivity(), new Observer<List<Stock>>() {
                @Override
                public void onChanged(List<Stock> stocks) {
//                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    adapter.submitList(stocks);
                }
            });
        } else {

            viewModel.getRemoteStocks(stockValue).observe(getViewLifecycleOwner(), new Observer<List<Stock>>() {
                @Override
                public void onChanged(List<Stock> remoteStocks) {

                    for (Stock remoteStock : remoteStocks) {
                        for (Stock dbLikedStock : dbLikedStocks) {
                            if (dbLikedStock.getSymbol().equals(remoteStock.getSymbol()))
                                remoteStock.setLiked(true);
                        }
                    }
                    viewModel.insertStocks(remoteStocks);
                }
            });

            viewModel.getDbStocks(stockValue).observe(getViewLifecycleOwner(), new Observer<List<Stock>>() {
                @Override
                public void onChanged(List<Stock> stocks) {
//                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
//                    Log.d(TAG, "onChanged: " + stocks.get(0).getRegularMarketChange());
                    dbStocks = stocks;
                    adapter.submitList(stocks);
                }
            });
        }


        return view;
    }

    public String getStockValues() {
        return stockValue;
    }

    public void setStockValues(String stockValues) {
        this.stockValue = stockValues;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onStart: HIIIIIIIIIII");
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.submitList(dbStocks);
    }

    @Override
    public void onStarClick(Stock stock, ImageView starIv) {
        boolean isLiked = stock.isLiked();
        if (isLiked) {
            starIv.setImageResource(R.drawable.ic_star_unliked);
//            stock.setLiked(false);
            viewModel.dislike(stock.getSymbol());
        } else {
            starIv.setImageResource(R.drawable.ic_star_liked);
//            stock.setLiked(true);
            viewModel.like(stock.getSymbol());
        }
    }

}
