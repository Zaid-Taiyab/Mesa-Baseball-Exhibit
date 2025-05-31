package com.example.mesabaseballexhibit.features.timeline;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mesabaseballexhibit.R;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout indicatorsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Root layout
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black));
        root.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));

        // ViewPager2
        viewPager = new ViewPager2(this);
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1
        ));
        root.addView(viewPager);

        // Indicators container
        indicatorsContainer = new LinearLayout(this);
        indicatorsContainer.setOrientation(LinearLayout.HORIZONTAL);
        indicatorsContainer.setGravity(Gravity.CENTER);
        indicatorsContainer.setPadding(0, 16, 0, 32);
        root.addView(indicatorsContainer);

        setContentView(root);

        // Timeline data
        List<TimelineItem> items = new ArrayList<>();
        items.add(new TimelineItem("1929", "The first spring training game was played in Arizona, when the Detroit Tigers hosted the Pittsburgh Pirates at Phoenix.", R.drawable.original_time));
        items.add(new TimelineItem("1947", "Horace Stoneham of the Giants and Bill Veeck of the Indians were instrumental in bringing a spring training game to Arizona.", R.drawable.horace_stoneham));
        items.add(new TimelineItem("1952", "The Chicago Cubs became Arizona’s third Cactus League team when they moved from their spring training home on Catalina Island to Mesa’s Rendezvous Park.", R.drawable.teams2));
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

            // Background image
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    0, 1
            ));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            layout.addView(imageView);

            // Overlay text container
            LinearLayout textOverlay = new LinearLayout(context);
            textOverlay.setOrientation(LinearLayout.VERTICAL);
            textOverlay.setBackgroundColor(0xAA000000); // semi-transparent black
            textOverlay.setPadding(24, 24, 24, 24);
            textOverlay.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            // Year text
            TextView yearText = new TextView(context);
            yearText.setTextSize(48);
            yearText.setTypeface(Typeface.DEFAULT_BOLD);
            yearText.setTextColor(0xFFFFFFFF);
            textOverlay.addView(yearText);

            // Description text
            TextView descriptionText = new TextView(context);
            descriptionText.setTextSize(26);
            descriptionText.setTextColor(0xFFFFFFFF);
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


