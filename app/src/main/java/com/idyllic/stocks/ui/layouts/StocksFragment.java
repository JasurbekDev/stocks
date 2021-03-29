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
import com.idyllic.stocks.utils.Constants;
import com.idyllic.stocks.viewmodel.StocksViewModel;

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

    public static StocksFragment getInstance(boolean isFavourites, Constants.StockValues stockValue) {
        StocksFragment stocksFragment = new StocksFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_FAVOURITES, isFavourites);
        bundle.putString(STOCK_VALUE, stockValue.toString().toLowerCase());
        stocksFragment.setArguments(bundle);
        return stocksFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stocks, container, false);

        recyclerView = view.findViewById(R.id.stock_rv);
        progressBar = view.findViewById(R.id.stocks_progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new StockAdapter(this);
        recyclerView.setAdapter(adapter);

        isFavourites = getArguments().getBoolean(IS_FAVOURITES);
        stockValue = getArguments().getString(STOCK_VALUE);

        viewModel = new ViewModelProvider(this).get(StocksViewModel.class);

        Log.d(TAG, "onCreateView: PageItem Enum: " + Constants.StockValues.DAY_GAINERS.toString().toLowerCase());

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
            viewModel.getStocks(stockValue).observe(getActivity(), new Observer<List<Stock>>() {
                @Override
                public void onChanged(List<Stock> stocks) {
//                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
//                    Log.d(TAG, "onChanged: " + stocks.get(0).getRegularMarketChange());
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
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStarClick(Stock stock, ImageView starIv) {
        boolean isLiked = stock.isLiked();
        if (isLiked) {
            starIv.setImageResource(R.drawable.ic_star_unliked);
            viewModel.dislike(stock.getSymbol());
        } else {
            starIv.setImageResource(R.drawable.ic_star_liked);
            viewModel.like(stock.getSymbol());
        }
    }
}
