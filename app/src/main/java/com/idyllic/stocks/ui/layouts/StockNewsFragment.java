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
import com.idyllic.stocks.data.models.StockNews;
import com.idyllic.stocks.ui.adapters.StockNewsAdapter;
import com.idyllic.stocks.viewmodel.StocksViewModel;

import java.util.ArrayList;
import java.util.List;

public class StockNewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private StockNewsAdapter adapter;
    private StocksViewModel viewModel;
    private ProgressBar progressBar;
    private ImageView emptyBoxIv;
    private String symbol;

    public static StockNewsFragment getInstance(String symmbol) {
        StockNewsFragment stockNewsFragment = new StockNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("symbol", symmbol);
        stockNewsFragment.setArguments(bundle);
        return stockNewsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stock_news, container, false);

        symbol = getArguments().getString("symbol");

        viewModel = new ViewModelProvider(this).get(StocksViewModel.class);

        progressBar = view.findViewById(R.id.stock_news_progress_bar);
        recyclerView = view.findViewById(R.id.stock_news_rv);
        emptyBoxIv = view.findViewById(R.id.news_empty_box_iv);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new StockNewsAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel.getStockNews(symbol).observe(getViewLifecycleOwner(), new Observer<List<StockNews>>() {
            @Override
            public void onChanged(List<StockNews> stockNewsList) {
                if (stockNewsList != null) {
                    if (stockNewsList.isEmpty()) {
                        emptyBoxIv.setVisibility(View.VISIBLE);
                    }
                    adapter.setStockNewsList(stockNewsList);
                    adapter.notifyDataSetChanged();
                } else {
                    emptyBoxIv.setVisibility(View.VISIBLE);
                }
            }
        });

        viewModel.isStockNewsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    progressBar.setVisibility(View.VISIBLE);
                    emptyBoxIv.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }
}
