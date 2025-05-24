package com.example.mesabaseballexhibit.features;

import com.example.mesabaseballexhibit.R;
import java.util.ArrayList;
import java.util.List;

public class InducteeData {
    public static List<com.example.mesabaseballexhibit.features.Inductee> getInducteesForYear(int year) {
        List<com.example.mesabaseballexhibit.features.Inductee> list = new ArrayList<>();

        if (year == 2014) {
            list.add(new com.example.mesabaseballexhibit.features.Inductee(R.drawable.dwight_patterson,
                    "Dwight 'Pat' Patterson",
                    "Mesa Rancher and Businessman",
                    "Credited with bringing the Cubs to Mesa in 1952 and again in 1979..."));
            // Add more inductees here
        }
        if (year == 2016) {
            list.add(new com.example.mesabaseballexhibit.features.Inductee(R.drawable.dwight_patterson,
                    "Dwight 'Pat' Patterson",
                    "Mesa Rancher and Businessman",
                    "Credited with bringing the Cubs to Mesa in 1952 and again in 1979..."));
            // Add more inductees here
        }
        if (year == 2017) {
            list.add(new com.example.mesabaseballexhibit.features.Inductee(R.drawable.dwight_patterson,
                    "Dwight 'Pat' Patterson",
                    "Mesa Rancher and Businessman",
                    "Credited with bringing the Cubs to Mesa in 1952 and again in 1979..."));
            // Add more inductees here
        }
        if (year == 2018) {
            list.add(new com.example.mesabaseballexhibit.features.Inductee(R.drawable.dwight_patterson,
                    "Dwight 'Pat' Patterson",
                    "Mesa Rancher and Businessman",
                    "Credited with bringing the Cubs to Mesa in 1952 and again in 1979..."));
            // Add more inductees here
        }
        if (year == 2019) {
            list.add(new com.example.mesabaseballexhibit.features.Inductee(R.drawable.dwight_patterson,
                    "Dwight 'Pat' Patterson",
                    "Mesa Rancher and Businessman",
                    "Credited with bringing the Cubs to Mesa in 1952 and again in 1979..."));
            // Add more inductees here
        }
        if (year == 2020) {
            list.add(new com.example.mesabaseballexhibit.features.Inductee(R.drawable.dwight_patterson,
                    "Dwight 'Pat' Patterson",
                    "Mesa Rancher and Businessman",
                    "Credited with bringing the Cubs to Mesa in 1952 and again in 1979..."));
            // Add more inductees here
        }
        // Repeat for other years (2017, 2018, etc.)
        return list;
    }
}
