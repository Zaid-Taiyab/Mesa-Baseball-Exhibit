package com.example.mesabaseballexhibit;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlayerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        // Retrieve the player name passed from MainActivity
        String playerName = getIntent().getStringExtra("playerName");

        TextView playerNameTextView = findViewById(R.id.playerNameTextView);
        playerNameTextView.setText(playerName);

        // Set a placeholder image for now
        ImageView playerImageView = findViewById(R.id.playerImageView);
        playerImageView.setImageResource(R.drawable.img); // Use a valid placeholder image

        // Set placeholder stats for now
        TextView playerStatsTextView = findViewById(R.id.playerStatsTextView);
        playerStatsTextView.setText("Player Stats:\n- Example stat 1\n- Example stat 2");
    }
}
