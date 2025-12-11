package com.example.mesabaseballexhibit;

import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mesabaseballexhibit.features.HistoricalHighlightsActivity;
import com.example.mesabaseballexhibit.features.players.PlayerActivity;
import com.example.mesabaseballexhibit.features.TeamListActivity;
import com.example.mesabaseballexhibit.features.timeline.TimelineActivity;
import com.example.mesabaseballexhibit.features.trivia.SelectDifficultyActivity;
import com.example.mesabaseballexhibit.features.HallOfFameActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private CardView triviaCard, playersCard, teamsCard, highlightsCard, timelineCard, hallOfFameCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find cards by ID
        triviaCard = findViewById(R.id.triviaCard);
        playersCard = findViewById(R.id.playersCard);
        teamsCard = findViewById(R.id.teamsCard);
        highlightsCard = findViewById(R.id.highlightsCard);
        timelineCard = findViewById(R.id.timelineCard);
        hallOfFameCard = findViewById(R.id.hallOfFameCard);

        // Set listeners for each card
        triviaCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SelectDifficultyActivity.class)));
        playersCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PlayerActivity.class)));
        teamsCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TeamListActivity.class)));
        highlightsCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HistoricalHighlightsActivity.class)));
        timelineCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TimelineActivity.class)));
        hallOfFameCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HallOfFameActivity.class)));

        // Exit button
        MaterialButton btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish(); // Optional: closes this activity
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        restartAnimator(triviaCard, R.animator.button_hover_scale);
        restartAnimator(playersCard, R.animator.button_hover_scale);
        restartAnimator(teamsCard, R.animator.button_hover_scale);
        restartAnimator(highlightsCard, R.animator.button_hover_scale);
        restartAnimator(timelineCard, R.animator.button_hover_scale);
        restartAnimator(hallOfFameCard, R.animator.button_hover_scale);
    }

    private void restartAnimator(CardView view, int animatorRes) {
        view.setStateListAnimator(null); // clear old animator
        view.setStateListAnimator(AnimatorInflater.loadStateListAnimator(this, animatorRes)); // reload
    }
}
