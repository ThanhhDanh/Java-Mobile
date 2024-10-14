package com.example.quiz;

import android.widget.TextView;

public class Questions {
    private int question;
    private boolean result;

    public Questions(int question, boolean result) {
        this.question = question;
        this.result = result;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }
}
