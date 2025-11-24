package com.example.mesabaseballexhibit.features;


import java.util.List;

public class FactPage {
    private final List<Fact> facts;

    public FactPage(List<Fact> facts) {
        this.facts = facts;
    }

    public List<Fact> getFacts() {
        return facts;
    }
}

