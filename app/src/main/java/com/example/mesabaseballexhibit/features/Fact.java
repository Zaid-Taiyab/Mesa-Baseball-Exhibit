package com.example.mesabaseballexhibit.features;

public class Fact {
    private final String heading;
    private final String content;

    public Fact(String heading, String content) {
        this.heading = heading;
        this.content = content;
    }

    public String getHeading() {
        return heading;
    }

    public String getContent() {
        return content;
    }
}
