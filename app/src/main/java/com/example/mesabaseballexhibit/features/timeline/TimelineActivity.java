package com.example.mesabaseballexhibit.features.timeline;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mesabaseballexhibit.MainActivity;
import com.example.mesabaseballexhibit.R;
import com.example.mesabaseballexhibit.WelcomeActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout indicatorsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Root layout
        FrameLayout root = new FrameLayout(this);
        root.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));
        root.setBackgroundColor(0xFF800000); // maroon background

        // Main vertical content
        LinearLayout mainContent = new LinearLayout(this);
        mainContent.setOrientation(LinearLayout.VERTICAL);
        mainContent.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));

        // ViewPager2
        viewPager = new ViewPager2(this);
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1
        ));
        mainContent.addView(viewPager);

        // Indicators container
        indicatorsContainer = new LinearLayout(this);
        indicatorsContainer.setOrientation(LinearLayout.HORIZONTAL);
        indicatorsContainer.setGravity(Gravity.CENTER);
        indicatorsContainer.setPadding(0, 8, 0, 8);
        mainContent.addView(indicatorsContainer);

        // Add main content to root
        root.addView(mainContent);

        // Exit button
        MaterialButton btnExit = new MaterialButton(this);
        FrameLayout.LayoutParams exitParams = new FrameLayout.LayoutParams(
                dpToPx(this, 85),
                dpToPx(this, 75)
        );
        exitParams.gravity = Gravity.TOP | Gravity.END;
        exitParams.setMargins(dpToPx(this, 16), dpToPx(this, 16), dpToPx(this, 16), 0);
        btnExit.setLayoutParams(exitParams);
        btnExit.setText("Exit");
        btnExit.setTextSize(20);
        btnExit.setTextColor(0xFF000000); // same as exit button text
        btnExit.setBackgroundColor(0xFFFFD700); // gold
        btnExit.setCornerRadius(dpToPx(this, 24));
        btnExit.setOnClickListener(v -> {
            startActivity(new Intent(TimelineActivity.this, MainActivity.class));
            finish();
        });
        root.addView(btnExit);

        // Set the content view
        setContentView(root);

        // Timeline data
        List<TimelineItem> items = new ArrayList<>();
        items.add(new TimelineItem("1929", "The first spring training game was played in Arizona, when the Detroit Tigers hosted the Pittsburgh Pirates at Phoenix.", R.drawable.timeline_background));
        items.add(new TimelineItem("1947", "Horace Stoneham of the Giants and Bill Veeck of the Indians were instrumental in bringing a spring training game to Arizona.", R.drawable.timeline2));
        items.add(new TimelineItem("1952", "The Chicago Cubs became Arizona’s third Cactus League team when they moved from their spring training home on Catalina Island to Mesa’s Rendezvous Park.", R.drawable.teams1));
        items.add(new TimelineItem("1998", "Cactus League membership grows to 10 teams as the expansion Arizona Diamondbacks and Chicago White Sox join the league.", R.drawable.teams4));
        items.add(new TimelineItem("2008", "The Cactus League can now boast it has half of all Major League Baseball teams training in Arizona as the league membership reaches 15 teams.", R.drawable.teams3));

        // Adapter
        viewPager.setAdapter(new TimelineAdapter(items, this));

        setupIndicators(items.size());
        setCurrentIndicator(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                setCurrentIndicator(position);
            }
        });
    }

    // Timeline Item Model
    private static class TimelineItem {
        String year;
        String description;
        int imageResId;

        TimelineItem(String year, String description, int imageResId) {
            this.year = year;
            this.description = description;
            this.imageResId = imageResId;
        }
    }

    // Adapter
    private static class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {
        private final List<TimelineItem> items;
        private final Context context;

        TimelineAdapter(List<TimelineItem> items, Context context) {
            this.items = items;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(Gravity.CENTER);
            layout.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));
            layout.setPadding(32, 32, 32, 32);

            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    dpToPx(context, 480)
            );
            imageParams.topMargin = dpToPx(context, 16);
            imageView.setLayoutParams(imageParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            layout.addView(imageView);

            LinearLayout textOverlay = new LinearLayout(context);
            textOverlay.setOrientation(LinearLayout.VERTICAL);
            textOverlay.setBackgroundColor(0xFF800000); // semi-transparent rose overlay

            textOverlay.setPadding(16, 16, 16, 16);
            textOverlay.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            TextView yearText = new TextView(context);
            yearText.setTextSize(48);
            yearText.setTypeface(Typeface.DEFAULT_BOLD);
            yearText.setTextColor(0xFFffd700); // match exit button text
            textOverlay.addView(yearText);

            TextView descriptionText = new TextView(context);
            descriptionText.setTextSize(26);
            descriptionText.setTextColor(0xFFffd700);
            textOverlay.addView(descriptionText);

            layout.addView(textOverlay);

            return new ViewHolder(layout, imageView, yearText, descriptionText);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            TimelineItem item = items.get(position);
            holder.imageView.setImageResource(item.imageResId);
            holder.yearText.setText(item.year);
            holder.descriptionText.setText(item.description);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView yearText;
            TextView descriptionText;

            ViewHolder(View itemView, ImageView imageView, TextView yearText, TextView descriptionText) {
                super(itemView);
                this.imageView = imageView;
                this.yearText = yearText;
                this.descriptionText = descriptionText;
            }
        }
    }

    private static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    private void setupIndicators(int count) {
        indicatorsContainer.removeAllViews();
        for (int i = 0; i < count; i++) {
            View dot = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40, 40);
            params.setMargins(8, 0, 8, 0);
            dot.setLayoutParams(params);
            dot.setBackground(ContextCompat.getDrawable(this, R.drawable.indicator_inactive));
            indicatorsContainer.addView(dot);
        }
    }

    private void setCurrentIndicator(int position) {
        for (int i = 0; i < indicatorsContainer.getChildCount(); i++) {
            View dot = indicatorsContainer.getChildAt(i);
            dot.setBackground(ContextCompat.getDrawable(this,
                    i == position ? R.drawable.indicator_active : R.drawable.indicator_inactive));
        }
    }
}

