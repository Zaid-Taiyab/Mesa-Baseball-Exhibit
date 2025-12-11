package com.example.mesabaseballexhibit.features.trivia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mesabaseballexhibit.core.MuseumDatabaseHelper;
import com.example.mesabaseballexhibit.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class TriviaActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answerOptionsGroup;
    private Button submitAnswerButton;

    private List<TriviaQuestion> triviaQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int totalQuestions = 10;  // Number of questions per quiz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        MaterialButton btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> onBackPressed());

        questionTextView = findViewById(R.id.questionTextView);
        answerOptionsGroup = findViewById(R.id.answerOptionsGroup);
        submitAnswerButton = findViewById(R.id.submitAnswerButton);

        // Fetch the difficulty level passed from the previous activity
        String selectedDifficulty = getIntent().getStringExtra("difficulty");

        // Provide a default value if no difficulty was passed
        if (selectedDifficulty == null) {
            selectedDifficulty = "easy";  // Default to "easy"
        }

        // Fetch random trivia questions based on difficulty
        MuseumDatabaseHelper dbHelper = new MuseumDatabaseHelper(this);
        triviaQuestions = dbHelper.getRandomTriviaQuestions(selectedDifficulty, totalQuestions);

        // Load the first question
        loadQuestion(currentQuestionIndex);

        // Set up submit button logic
        submitAnswerButton.setOnClickListener(v -> checkAnswer());
    }

    private void loadQuestion(int questionIndex) {
        if (questionIndex < triviaQuestions.size()) {
            TriviaQuestion question = triviaQuestions.get(questionIndex);
            questionTextView.setText(question.getQuestion());

            // Set options
            RadioButton option1 = findViewById(R.id.option1RadioButton);
            RadioButton option2 = findViewById(R.id.option2RadioButton);
            RadioButton option3 = findViewById(R.id.option3RadioButton);
            RadioButton option4 = findViewById(R.id.option4RadioButton);

            option1.setText(question.getOptions()[0]);
            option2.setText(question.getOptions()[1]);
            option3.setText(question.getOptions()[2]);
            option4.setText(question.getOptions()[3]);

            // Clear previous selections
            answerOptionsGroup.clearCheck();
        } else {
            // Calculate the percentage based on the number of questions in the quiz
            double percentageCorrect = ((double) score / triviaQuestions.size()) * 100;

            // Display the score and percentage
            Toast.makeText(this, "Quiz Completed! You scored: " + score + "/" + triviaQuestions.size() +
                    " (" + percentageCorrect + "% correct)", Toast.LENGTH_LONG).show();

            finish();  // End the activity, or you could restart the quiz
        }
    }


    private void checkAnswer() {
        // Get the selected RadioButton
        int selectedOptionId = answerOptionsGroup.getCheckedRadioButtonId();

        if (selectedOptionId == -1) {
            // No option selected, prompt user
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        // Determine which option was selected
        int selectedAnswerIndex = answerOptionsGroup.indexOfChild(findViewById(selectedOptionId));
        TriviaQuestion currentQuestion = triviaQuestions.get(currentQuestionIndex);

        // Check if the selected answer is correct
        if (selectedAnswerIndex == currentQuestion.getCorrectAnswerIndex()) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
        }

        // Move to the next question
        currentQuestionIndex++;
        loadQuestion(currentQuestionIndex);
    }
}
