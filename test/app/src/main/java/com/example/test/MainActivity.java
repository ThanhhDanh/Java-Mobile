package com.example.test;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mButtonTrue;
    private Button mButtonFalse;
    private Button mButtonNext;
    private Button btnCheat;
    private TextView Question;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, true),
            new Question(R.string.question6, true),
            new Question(R.string.question7, true),
            new Question(R.string.question8, false)
    };

    private int currentQuestionIndex = 0;

    private boolean mIsCheater;

    private static final String KEY_INDEX = "index";

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, currentQuestionIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonTrue = findViewById(R.id.button_true);
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mButtonFalse = findViewById(R.id.button_false);
        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        Question = findViewById(R.id.question);
        mButtonNext = findViewById(R.id.button_next);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestionIndex++;
                if (currentQuestionIndex < mQuestionBank.length) {
                    updateQuestion();
                } else {
                    Question.setText("Đã hết câu hỏi");
                    mButtonNext.setEnabled(false);
                }
            }
        });

        btnCheat = findViewById(R.id.btn_cheat);
        btnCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, CheatActivity.class);
                boolean answer = mQuestionBank[currentQuestionIndex].getAnswer();
                Intent i = CheatActivity.newIntent(MainActivity.this, answer);
                startActivityResult.launch(i);
            }
        });

        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();
    }

    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null && result.getResultCode() == RESULT_OK) {
                        if (result.getData() != null)
                            mIsCheater = CheatActivity.getAnswerShow(result.getData());
                    }
                }
            }
    );

    private void updateQuestion() {
        Question.setText(mQuestionBank[currentQuestionIndex].getQuestion());
    }

    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer = mQuestionBank[currentQuestionIndex].getAnswer();
        int messageResID = 0;
        if (!mIsCheater) {
            if (userAnswer == correctAnswer) {
                messageResID = R.string.thongbao_true;
            } else {
                messageResID = R.string.thongbao_false;
            }

        } else {
            messageResID = R.string.toast_cheating;
        }
        Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show();
    }


}



