package com.group8.dragcode;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.group8.dragcode.qclasses.Question;

public class QuestionSelectionActivity extends AppCompatActivity {
    QuestionSet questionSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_selection_layout);

        this.questionSet = getIntent().getParcelableExtra("QSET");

        GridView gridView = (GridView) findViewById(R.id.questionSelection);
        gridView.setAdapter(new QuestionButtonAdapter(this, this.questionSet));

    }

    public void returnToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToQuestion(View v){
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("QKEY", this.questionSet.getQuestionKeyForIndex(v.getId()));
        intent.putExtra("QSET", this.questionSet);
        startActivity(intent);
    }

}

