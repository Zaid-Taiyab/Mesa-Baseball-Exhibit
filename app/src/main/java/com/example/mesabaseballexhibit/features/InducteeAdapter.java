package com.example.mesabaseballexhibit.features;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mesabaseballexhibit.R;
import com.example.mesabaseballexhibit.features.Inductee;

import java.util.List;

public class InducteeAdapter extends RecyclerView.Adapter<InducteeAdapter.ViewHolder> {

    private List<Inductee> inducteeList;

    public InducteeAdapter(List<Inductee> inducteeList) {
        this.inducteeList = inducteeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inductee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Inductee inductee = inducteeList.get(position);
        holder.image.setImageResource(inductee.imageResId);
        holder.name.setText(inductee.name);
        holder.title.setText(inductee.title);
        holder.bio.setText(inductee.bio);
    }

    @Override
    public int getItemCount() {
        return inducteeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, title, bio;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.inducteeImage);
            name = itemView.findViewById(R.id.inducteeName);
            title = itemView.findViewById(R.id.inducteeTitle);
            bio = itemView.findViewById(R.id.inducteeBio);
        }
    }
}

