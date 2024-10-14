package com.example.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnTrue;
    private Button btnFalse;
    private Button btnNext;
    private Button btnPrevious;
    private TextView questions;

    private Questions[] questionList;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liên kết các nút và TextView từ giao diện
        btnTrue = findViewById(R.id.btn_true);
        btnFalse = findViewById(R.id.btn_false);
        btnNext = findViewById(R.id.btn_next);
        btnPrevious = findViewById(R.id.btn_previous);
        questions = findViewById(R.id.text_view_question);

        // Tạo danh sách các câu hỏi với đáp án
        questionList = new Questions[] {
                new Questions(R.string.question1, true),
                new Questions(R.string.question2, true),
                new Questions(R.string.question3, true),
                new Questions(R.string.question4, true),
                new Questions(R.string.question5, true),
                new Questions(R.string.question6, false),
                new Questions(R.string.question7, false),
                new Questions(R.string.question8, true),
                new Questions(R.string.question9, true),
                new Questions(R.string.question10, false),
        };

        // Hiển thị câu hỏi đầu tiên
        updateQuestion();

        // Xử lý khi người dùng chọn True
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        // Xử lý khi người dùng chọn False
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        // Xử lý nút Next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < questionList.length - 1) {
                    currentIndex++;
                    updateQuestion();
                } else {
                    Toast.makeText(MainActivity.this, "Đây là câu hỏi cuối cùng!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý nút Previous
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    currentIndex--;
                    updateQuestion();
                } else {
                    Toast.makeText(MainActivity.this, "Đây là câu hỏi đầu tiên!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void updateQuestion() {
        int question = questionList[currentIndex].getQuestion();
        questions.setText(question);
    }

    // Phương thức kiểm tra đáp án
    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer = questionList[currentIndex].isResult();
        if (userAnswer == correctAnswer) {
            Toast.makeText(MainActivity.this, R.string.notice_true, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, R.string.notice_false, Toast.LENGTH_SHORT).show();
        }
    }
}