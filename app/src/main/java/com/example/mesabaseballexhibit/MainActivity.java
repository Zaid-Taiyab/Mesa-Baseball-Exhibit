package com.example.mesabaseballexhibit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find buttons by ID
        Button triviaButton = findViewById(R.id.triviaChallengeButton);
        Button playersButton = findViewById(R.id.iconicPlayersButton);
        Button teamsButton = findViewById(R.id.iconicTeamsButton);
        Button highlightsButton = findViewById(R.id.historicalHighlightsButton);
        Button timelineButton = findViewById(R.id.timelineButton);

        // Set listeners to navigate to respective activities
        timelineButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TimelineActivity.class)));
        triviaButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SelectDifficultyActivity.class))); // Update this
        playersButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PlayerListActivity.class)));
        teamsButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TeamListActivity.class)));
        highlightsButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HistoricalHighlightsActivity.class)));
    }
}

