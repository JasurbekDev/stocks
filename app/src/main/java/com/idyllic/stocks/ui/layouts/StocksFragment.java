package com.idyllic.stocks.ui.layouts;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idyllic.stocks.R;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.data.models.StockResponse;
import com.idyllic.stocks.ui.adapters.StockAdapter;
import com.idyllic.stocks.utils.Utils;
import com.idyllic.stocks.viewmodel.StocksViewModel;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.NO_ID;

public class StocksFragment extends Fragment implements StockAdapter.StockAdapterListener {

    public static final String TAG = "StocksFragment";
    private static final String IS_FAVOURITES = "isFavourites";
    private static final String STOCK_VALUE = "stockValue";

    private RecyclerView recyclerView;
    private StockAdapter adapter;
    private StocksViewModel viewModel;
    private ProgressBar progressBar;
    private ImageView emptyBoxIv;
    private boolean isFavourites;
    private String stockValue;
    private List<Stock> dbLikedStocks = new ArrayList<>();
    private List<Stock> dbStocks = new ArrayList<>();
    private int searchPageNumber = 1;
    private View view;
    private int searchTotalPageNumber;

    public static StocksFragment getInstance(boolean isFavourites, Utils.StockValues stockValue, HomeStockAdapterListener homeStockAdapterListener) {
        StocksFragment stocksFragment = new StocksFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_FAVOURITES, isFavourites);
        bundle.putString(STOCK_VALUE, stockValue.toString().toLowerCase());


        stocksFragment.setArguments(bundle);
        return stocksFragment;
    }

    public StocksFragment() {
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stocks, container, false);

        recyclerView = view.findViewById(R.id.stock_rv);
        progressBar = view.findViewById(R.id.stocks_progress_bar);
        emptyBoxIv = view.findViewById(R.id.home_empty_box_iv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new StockAdapter(this);
        recyclerView.setAdapter(adapter);

        isFavourites = getArguments().getBoolean(IS_FAVOURITES);
        stockValue = getArguments().getString(STOCK_VALUE);

        viewModel = new ViewModelProvider(this).get(StocksViewModel.class);

        Log.d(TAG, "onCreateView: PageItem Enum: " + Utils.StockValues.DAY_GAINERS.toString().toLowerCase());

        viewModel.getStockResponse().observe(getViewLifecycleOwner(), new Observer<StockResponse>() {
            @Override
            public void onChanged(StockResponse response) {
                if (response != null) {
                    searchTotalPageNumber = (int) Math.ceil((double) response.getTotal() / (double) response.getCount());
                }
            }
        });

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

                    progressBar.setVisibility(View.GONE);
                    if (stocks.isEmpty()) {
                        emptyBoxIv.setVisibility(View.VISIBLE);
                    } else {
                        emptyBoxIv.setVisibility(View.GONE);
                    }
                    adapter.submitList(stocks);
                }
            });
        } else {

            viewModel.getRemoteStocks(stockValue, searchPageNumber).observe(getViewLifecycleOwner(), new Observer<List<Stock>>() {
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

                    progressBar.setVisibility(View.GONE);
                    if (stocks.isEmpty()) {
                        emptyBoxIv.setVisibility(View.VISIBLE);
                    } else {
                        emptyBoxIv.setVisibility(View.GONE);
                    }

                    dbStocks = stocks;
                    adapter.submitList(stocks);
                }
            });
        }

        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                    searchPageNumber++;
                    if (searchTotalPageNumber != 0 && searchPageNumber <= searchTotalPageNumber) {
                        viewModel.getRemoteStocks(stockValue, searchPageNumber);
                    }
                }

            }
        });


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

            viewModel.dislike(stock.getSymbol());
        } else {
            starIv.setImageResource(R.drawable.ic_star_liked);

            viewModel.like(stock.getSymbol());
        }
    }

    @Override
    public void onCardClick(Stock stock) {
        HomeFragmentDirections.ActionHomeFragmentToCardFragment action = HomeFragmentDirections.actionHomeFragmentToCardFragment(stock);

        Navigation.findNavController(view).navigate(action);
    }
}
