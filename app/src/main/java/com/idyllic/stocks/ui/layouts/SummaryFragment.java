package com.idyllic.stocks.ui.layouts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idyllic.stocks.R;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.data.models.Summary;
import com.idyllic.stocks.ui.adapters.SummaryAdapter;
import com.idyllic.stocks.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SummaryFragment extends Fragment {

    private RecyclerView recyclerView;
    private SummaryAdapter adapter;
    private Stock stock;

    public static SummaryFragment getInstance(Stock stock) {
        SummaryFragment summaryFragment = new SummaryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("stock", stock);
        summaryFragment.setArguments(bundle);
        return summaryFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        stock = (Stock) getArguments().getSerializable("stock");

        List<Summary> summaries = new ArrayList<>();
        summaries.add(new Summary("Name", stock.getLongName()));
        summaries.add(new Summary("Fifty day average", Utils.round(stock.getFiftyDayAverage(), 2) + " " + stock.getCurrency()));
        summaries.add(new Summary("Two hundred day average", Utils.round(stock.getTwoHundredDayAverage(), 2) + " " + stock.getCurrency()));
        summaries.add(new Summary("Fifty two week low", Utils.round(stock.getFiftyTwoWeekLow(), 2) + " " + stock.getCurrency()));
        summaries.add(new Summary("Fifty two week high", Utils.round(stock.getFiftyTwoWeekHigh(), 2) + " " + stock.getCurrency()));
        summaries.add(new Summary("Market state", stock.getMarketState()));

        recyclerView = view.findViewById(R.id.summary_rv);

        adapter = new SummaryAdapter(summaries);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("SummFrag", "onStart: ");
    }
}
