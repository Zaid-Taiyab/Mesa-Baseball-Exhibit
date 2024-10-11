package com.example.mesabaseballexhibit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Find the button and set an OnClickListener to navigate to the MainActivity
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            // Navigate to MainActivity
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            // Optionally, finish the WelcomeActivity so it can't be returned to
            finish();
        });
    }
}

