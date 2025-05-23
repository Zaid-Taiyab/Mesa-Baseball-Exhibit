package com.example.mesabaseballexhibit;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private ImageView batImage;
    private ImageView ballImage;
    private ImageView bagImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        batImage = findViewById(R.id.batImage);
        ballImage = findViewById(R.id.ballImage);
        bagImage = findViewById(R.id.bagImage);

        batImage.setOnClickListener(v -> {
            playDropAnimation();
        });
    }

    private void playDropAnimation() {
        float dropYBat = bagImage.getY() - batImage.getY();
        float dropYBall = bagImage.getY() - ballImage.getY();

        ObjectAnimator batDrop = ObjectAnimator.ofFloat(batImage, "translationY", 0f, dropYBat);
        batDrop.setDuration(700);
        batDrop.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator ballDrop = ObjectAnimator.ofFloat(ballImage, "translationY", 0f, dropYBall);
        ballDrop.setDuration(700);
        ballDrop.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator batFade = ObjectAnimator.ofFloat(batImage, "alpha", 1f, 0f);
        batFade.setDuration(300);
        batFade.setStartDelay(600);

        ObjectAnimator ballFade = ObjectAnimator.ofFloat(ballImage, "alpha", 1f, 0f);
        ballFade.setDuration(300);
        ballFade.setStartDelay(600);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(batDrop, ballDrop, batFade, ballFade);
        animatorSet.start();

        animatorSet.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

