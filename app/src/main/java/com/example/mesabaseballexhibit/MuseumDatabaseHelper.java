package com.example.mesabaseballexhibit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MuseumDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "museum.db";
    private static final int DATABASE_VERSION = 1;

    public MuseumDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the trivia table with difficulty levels
        db.execSQL("CREATE TABLE IF NOT EXISTS trivia (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "question TEXT, " +
                "option1 TEXT, " +
                "option2 TEXT, " +
                "option3 TEXT, " +
                "option4 TEXT, " +
                "correct_answer INTEGER, " +
                "difficulty TEXT)");

        // Populate the table with trivia questions
        seedDatabase(db);
    }

    private void seedDatabase(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Easy Questions
        addQuestion(db, values, "Who won the 2017 World Series MVP?", "Jose Altuve", "George Springer", "Carlos Correa", "Justin Verlander", 1, "easy");
        // Add other questions with the same method
        Log.d("DatabaseHelper", "Seeding completed.");
    }

    private void addQuestion(SQLiteDatabase db, ContentValues values, String question, String option1, String option2, String option3, String option4, int correctAnswer, String difficulty) {
        values.clear();
        values.put("question", question);
        values.put("option1", option1);
        values.put("option2", option2);
        values.put("option3", option3);
        values.put("option4", option4);
        values.put("correct_answer", correctAnswer);
        values.put("difficulty", difficulty);

        long result = db.insert("trivia", null, values);
        if (result != -1) {
            Log.d("DatabaseHelper", "Inserted question: " + question);
        } else {
            Log.e("DatabaseHelper", "Failed to insert question: " + question);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS trivia");
        onCreate(db);
    }

    public List<TriviaQuestion> getRandomTriviaQuestions(String difficulty, int limit) {
        List<TriviaQuestion> questions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Ensure the difficulty is not null before querying the database
        if (difficulty == null || difficulty.isEmpty()) {
            difficulty = "easy";  // Default value if difficulty is null or empty
        }

        String query = "SELECT * FROM trivia WHERE difficulty = ? ORDER BY RANDOM() LIMIT ?";
        Cursor cursor = db.rawQuery(query, new String[]{difficulty, String.valueOf(limit)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
                String option1 = cursor.getString(cursor.getColumnIndexOrThrow("option1"));
                String option2 = cursor.getString(cursor.getColumnIndexOrThrow("option2"));
                String option3 = cursor.getString(cursor.getColumnIndexOrThrow("option3"));
                String option4 = cursor.getString(cursor.getColumnIndexOrThrow("option4"));
                int correctAnswer = cursor.getInt(cursor.getColumnIndexOrThrow("correct_answer"));

                TriviaQuestion triviaQuestion = new TriviaQuestion(question, new String[]{option1, option2, option3, option4}, correctAnswer);
                questions.add(triviaQuestion);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return questions;
    }
}
