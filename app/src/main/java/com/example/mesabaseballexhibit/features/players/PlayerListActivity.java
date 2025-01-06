package com.example.mesabaseballexhibit.features.players;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mesabaseballexhibit.R;

public class PlayerListActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        // Find your clickable layout using the new ID
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, PlayerDetailActivity.class);
        intent.putExtra("playerName", "Example Player"); // Add this line
        startActivity(intent);
    }
}