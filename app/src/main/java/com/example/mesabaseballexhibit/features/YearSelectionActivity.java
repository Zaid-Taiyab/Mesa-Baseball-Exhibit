package com.example.mesabaseballexhibit.features;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mesabaseballexhibit.R;

public class YearSelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_selection);

        int[] buttonIds = {
                R.id.btn2014, R.id.btn2016, R.id.btn2017, R.id.btn2018, R.id.btn2019, R.id.btn2020
        };

        for (int id : buttonIds) {
            Button yearBtn = findViewById(id);
            yearBtn.setOnClickListener(v -> {
                String year = yearBtn.getText().toString();
                Intent intent = new Intent(this, HallOfFameActivity.class);
                intent.putExtra("YEAR", year);
                startActivity(intent);
            });
        }
    }
}

