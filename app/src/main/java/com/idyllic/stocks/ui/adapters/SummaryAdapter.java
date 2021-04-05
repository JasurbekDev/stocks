package com.idyllic.stocks.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idyllic.stocks.R;
import com.idyllic.stocks.data.models.Summary;

import java.util.List;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder> {

    private List<Summary> summaries;

    public SummaryAdapter(List<Summary> summaries) {
        this.summaries = summaries;
    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SummaryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summary, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryViewHolder holder, int position) {
        holder.bind(summaries.get(position));
    }

    @Override
    public int getItemCount() {
        return summaries.size();
    }

    static class SummaryViewHolder extends RecyclerView.ViewHolder {

        public SummaryViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Summary summary) {
            TextView title = itemView.findViewById(R.id.summary_title_tv);
            TextView value = itemView.findViewById(R.id.summary_value_tv);

            title.setText(summary.getTitle());
            value.setText(summary.getValue());
        }
    }
}
