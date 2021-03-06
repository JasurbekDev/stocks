package com.idyllic.stocks.ui.adapters;

import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.idyllic.stocks.R;
import com.idyllic.stocks.data.models.Stock;
import com.idyllic.stocks.ui.layouts.HomeStockAdapterListener;

import static com.idyllic.stocks.utils.Utils.FINNHUB_STOCK_LOGO_URL;
import static com.idyllic.stocks.utils.Utils.round;

public class StockAdapter extends ListAdapter<Stock, StockAdapter.StockViewHolder> {

    private StockAdapterListener adapterListener;
    private HomeStockAdapterListener homeStockAdapterListener;

    public StockAdapter(StockAdapterListener adapterListener) {
        super(Stock.DIFF_CALLBACK);
        this.adapterListener = adapterListener;
        this.homeStockAdapterListener = homeStockAdapterListener;
    }

    @NonNull
    @Override
    public StockAdapter.StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        private ImageView stockIv;
        private StockAdapterListener adapterListener;
        private HomeStockAdapterListener homeStockAdapterListener;
        private int position;

        private CardView cardView;

        public StockViewHolder(@NonNull View itemView, StockAdapterListener adapterListener, HomeStockAdapterListener homeStockAdapterListener) {
            super(itemView);
            this.adapterListener = adapterListener;
            this.homeStockAdapterListener = homeStockAdapterListener;
        }

        public void bind(Stock stock, int position) {
            cardView = itemView.findViewById(R.id.stock_item_root);
            symbolTv = itemView.findViewById(R.id.symbol);
            shortNameTv = itemView.findViewById(R.id.short_name);
            regularMarketPriceTv = itemView.findViewById(R.id.regular_market_price);
            regularMarketChangeTv = itemView.findViewById(R.id.regular_market_change);
            starIv = itemView.findViewById(R.id.star_iv);
            stockIv = itemView.findViewById(R.id.stock_iv);

            this.position = position;

            symbolTv.setText(stock.getSymbol());
            shortNameTv.setText(stock.getShortName());

            double regularMarketChange = stock.getRegularMarketChange();

            Glide.with(itemView.getContext())
                    .load(FINNHUB_STOCK_LOGO_URL + stock.getSymbol())
                    .centerCrop()
                    .placeholder(R.drawable.ic_no_image)
                    .into(stockIv);


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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterListener.onCardClick(stock);
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
    }

    public interface StockAdapterListener {
        void onStarClick(Stock stock, ImageView starIv);

        void onCardClick(Stock stock);
    }

}
