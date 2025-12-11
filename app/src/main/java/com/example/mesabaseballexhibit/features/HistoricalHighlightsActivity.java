package com.example.mesabaseballexhibit.features;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mesabaseballexhibit.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class HistoricalHighlightsActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout indicatorsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);
        MaterialButton btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> finish());
        viewPager = findViewById(R.id.viewPager);
        indicatorsContainer = findViewById(R.id.indicatorsContainer);
        indicatorsContainer.setBackgroundColor(0x66000000);
        // 1️⃣ Add all facts
        List<Fact> allFacts = new ArrayList<>();
        allFacts.add(new Fact("Satchel Paige (1947)",
                "One of the first African American players to train in Arizona with the Cleveland Indians."));
        allFacts.add(new Fact("Orioles in Scottsdale (1956)",
                "Baltimore Orioles were among the first east-coast teams to train in the west."));
        allFacts.add(new Fact("Hohokam Park Crowd",
                "Mesa once hosted 12,000+ fans, largest single-game crowd in Cactus League history."));
        allFacts.add(new Fact("Save the Cactus League",
                "In the 1960s, boosters launched campaigns to keep teams in the desert."));
        allFacts.add(new Fact("Milwaukee Brewers Debut",
                "The Brewers held their first spring training in Sun City in 1970."));
        allFacts.add(new Fact("Fans and Lawn Chairs",
                "Early ballparks often had only basic bleachers; fans brought lawn chairs."));
        allFacts.add(new Fact("First Night Game (1960s)",
                "Cactus League experimented with night games under desert stars."));
        allFacts.add(new Fact("The Beatles & Baseball (1964)",
                "Giants trained in Arizona while Beatles played their first Arizona concert."));
        allFacts.add(new Fact("A’s & Mustangs (1970s)",
                "Oakland A’s shared Scottsdale field with a local Mustang car show."));
        allFacts.add(new Fact("Cubs Draw Media (1980s)",
                "Chicago media broadcasted daily from Mesa, making Cactus League a spring tradition."));
        allFacts.add(new Fact("Diamondbacks Debut (1998)",
                "Arizona Diamondbacks held their first spring training in Tucson before MLB debut."));
        allFacts.add(new Fact("Legends as Coaches",
                "Hall of Famers like Feller, McCovey, Williams often returned as guest instructors."));

        // 2️⃣ Split into 3 pages (~4 facts per page)
        List<FactPage> pages = new ArrayList<>();
        int pageSize = 5;
        for (int i = 0; i < allFacts.size(); i += pageSize) {
            pages.add(new FactPage(allFacts.subList(i, Math.min(i + pageSize, allFacts.size()))));
        }

        // 3️⃣ Set adapter
        FactPageAdapter adapter = new FactPageAdapter(pages);
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
        ImageView[] indicators = new ImageView[viewPager.getAdapter().getItemCount()];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(30, 15, 30, 15);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.indicator_inactive));
            indicators[i].setLayoutParams(params);
            indicatorsContainer.addView(indicators[i]);
        }
    }

    private void setCurrentIndicator(int position) {
        int childCount = indicatorsContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) indicatorsContainer.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.indicator_inactive));
            }
        }
    }
}
