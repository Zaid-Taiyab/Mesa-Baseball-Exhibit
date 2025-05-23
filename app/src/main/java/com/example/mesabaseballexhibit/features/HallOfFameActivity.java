package com.example.mesabaseballexhibit.features;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mesabaseballexhibit.R;

import java.util.ArrayList;
import java.util.List;

public class HallOfFameActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    InducteeAdapter adapter;
    List<Inductee> inductees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);

        recyclerView = findViewById(R.id.hofRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        inductees = new ArrayList<>();
        inductees.add(new Inductee(
                "Dwight “Pat” Patterson",
                "Mesa Rancher and Businessman",
                "One of the Cactus League's biggest civic boosters, he is credited with bringing the Chicago Cubs to Mesa \n" +
                        "in 1952 and then bringing them back to Mesa from Scottsdale in 1979. He was the first chairman of the \n" +
                        "storied Hohokams civic club, created to pursue a spring training baseball team in 1946 and was selected \n" +
                        "by Governor Rose Mofford to lead her task force to save spring training in the late 1980's.",
                R.drawable.players1
        ));

        // Add more inductees here...

        adapter = new InducteeAdapter(inductees);
        recyclerView.setAdapter(adapter);
    }
}
