package com.example.mesabaseballexhibit.features.players;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mesabaseballexhibit.R;

public class PlayerActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private LinearLayout indicatorsContainer;
    private int[] images = {
            R.drawable.players1,
            R.drawable.players2,
            R.drawable.players3,
            R.drawable.players4,
            R.drawable.players5,
            R.drawable.players6,
            R.drawable.players7,
            R.drawable.players8,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        viewPager = findViewById(R.id.viewPager);
        indicatorsContainer = findViewById(R.id.indicatorsContainer);

        PlayerImageAdapter adapter = new PlayerImageAdapter(images);
        viewPager.setAdapter(adapter);

        setupIndicators();
        setCurrentIndicator(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                setCurrentIndicator(position);
            }
        });
    }

    private void setupIndicators() {
        ImageView[] indicators = new ImageView[images.length];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(30, 50, 30, 50);

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