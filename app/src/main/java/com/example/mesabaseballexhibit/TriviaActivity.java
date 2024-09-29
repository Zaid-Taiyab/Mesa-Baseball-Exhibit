package com.example.mesabaseballexhibit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class TriviaActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answerOptionsGroup;
    private Button submitAnswerButton;

    private List<TriviaQuestion> triviaQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        questionTextView = findViewById(R.id.questionTextView);
        answerOptionsGroup = findViewById(R.id.answerOptionsGroup);
        submitAnswerButton = findViewById(R.id.submitAnswerButton);

        // Initialize trivia questions
        triviaQuestions = getTriviaQuestions();

        // Load the first question
        loadQuestion(currentQuestionIndex);

        // Set up submit button logic
        submitAnswerButton.setOnClickListener(v -> checkAnswer());
    }

    private List<TriviaQuestion> getTriviaQuestions() {
        List<TriviaQuestion> questions = new ArrayList<>();

        questions.add(new TriviaQuestion(
                "Who was the MVP of the 2017 World Series?",
                new String[]{"Jose Altuve", "George Springer", "Carlos Correa", "Justin Verlander"},
                1
        ));

        questions.add(new TriviaQuestion(
                "Which Arizona team won the 2001 World Series?",
                new String[]{"Arizona Diamondbacks", "Phoenix Suns", "Arizona Cardinals", "Phoenix Coyotes"},
                0
        ));

        questions.add(new TriviaQuestion(
                "Which Arizona baseball player hit 602 career home runs?",
                new String[]{"Paul Goldschmidt", "Luis Gonzalez", "Reggie Jackson", "Barry Bonds"},
                1
        ));

        // Add more questions as needed

        return questions;
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
            // All questions answered, show final score
            Toast.makeText(this, "Quiz Completed! Your Score: " + score + "/" + triviaQuestions.size(), Toast.LENGTH_LONG).show();
            finish(); // End the activity, or you could restart the quiz
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
