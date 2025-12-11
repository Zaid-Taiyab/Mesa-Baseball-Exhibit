package com.example.mesabaseballexhibit.features.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mesabaseballexhibit.R;
import com.google.android.material.button.MaterialButton;

public class SelectDifficultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_difficulty);

        Button easyButton = findViewById(R.id.easyButton);
        Button mediumButton = findViewById(R.id.mediumButton);
        Button hardButton = findViewById(R.id.hardButton);
        MaterialButton btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> onBackPressed());

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTriviaActivity("easy");
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTriviaActivity("medium");
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTriviaActivity("hard");
            }
        });
    }

    private void startTriviaActivity(String difficulty) {
        Intent intent = new Intent(SelectDifficultyActivity.this, TriviaActivity.class);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
    }

}

