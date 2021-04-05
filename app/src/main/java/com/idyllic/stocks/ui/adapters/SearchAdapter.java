package com.idyllic.stocks.ui.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.idyllic.stocks.R;
import com.idyllic.stocks.data.models.SearchResult;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.ui.layouts.HomeStockAdapterListener;

import static com.idyllic.stocks.utils.Utils.round;

public class SearchAdapter extends ListAdapter<SearchResult, SearchAdapter.StockViewHolder> {

    private StockAdapterListener adapterListener;
    private HomeStockAdapterListener homeStockAdapterListener;

    public SearchAdapter() {
        super(SearchResult.DIFF_CALLBACK);
//        this.adapterListener = adapterListener;
//        this.homeStockAdapterListener = homeStockAdapterListener;
    }

    @NonNull
    @Override
    public SearchAdapter.StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);
        return new StockViewHolder(view, adapterListener, homeStockAdapterListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
        holder.bind(getItem(position), position);
    }

//    @Override
//    public int getItemCount() {
//        return getItemCount();
//    }

    class StockViewHolder extends RecyclerView.ViewHolder {

        private TextView symbolTv;
        private TextView shortNameTv;
        private TextView regularMarketPriceTv;
        private TextView regularMarketChangeTv;
        private ImageView starIv;
        private StockAdapterListener adapterListener;
        private HomeStockAdapterListener homeStockAdapterListener;
        private int position;

        private CardView cardView;

        public StockViewHolder(@NonNull View itemView, StockAdapterListener adapterListener, HomeStockAdapterListener homeStockAdapterListener) {
            super(itemView);
            this.adapterListener = adapterListener;
            this.homeStockAdapterListener = homeStockAdapterListener;
        }

        public void bind(SearchResult stock, int position) {
            cardView = itemView.findViewById(R.id.stock_item_root);
            symbolTv = itemView.findViewById(R.id.symbol);
            shortNameTv = itemView.findViewById(R.id.short_name);
            regularMarketPriceTv = itemView.findViewById(R.id.regular_market_price);
            regularMarketChangeTv = itemView.findViewById(R.id.regular_market_change);
            starIv = itemView.findViewById(R.id.star_iv);

            this.position = position;

            symbolTv.setText(stock.getSymbol());
            shortNameTv.setText(stock.getDescription());

            starIv.setVisibility(View.GONE);
            regularMarketPriceTv.setVisibility(View.GONE);
            regularMarketChangeTv.setVisibility(View.GONE);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    homeStockAdapterListener.onCardClick(stock);
//                }
//            });

            int cornerRadius = (int) (itemView.getContext().getResources().getDisplayMetrics().density * 16);
            cardView.setRadius(cornerRadius);
            if (position % 2 == 0) {
                cardView.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.stock_item_dark_bg));
            } else {
                cardView.setCardBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    public interface StockAdapterListener {
        void onStarClick(Stock stock, ImageView starIv);
    }

}
