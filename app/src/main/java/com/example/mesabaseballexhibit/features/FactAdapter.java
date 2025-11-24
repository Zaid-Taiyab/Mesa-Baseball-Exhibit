package com.example.mesabaseballexhibit.features;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mesabaseballexhibit.R;
import java.util.List;

public class FactAdapter extends RecyclerView.Adapter<FactAdapter.FactViewHolder> {

    private final List<Fact> facts;

    public FactAdapter(List<Fact> facts) {
        this.facts = facts;
    }

    @NonNull
    @Override
    public FactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fact_card, parent, false);
        return new FactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FactViewHolder holder, int position) {
        Fact fact = facts.get(position);
        holder.heading.setText(fact.getHeading());
        holder.content.setText(fact.getContent());
    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    static class FactViewHolder extends RecyclerView.ViewHolder {
        TextView heading, content;

        public FactViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.factHeading);
            content = itemView.findViewById(R.id.factContent);
        }
    }
}
