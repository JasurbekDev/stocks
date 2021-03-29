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
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.utils.Constants;

public class StockAdapter extends ListAdapter<Stock, StockAdapter.StockViewHolder> {

    private StockAdapterListener adapterListener;

    public StockAdapter(StockAdapterListener adapterListener) {
        super(Stock.DIFF_CALLBACK);
        this.adapterListener = adapterListener;
    }

    @NonNull
    @Override
    public StockAdapter.StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);
        return new StockViewHolder(view, adapterListener);
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

        private CardView cardView;

        public StockViewHolder(@NonNull View itemView, StockAdapterListener adapterListener) {
            super(itemView);
            this.adapterListener = adapterListener;
        }

        public void bind(Stock stock, int position) {
            cardView = itemView.findViewById(R.id.stock_item_root);
            symbolTv = itemView.findViewById(R.id.symbol);
            shortNameTv = itemView.findViewById(R.id.short_name);
            regularMarketPriceTv = itemView.findViewById(R.id.regular_market_price);
            regularMarketChangeTv = itemView.findViewById(R.id.regular_market_change);
            starIv = itemView.findViewById(R.id.star_iv);

            symbolTv.setText(stock.getSymbol());
            shortNameTv.setText(stock.getShortName());

            double regularMarketChange = stock.getRegularMarketChange();

            if (regularMarketChange < 0) {
                regularMarketChangeTv.setTextColor(itemView.getContext().getResources().getColor(R.color.red));
            } else {
                regularMarketChangeTv.setTextColor(itemView.getContext().getResources().getColor(R.color.green));
            }

            regularMarketPriceTv.setText(stock.getRegularMarketPrice() + " " + stock.getCurrency());
            regularMarketChangeTv.setText(((regularMarketChange >= 0) ? "+" : "") + round(regularMarketChange, 2) + " " + stock.getCurrency() + " (" + round(stock.getRegularMarketChangePercent(), 2) + "%)");

            boolean isLiked = stock.isLiked();
            if (isLiked) {
                starIv.setImageResource(R.drawable.ic_star_liked);
            } else {
                starIv.setImageResource(R.drawable.ic_star_unliked);
            }

            starIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterListener.onStarClick(stock, starIv);
                }
            });

            int cornerRadius = (int) (itemView.getContext().getResources().getDisplayMetrics().density * 16);
            cardView.setRadius(cornerRadius);
            if (position % 2 == 0) {
                cardView.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.stock_item_dark_bg));
            } else {
                cardView.setCardBackgroundColor(Color.TRANSPARENT);
            }
        }

        public double round(double value, int places) {
            if (places < 0) throw new IllegalArgumentException();

            long factor = (long) Math.pow(10, places);
            value = value * factor;
            long tmp = Math.round(value);
            return (double) tmp / factor;
        }
    }

    public interface StockAdapterListener {
        void onStarClick(Stock stock, ImageView starIv);
    }
}
