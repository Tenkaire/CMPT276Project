package com.group8.dragcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.group8.dragcode.qclasses.Question;

public class QuestionSelectionActivity extends AppCompatActivity {
    QuestionSet questionSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_selection_layout);

        String[] questionKeys = new String[12];
        for (int i = 0; i < 12; i++){
            questionKeys[i] = "TEST";
        }
        QuestionSet testQuestionSet = new QuestionSet(questionKeys);
        this.questionSet = testQuestionSet;

        GridView gridView = (GridView) findViewById(R.id.questionSelection);
        gridView.setAdapter(new QuestionButtonAdapter(this, testQuestionSet));

    }

    public void returnToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToQuestion(View v){
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("QKEY", this.questionSet.getQuestionKeyForIndex(v.getId()));
        startActivity(intent);
    }
}
