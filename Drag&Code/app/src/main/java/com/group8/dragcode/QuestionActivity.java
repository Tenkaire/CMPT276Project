package com.group8.dragcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuestionActivity extends AppCompatActivity {

    private Question question;

    public QuestionActivity(Question activityQuestion)
    {
        if (activityQuestion == null) activityQuestion = new Question();
        question = activityQuestion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }
}
