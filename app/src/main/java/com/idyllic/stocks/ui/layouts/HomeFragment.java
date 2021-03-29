package com.idyllic.stocks.ui.layouts;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.idyllic.stocks.R;
import com.idyllic.stocks.ui.adapters.ViewPagerAdapter;
import com.idyllic.stocks.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private List<String> titles = new ArrayList<>();
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        searchView = view.findViewById(R.id.home_search_bar);
        tabLayout = view.findViewById(R.id.home_tablayout);
        viewPager = view.findViewById(R.id.home_view_pager);

        titles.add("Day gainers");
        titles.add("Favourites");
        titles.add("Most actives");
        titles.add("Day losers");

//        TextView textView = new TextView(getContext());
//        textView.setText("Stocks");

//        tabLayout.addTab(tabLayout.newTab().setText("Day gainers"));
//        tabLayout.addTab(tabLayout.newTab().setText("Favourite"));
//        tabLayout.addTab(tabLayout.newTab().setText("3"));
//        tabLayout.addTab(tabLayout.newTab().setText("4"));

        List<Constants.StockValues> stockValues = new ArrayList<>();
        stockValues.add(Constants.StockValues.DAY_GAINERS);
        stockValues.add(Constants.StockValues.FAVOURITES);
        stockValues.add(Constants.StockValues.MOST_ACTIVES);
        stockValues.add(Constants.StockValues.DAY_LOSERS);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(requireActivity(), stockValues);
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
}
