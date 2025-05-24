package com.example.mesabaseballexhibit.features;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mesabaseballexhibit.R;

public class HallOfFameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);
        // No dynamic logic needed if all inductee cards are in XML
    }
}
