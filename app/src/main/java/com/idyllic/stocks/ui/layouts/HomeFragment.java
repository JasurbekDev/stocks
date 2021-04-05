package com.idyllic.stocks.ui.layouts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.idyllic.stocks.R;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.ui.adapters.StockAdapter;
import com.idyllic.stocks.ui.adapters.ViewPagerAdapter;
import com.idyllic.stocks.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static androidx.navigation.Navigation.findNavController;

public class HomeFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "HomeFragment";
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private List<String> titles = new ArrayList<>();
    private SearchView searchView;
    private View view;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        searchView = view.findViewById(R.id.home_search_bar);
        tabLayout = view.findViewById(R.id.home_tablayout);
        viewPager = view.findViewById(R.id.home_view_pager);

        titles.add("Day gainers");
        titles.add("Favourites");
        titles.add("Most actives");
        titles.add("Day losers");

        setSearchViewOnClickListener(searchView, this);


        List<Utils.StockValues> stockValues = new ArrayList<>();
        stockValues.add(Utils.StockValues.DAY_GAINERS);
        stockValues.add(Utils.StockValues.FAVOURITES);
        stockValues.add(Utils.StockValues.MOST_ACTIVES);
        stockValues.add(Utils.StockValues.DAY_LOSERS);

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


    public static void setSearchViewOnClickListener(View v, View.OnClickListener listener) {
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) v;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = group.getChildAt(i);
                if (child instanceof LinearLayout || child instanceof RelativeLayout) {
                    setSearchViewOnClickListener(child, listener);
                }

                if (child instanceof TextView) {
                    TextView text = (TextView) child;
                    text.setFocusable(false);
                }
                child.setOnClickListener(listener);
            }
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        navController = findNavController(view);
    }


    @Override
    public void onClick(View v) {
        findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment());
    }
}
