package com.example.mesabaseballexhibit.features;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.mesabaseballexhibit.R;

public class HallOfFameActivity extends AppCompatActivity {

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);

        container = findViewById(R.id.container);

        // Example inductee data (can add more)
        addInducteeCard(
                R.drawable.dwight_patterson, // your drawable in res/drawable
                "Dwight “Pat” Patterson",
                "Mesa Rancher and Businessman",
                "One of the Cactus League's biggest civic boosters, credited with bringing the Chicago Cubs to Mesa in 1952 and then bringing them back from Scottsdale in 1979. First chairman of the Hohokams civic club, created to pursue a spring training baseball team in 1946."
        );

        addInducteeCard(
                R.drawable.horace_stoneham,
                "Horace Stoneham",
                "Owner, New York/San Francisco Giants, 1936 - 1976",
                "Credited with helping to start the Cactus League when he moved his team to Phoenix from Florida in 1947. Spent more than two decades visiting the Buckhorn Baths and Mineral Springs in Mesa leaving a rich trail of spring training history."
        );

        // Add more inductees similarly...
    }

    private void addInducteeCard(int imageResId, String name, String title, String bio) {
        // Create CardView
        CardView card = new CardView(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 24);
        card.setLayoutParams(cardParams);
        card.setRadius(16f);
        card.setCardElevation(8f);
        card.setUseCompatPadding(true);
        card.setContentPadding(24, 24, 24, 24);

        // Inner LinearLayout inside CardView
        LinearLayout cardLayout = new LinearLayout(this);
        cardLayout.setOrientation(LinearLayout.VERTICAL);
        cardLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // ImageView for inductee photo
        ImageView photo = new ImageView(this);
        photo.setImageResource(imageResId);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                500
        );
        photo.setLayoutParams(imageParams);
        photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
        photo.setAdjustViewBounds(true);
        photo.setClipToOutline(true);

        // TextView: Name
        TextView nameView = new TextView(this);
        nameView.setText(name);
        nameView.setTypeface(Typeface.DEFAULT_BOLD);
        nameView.setTextSize(20);
        nameView.setPadding(0, 16, 0, 4);

        // TextView: Title
        TextView titleView = new TextView(this);
        titleView.setText(title);
        titleView.setTypeface(Typeface.SERIF);
        titleView.setTextSize(16);
        titleView.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        titleView.setPadding(0, 0, 0, 12);

        // TextView: Bio
        TextView bioView = new TextView(this);
        bioView.setText(bio);
        bioView.setTextSize(14);
        bioView.setLineSpacing(1.2f, 1.2f);

        // Add views to cardLayout
        cardLayout.addView(photo);
        cardLayout.addView(nameView);
        cardLayout.addView(titleView);
        cardLayout.addView(bioView);

        // Add cardLayout to CardView
        card.addView(cardLayout);

        // Add CardView to container LinearLayout
        container.addView(card);
    }
}

