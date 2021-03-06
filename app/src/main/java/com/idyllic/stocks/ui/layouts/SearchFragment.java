package com.idyllic.stocks.ui.layouts;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idyllic.stocks.R;
import com.idyllic.stocks.data.models.SearchResult;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.ui.adapters.SearchAdapter;
import com.idyllic.stocks.ui.adapters.StockAdapter;
import com.idyllic.stocks.viewmodel.StocksViewModel;

import java.util.List;

public class SearchFragment extends Fragment implements StockAdapter.StockAdapterListener {

    private SearchView searchView;
    private ProgressBar progressBar;
    private StocksViewModel viewModel;
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private View view;
    private ImageView emptyBoxIv;
    private HomeStockAdapterListener homeStockAdapterListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search, container, false);

        viewModel = new ViewModelProvider(this).get(StocksViewModel.class);
        homeStockAdapterListener = getArguments().getParcelable("homeStockAdapterListener");

        initLayout();

        setupSearchView(searchView);

        return view;
    }

    private void initLayout() {
        searchView = view.findViewById(R.id.search_bar);
        progressBar = view.findViewById(R.id.search_progress_bar);
        recyclerView = view.findViewById(R.id.search_rv);
        emptyBoxIv = view.findViewById(R.id.empty_box_iv);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new SearchAdapter();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        searchView.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void setupSearchView(SearchView searchView) {

        searchView.findFocus();
        ImageView searchClose = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        ImageView backIcon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        searchClose.setImageResource(R.drawable.ic_close);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.clearFocus();
                Navigation.findNavController(view).popBackStack();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (isNetworkConnectionAvailable()) {
                    progressBar.setVisibility(View.VISIBLE);
                    emptyBoxIv.setVisibility(View.GONE);
                    viewModel.searchStocks(newText).observe(getViewLifecycleOwner(), new Observer<List<SearchResult>>() {
                        @Override
                        public void onChanged(List<SearchResult> searchResults) {
                            progressBar.setVisibility(View.GONE);
                            if (searchResults.isEmpty()) {
                                emptyBoxIv.setVisibility(View.VISIBLE);
                            } else {
                                emptyBoxIv.setVisibility(View.GONE);
                            }
                            adapter.submitList(searchResults);
                        }
                    });
                }
                return true;
            }
        });
    }

    @Override
    public void onStarClick(Stock stock, ImageView starIv) {

    }

    @Override
    public void onCardClick(Stock stock) {

    }

    boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) return false;
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }
}
