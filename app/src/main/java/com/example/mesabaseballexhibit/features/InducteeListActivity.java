package com.example.mesabaseballexhibit.features;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mesabaseballexhibit.R;
import com.example.mesabaseballexhibit.features.InducteeAdapter;

import java.util.List;

public class InducteeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inductee_list);

        int year = getIntent().getIntExtra("YEAR", 0);
        TextView title = findViewById(R.id.inducteeYearTitle);
        title.setText("Hall of Fame " + year);

        RecyclerView recyclerView = findViewById(R.id.inducteeRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<com.example.mesabaseballexhibit.features.Inductee> inductees = InducteeData.getInducteesForYear(year); // You define this
        recyclerView.setAdapter(new InducteeAdapter(inductees));
    }
}

