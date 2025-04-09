package com.example.mesabaseballexhibit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mesabaseballexhibit.features.HistoricalHighlightsActivity;
import com.example.mesabaseballexhibit.features.players.PlayerActivity;
import com.example.mesabaseballexhibit.features.TeamListActivity;
import com.example.mesabaseballexhibit.features.timeline.TimelineActivity;
import com.example.mesabaseballexhibit.features.trivia.SelectDifficultyActivity;
import com.example.mesabaseballexhibit.features.HallOfFameActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find cards by ID
        CardView triviaCard = findViewById(R.id.triviaCard);
        CardView playersCard = findViewById(R.id.playersCard);
        CardView teamsCard = findViewById(R.id.teamsCard);
        CardView highlightsCard = findViewById(R.id.highlightsCard);
        CardView timelineCard = findViewById(R.id.timelineCard);
        CardView hallOfFameCard = findViewById(R.id.hallOfFameCard);

        // Set listeners for each card
        triviaCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SelectDifficultyActivity.class)));
        playersCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PlayerActivity.class)));
        teamsCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TeamListActivity.class)));
        highlightsCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HistoricalHighlightsActivity.class)));
        timelineCard.setOnClickListener(v-> startActivity(new Intent(MainActivity.this, TimelineActivity.class)));
        hallOfFameCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HallOfFameActivity.class)));
    }
}

