package com.example.mesabaseballexhibit.features;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mesabaseballexhibit.R;
import com.example.mesabaseballexhibit.features.InducteeAdapter;
import com.google.android.material.button.MaterialButton;

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

        // 🔽 Enable scroll bar and keep it visible
        recyclerView.setVerticalScrollBarEnabled(true);
        recyclerView.setScrollbarFadingEnabled(false);  // Keeps it visible

        // 🔽 Adapter and data
        List<com.example.mesabaseballexhibit.features.Inductee> inductees = InducteeData.getInducteesForYear(year);
        recyclerView.setAdapter(new InducteeAdapter(inductees));

        MaterialButton btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> onBackPressed());
        // 🔽 Scroll hint fade out
        TextView scrollHint = findViewById(R.id.scrollHintText);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && scrollHint.getAlpha() > 0f) {
                    scrollHint.animate().alpha(0f).setDuration(500).start();
                }
            }
        });
    }
}


