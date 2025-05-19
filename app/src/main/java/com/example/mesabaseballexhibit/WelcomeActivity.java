package com.example.mesabaseballexhibit;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
public class WelcomeActivity extends AppCompatActivity {
    private ImageView batImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        batImage = findViewById(R.id.batImage);
        batImage.setRotation(20);
        batImage.setOnClickListener(v -> {
            swingBat();
            batImage.postDelayed(() -> {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }, 600); // Wait for animation to finish
        });
    }
    private void swingBat() {
        batImage.post(() -> {
            int w = batImage.getWidth();
            int h = batImage.getHeight();
            batImage.setPivotX(w);     // right edge
            batImage.setPivotY(h);     // bottom edge
            batImage.setRotation(0f);  // reset rotation
            ObjectAnimator load = ObjectAnimator.ofFloat(batImage, "rotation", 0f, 40f);
            load.setDuration(200);
            load.setInterpolator(new AccelerateInterpolator());
            ObjectAnimator preSwing = ObjectAnimator.ofFloat(batImage, "rotation", 40f, 35f);
            preSwing.setDuration(80);
            preSwing.setInterpolator(new LinearInterpolator());
            ObjectAnimator whip = ObjectAnimator.ofFloat(batImage, "rotation", 35f, -25f);
            whip.setDuration(120);
            whip.setInterpolator(new DecelerateInterpolator());
            ObjectAnimator contactPause = ObjectAnimator.ofFloat(batImage, "rotation", -25f, -20f);
            contactPause.setDuration(60);
            contactPause.setInterpolator(new LinearInterpolator());
            ObjectAnimator followThrough = ObjectAnimator.ofFloat(batImage, "rotation", -20f, -60f);
            followThrough.setDuration(200);
            followThrough.setInterpolator(new DecelerateInterpolator());
            AnimatorSet swingSet = new AnimatorSet();
            swingSet.playSequentially(load, preSwing, whip, contactPause, followThrough);
            swingSet.start();
        });
    }
}

