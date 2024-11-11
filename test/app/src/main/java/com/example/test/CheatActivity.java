package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER = "vt-le.quiz.answer";
    private static final String EXTRA_ANSWER_RESULT = "vt-le.quiz.answer_result";

    private boolean mAnswer;
    private TextView mTextViewAnswer;
    private Button btnShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswer = getIntent().getBooleanExtra(EXTRA_ANSWER, false);
        mTextViewAnswer = findViewById(R.id.m_text_view_answer);
        btnShowAnswer = findViewById(R.id.show_answer);

        btnShowAnswer.setOnClickListener(view -> {
            if(mAnswer) {
                mTextViewAnswer.setText("True");
            } else {
                mTextViewAnswer.setText("False");
            }
            setAnswerResult(true);
        });

    }

    private void setAnswerResult(boolean isAnwserShow ){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_RESULT, isAnwserShow);
        setResult(RESULT_OK, data);
    }

    public static boolean getAnswerShow(Intent intent){
        return intent.getBooleanExtra(EXTRA_ANSWER_RESULT, false);
    }

    public static Intent newIntent(Context pakageContext, boolean answer){
        Intent i = new Intent(pakageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER, answer);
        return i;
    }

}