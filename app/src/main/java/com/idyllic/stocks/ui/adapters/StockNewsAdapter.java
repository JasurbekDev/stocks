package com.idyllic.stocks.ui.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idyllic.stocks.R;
import com.idyllic.stocks.data.models.StockNews;
import com.idyllic.stocks.data.models.Summary;

import java.util.Arrays;
import java.util.List;

public class StockNewsAdapter extends RecyclerView.Adapter<StockNewsAdapter.StockNewsViewHolder> {

    private List<StockNews> stockNewsList;

    public StockNewsAdapter(List<StockNews> stockNewsList) {
        this.stockNewsList = stockNewsList;
    }

    @NonNull
    @Override
    public StockNewsAdapter.StockNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StockNewsAdapter.StockNewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StockNewsAdapter.StockNewsViewHolder holder, int position) {
        holder.bind(stockNewsList.get(position));
    }

    @Override
    public int getItemCount() {
        return stockNewsList.size();
    }

    public void setStockNewsList(List<StockNews> stockNewsList) {
        this.stockNewsList = stockNewsList;
    }

    static class StockNewsViewHolder extends RecyclerView.ViewHolder {

        public StockNewsViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(StockNews stockNews) {
            TextView title = itemView.findViewById(R.id.news_title);
            TextView description = itemView.findViewById(R.id.news_description);
            TextView newsDate = itemView.findViewById(R.id.news_date);

            title.setText(stockNews.getTitle());
            description.setText(stockNews.getDescription());
            List<String> dateParts = Arrays.asList(stockNews.getPubDate().split(" "));
            StringBuilder date = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                date.append(" ").append(dateParts.get(i));
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(stockNews.getLink()));
                    itemView.getContext().startActivity(intent);
                }
            });

            newsDate.setText(date.toString());
        }
    }
}
