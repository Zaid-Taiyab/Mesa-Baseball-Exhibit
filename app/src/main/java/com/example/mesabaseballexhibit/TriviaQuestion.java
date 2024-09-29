package com.example.mesabaseballexhibit;

public class TriviaQuestion {
    private String question;
    private String[] options;
    private int correctAnswerIndex;

    public TriviaQuestion(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
