package com.example.mesabaseballexhibit.features;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mesabaseballexhibit.R;

import java.util.List;

public class InducteeAdapter extends RecyclerView.Adapter<InducteeAdapter.ViewHolder> {

    private final List<Inductee> inductees;

    public InducteeAdapter(List<Inductee> inductees) {
        this.inductees = inductees;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView name, title, bio;

        public ViewHolder(View view) {
            super(view);
            photo = view.findViewById(R.id.inducteeImage);
            name = view.findViewById(R.id.inducteeName);
            title = view.findViewById(R.id.inducteeTitle);
            bio = view.findViewById(R.id.inducteeBio);
        }
    }

    @NonNull
    @Override
    public InducteeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inductee_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inductee i = inductees.get(position);
        holder.photo.setImageResource(i.imageResId);
        holder.name.setText(i.name);
        holder.title.setText(i.title);
        holder.bio.setText(i.bio);
    }

    @Override
    public int getItemCount() {
        return inductees.size();
    }
}
