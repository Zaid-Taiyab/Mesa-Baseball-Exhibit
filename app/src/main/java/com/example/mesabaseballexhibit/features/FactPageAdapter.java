package com.example.mesabaseballexhibit.features;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mesabaseballexhibit.R;

import java.util.List;

public class FactPageAdapter extends RecyclerView.Adapter<FactPageAdapter.PageViewHolder> {

    private final List<FactPage> pages;

    public FactPageAdapter(List<FactPage> pages) {
        this.pages = pages;
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fact_page, parent, false);
        return new PageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int position) {
        FactPage page = pages.get(position);
        holder.container.removeAllViews();

        for (Fact fact : page.getFacts()) {
            View factView = LayoutInflater.from(holder.container.getContext())
                    .inflate(R.layout.item_fact_card, holder.container, false);

            TextView heading = factView.findViewById(R.id.factHeading);
            TextView content = factView.findViewById(R.id.factContent);

            heading.setText(fact.getHeading());
            heading.setTextSize(22); // bigger heading
            heading.setTypeface(null, android.graphics.Typeface.BOLD);

            content.setText(fact.getContent());
            content.setTextSize(18); // bigger content
            content.setTypeface(null, Typeface.BOLD_ITALIC);

            holder.container.addView(factView);
        }
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    static class PageViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;

        public PageViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.factContainer);
        }
    }
}
