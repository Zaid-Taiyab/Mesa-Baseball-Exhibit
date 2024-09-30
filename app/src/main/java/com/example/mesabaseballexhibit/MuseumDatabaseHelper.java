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
        addQuestion(db, values, "Which team won the 2001 World Series?", "Arizona Diamondbacks", "Boston Red Sox", "Los Angeles Dodgers", "New York Yankees", 0, "easy");
        addQuestion(db, values, "Which player has the most career home runs?", "Barry Bonds", "Hank Aaron", "Babe Ruth", "Willie Mays", 0, "easy");
        addQuestion(db, values, "How many innings are in a standard baseball game?", "6", "7", "9", "10", 2, "easy");
        addQuestion(db, values, "Which team has won the most World Series titles?", "New York Yankees", "Boston Red Sox", "San Francisco Giants", "Chicago Cubs", 0, "easy");
        addQuestion(db, values, "Which player holds the record for most stolen bases in a single season?", "Lou Brock", "Rickey Henderson", "Ty Cobb", "Joe Morgan", 1, "easy");
        addQuestion(db, values, "Who was the first player to hit 60 home runs in a season?", "Babe Ruth", "Roger Maris", "Hank Aaron", "Willie Mays", 0, "easy");
        addQuestion(db, values, "What is the maximum number of outs in an inning?", "3", "4", "6", "9", 2, "easy");
        addQuestion(db, values, "Which position on the field is number 1?", "Pitcher", "Catcher", "Shortstop", "First baseman", 0, "easy");
        addQuestion(db, values, "Which player has won the most Gold Glove Awards?", "Brooks Robinson", "Greg Maddux", "Ozzie Smith", "Ivan Rodriguez", 1, "easy");

        // Medium Questions
        addQuestion(db, values, "Who was the first player to reach 3,000 hits?", "Ty Cobb", "Babe Ruth", "Pete Rose", "Hank Aaron", 0, "medium");
        addQuestion(db, values, "Which team did Jackie Robinson break the color barrier with?", "New York Yankees", "Boston Red Sox", "Brooklyn Dodgers", "Chicago Cubs", 2, "medium");
        addQuestion(db, values, "Which stadium is known as 'The House That Ruth Built'?", "Yankee Stadium", "Fenway Park", "Wrigley Field", "Dodger Stadium", 0, "medium");
        addQuestion(db, values, "Which player holds the record for the longest hitting streak?", "Derek Jeter", "Ty Cobb", "Joe DiMaggio", "Ted Williams", 2, "medium");
        addQuestion(db, values, "Which player has the most career RBIs?", "Hank Aaron", "Babe Ruth", "Alex Rodriguez", "Barry Bonds", 0, "medium");
        addQuestion(db, values, "Which pitcher holds the record for the most complete games?", "Cy Young", "Christy Mathewson", "Nolan Ryan", "Roger Clemens", 0, "medium");
        addQuestion(db, values, "Who was the first player to be inducted into the Baseball Hall of Fame?", "Babe Ruth", "Ty Cobb", "Honus Wagner", "Walter Johnson", 1, "medium");
        addQuestion(db, values, "What year did the Arizona Diamondbacks win their first World Series?", "1999", "2001", "2003", "2004", 1, "medium");
        addQuestion(db, values, "Which pitcher holds the record for most career strikeouts?", "Nolan Ryan", "Randy Johnson", "Roger Clemens", "Greg Maddux", 0, "medium");
        addQuestion(db, values, "Which team is known as the Cubs' biggest rival?", "White Sox", "Cardinals", "Brewers", "Reds", 1, "medium");

        // Hard Questions
        addQuestion(db, values, "Which pitcher holds the record for the most no-hitters?", "Sandy Koufax", "Nolan Ryan", "Cy Young", "Randy Johnson", 1, "hard");
        addQuestion(db, values, "Who was the youngest player to hit 500 home runs?", "Willie Mays", "Hank Aaron", "Alex Rodriguez", "Albert Pujols", 2, "hard");
        addQuestion(db, values, "Who was the first player to reach 400 home runs and 400 stolen bases?", "Willie Mays", "Barry Bonds", "Alex Rodriguez", "Ken Griffey Jr.", 1, "hard");
        addQuestion(db, values, "Which player holds the single-season record for home runs?", "Babe Ruth", "Barry Bonds", "Mark McGwire", "Sammy Sosa", 1, "hard");
        addQuestion(db, values, "Which team won the most games in a regular season?", "Chicago Cubs", "Seattle Mariners", "New York Yankees", "Boston Red Sox", 1, "hard");
        addQuestion(db, values, "Which player holds the record for most hits in a single season?", "Ichiro Suzuki", "Pete Rose", "Ty Cobb", "George Sisler", 0, "hard");
        addQuestion(db, values, "Which pitcher has the most career wins?", "Cy Young", "Walter Johnson", "Christy Mathewson", "Pete Alexander", 0, "hard");
        addQuestion(db, values, "Which team did Hank Aaron hit his 715th home run against?", "Los Angeles Dodgers", "New York Mets", "San Francisco Giants", "Cincinnati Reds", 2, "hard");
        addQuestion(db, values, "Who was the first player to win back-to-back MVP awards?", "Ernie Banks", "Babe Ruth", "Joe DiMaggio", "Mickey Mantle", 0, "hard");
        addQuestion(db, values, "Who was the first player to hit 4 home runs in a single game?", "Lou Gehrig", "Willie Mays", "Babe Ruth", "Mark Whiten", 0, "hard");
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
