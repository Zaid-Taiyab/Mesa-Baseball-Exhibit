package com.example.mesabaseballexhibit.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mesabaseballexhibit.features.trivia.TriviaQuestion;

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

        // ---------------------------
        // Cactus League + Arizona History
        // ---------------------------

        addQuestion(db, values,
                "Which city hosted the very first Cactus League spring training game in 1947?",
                "Phoenix",
                "Glendale",
                "Tucson",
                "Peoria",
                2,
                "medium");

        addQuestion(db, values,
                "Which Hall of Famer and owner of the Cleveland Indians was instrumental in bringing spring training to Arizona?",
                "Horace Stoneham",
                "Del Webb",
                "Phil Wrigley",
                "Bill Veeck",
                3,
                "medium");

        addQuestion(db, values,
                "Before Hohokam Stadium, which ballpark was home to the Chicago Cubs during spring training?",
                "Sloan Park",
                "Phoenix Municipal Stadium",
                "Rendezvous Park",
                "Scottsdale Stadium",
                2,
                "medium");

        addQuestion(db, values,
                "Which Arizona Cactus League ballpark is the largest in terms of seating capacity?",
                "Sloan Park (15,000 seats)",
                "Scottsdale Stadium",
                "Hohokam Stadium",
                "Camelback Ranch",
                0,
                "medium");

        addQuestion(db, values,
                "Which famous Cubs broadcaster, who coined the phrase 'Holy Cow!', was a fixture of spring training in Mesa?",
                "Vin Scully",
                "Bob Uecker",
                "Harry Caray",
                "Pat Hughes",
                2,
                "easy");

        // ---------------------------
        // General Baseball Trivia
        // ---------------------------

        addQuestion(db, values,
                "Which team won the 2001 World Series?",
                "Arizona Diamondbacks",
                "Boston Red Sox",
                "Los Angeles Dodgers",
                "New York Yankees",
                0,
                "easy");

        addQuestion(db, values,
                "Which team has won the most World Series titles?",
                "New York Yankees",
                "Boston Red Sox",
                "San Francisco Giants",
                "Chicago Cubs",
                0,
                "easy");

        addQuestion(db, values,
                "Which player holds the modern day record for most stolen bases in a single season?",
                "Lou Brock",
                "Rickey Henderson",
                "Ty Cobb",
                "Joe Morgan",
                1,
                "easy");

        addQuestion(db, values,
                "Which pitcher holds the record for the most no-hitters?",
                "Sandy Koufax",
                "Nolan Ryan",
                "Cy Young",
                "Randy Johnson",
                1,
                "hard");

        addQuestion(db, values,
                "Who was the first player to reach 400 home runs and 400 stolen bases?",
                "Willie Mays",
                "Barry Bonds",
                "Alex Rodriguez",
                "Ken Griffey Jr.",
                1,
                "hard");

        addQuestion(db, values,
                "Which player holds the record for most hits in a single season?",
                "Ichiro Suzuki",
                "Pete Rose",
                "Ty Cobb",
                "George Sisler",
                0,
                "easy");

        addQuestion(db, values,
                "Which team did Hank Aaron hit his 715th home run against?",
                "Los Angeles Dodgers",
                "New York Mets",
                "San Francisco Giants",
                "Cincinnati Reds",
                0,
                "medium");

        addQuestion(db, values,
                "Which future Hall of Famer played spring training in Scottsdale with the San Francisco Giants before hitting his record 756th career home run?",
                "Barry Bonds",
                "Ken Griffey Jr.",
                "Ernie Banks",
                "Willie Mays",
                0,
                "medium");

        addQuestion(db, values,
                "How many Cy Young Awards did Randy Johnson win while pitching for the Arizona Diamondbacks?",
                "2",
                "3",
                "4",
                "5",
                2,
                "medium");

        addQuestion(db, values,
                "Before joining the Dodgers, Ohtani played six seasons in MLB with which team?",
                "Los Angeles Dodgers",
                "Los Angeles Angels",
                "Seattle Mariners",
                "San Diego Padres",
                1,
                "easy");

        addQuestion(db, values,
                "What was the first song ever played on a ballpark organ in 1941 at Wrigley Field?",
                "Take Me Out to the Ball Game",
                "Happy Birthday",
                "The Star-Spangled Banner",
                "Beer Barrel Polka",
                3,
                "medium");

        addQuestion(db, values,
                "Before settling on the name 'Diamondbacks', which of the following was a finalist?",
                "Arizona Scorpions",
                "Arizona Coyotes",
                "Arizona Roadrunners",
                "Arizona Jackrabbits",
                0,
                "medium");
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
