package com.example.mesabaseballexhibit.features.players;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mesabaseballexhibit.R;

public class PlayerDetailActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private LinearLayout indicatorsContainer;
    // Add your image resources here
    private int[] images = {
            R.drawable.players1,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        // Retrieve the player name passed from MainActivity
        String playerName = getIntent().getStringExtra("playerName");

        TextView playerNameTextView = findViewById(R.id.playerNameTextView);
        playerNameTextView.setText(playerName);

        // Initialize ViewPager2 and indicators
        viewPager = findViewById(R.id.viewPager);
        indicatorsContainer = findViewById(R.id.indicatorsContainer);

        // Set up the image slider
        setupImageSlider();
        setupIndicators();
        setCurrentIndicator(0);

        // Set placeholder stats for now
        TextView playerStatsTextView = findViewById(R.id.playerStatsTextView);
        playerStatsTextView.setText("Player Stats:\n- Example stat 1\n- Example stat 2");
    }

    private void setupImageSlider() {
        PlayerImageAdapter adapter = new PlayerImageAdapter(images);
        viewPager.setAdapter(adapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });
    }

    private void setupIndicators() {
        ImageView[] indicators = new ImageView[images.length];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 0, 8, 0);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.indicator_inactive));
            indicators[i].setLayoutParams(params);
            indicatorsContainer.addView(indicators[i]);
        }
    }

    private void setCurrentIndicator(int position) {
        int childCount = indicatorsContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) indicatorsContainer.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(this,
                        R.drawable.indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(this,
                        R.drawable.indicator_inactive));
            }
        }
    }
}
