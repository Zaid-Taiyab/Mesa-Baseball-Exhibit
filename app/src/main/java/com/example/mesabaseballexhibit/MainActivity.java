package com.example.mesabaseballexhibit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Sample data for the player list
    String[] players = {"Player 1", "Player 2", "Player 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListView playerListView = findViewById(R.id.playerListView);
        // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players);
        // playerListView.setAdapter(adapter);
        Button triviaChallengeButton = findViewById(R.id.triviaChallengeButton);
        Button iconicPlayersButton = findViewById(R.id.iconicPlayersButton);
        Button iconicTeamsButton = findViewById(R.id.iconicTeamsButton);
        Button historicalHighlightsButton = findViewById(R.id.historicalHighlightsButton);

        triviaChallengeButton.setOnClickListener(v -> {
            // Add your intent or action here
        });

        iconicPlayersButton.setOnClickListener(v -> {
            // Add your intent or action here
        });

        iconicTeamsButton.setOnClickListener(v -> {
            // Add your intent or action here
        });

        historicalHighlightsButton.setOnClickListener(v -> {
            // Add your intent or action here
        });
    }
}

