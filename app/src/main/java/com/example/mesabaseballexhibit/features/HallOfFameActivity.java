package com.example.mesabaseballexhibit.features;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mesabaseballexhibit.R;
import com.google.android.material.button.MaterialButton;

public class HallOfFameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);
        setButtonListener(R.id.btn2014, 2014);
        setButtonListener(R.id.btn2016, 2016);
        setButtonListener(R.id.btn2017, 2017);
        setButtonListener(R.id.btn2018, 2018);
        setButtonListener(R.id.btn2019, 2019);
        setButtonListener(R.id.btn2020, 2020);
        MaterialButton btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> onBackPressed());
    }

    private void setButtonListener(int id, int year) {
        Button btn = findViewById(id);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, InducteeListActivity.class);
            intent.putExtra("YEAR", year);
            startActivity(intent);
        });
    }
}



