package com.idyllic.stocks.ui.layouts;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.idyllic.stocks.R;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.ui.adapters.ViewPagerAdapter;
import com.idyllic.stocks.utils.Utils;
import com.idyllic.stocks.viewmodel.StocksViewModel;

import java.util.ArrayList;
import java.util.List;

public class CardFragment extends Fragment {

    private Stock stock;
    private TextView symbolTv;
    private TextView shortNameTv;
    private ImageView arrowBack;
    private ImageView star;
    private StocksViewModel viewModel;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private List<String> titles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        stock = CardFragmentArgs.fromBundle(getArguments()).getStock();

        viewModel = new ViewModelProvider(requireActivity()).get(StocksViewModel.class);

        initLayout(view);

        titles.add("Chart");
        titles.add("Summary");
        titles.add("News");

        List<Utils.StockValues> stockValues = new ArrayList<>();
        stockValues.add(Utils.StockValues.CHART);
        stockValues.add(Utils.StockValues.SUMMARY);
        stockValues.add(Utils.StockValues.NEWS);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(requireActivity(), stockValues, stock);
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                View titleView =
                        LayoutInflater.from(getActivity()).inflate(R.layout.item_tab, null, false);
                tab.setCustomView(titleView);
                TextView titleTv = titleView.findViewById(R.id.title);
                titleTv.setText(titles.get(position));

                if (position == 0) {
                    titleTv.setTextColor(Color.parseColor("#000000"));
                    titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22F);
                } else {
                    titleTv.setTextColor(Color.parseColor("#BABABA"));
                    titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F);
                }
            }
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                ((TextView) customView.findViewById(R.id.title)).setTextColor(Color.parseColor("#000000"));
                ((TextView) customView.findViewById(R.id.title)).setTextSize(TypedValue.COMPLEX_UNIT_SP, 22F);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                ((TextView) customView.findViewById(R.id.title)).setTextColor(Color.parseColor("#BABABA"));
                ((TextView) customView.findViewById(R.id.title)).setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }

    private void initLayout(View view) {
        symbolTv = view.findViewById(R.id.card_stock_symbol);
        shortNameTv = view.findViewById(R.id.card_stock_short_name);
        arrowBack = view.findViewById(R.id.card_arrow_back);
        star = view.findViewById(R.id.card_star_iv);
        tabLayout = view.findViewById(R.id.card_tab_layout);
        viewPager = view.findViewById(R.id.card_view_pager);

        symbolTv.setText(stock.getSymbol());
        shortNameTv.setText(stock.getShortName());
        if (stock.isLiked()) {
            star.setImageResource(R.drawable.ic_card_star_filled);
        }

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stock.isLiked()) {
                    star.setImageResource(R.drawable.ic_card_star_unfilled);
                    stock.setLiked(false);
                    viewModel.dislike(stock.getSymbol());
                } else {
                    star.setImageResource(R.drawable.ic_card_star_filled);
                    stock.setLiked(true);
                    viewModel.like(stock.getSymbol());
                }
            }
        });
    }
}
