package com.example.app.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    Button startQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startQuiz = (Button)findViewById(R.id.btnStartQuiz);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizStart();
            }
        });
    }
    private void quizStart(){
        Intent intent = new Intent(StartActivity.this, QuizActivity.class);
        startActivity(intent);
    }
}
