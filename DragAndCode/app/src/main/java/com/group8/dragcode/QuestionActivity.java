package com.group8.dragcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.group8.dragcode.qclasses.Answer;
import com.group8.dragcode.qclasses.Question;
import com.group8.dragcode.qclasses.XMLReader;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class QuestionActivity extends AppCompatActivity
{

    private Question question;
    private String qKey;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        this.qKey = intent.getStringExtra("QKEY");

        XMLReader xml = new XMLReader(this);
        try
        {
            question = xml.getQuestion(qKey);
        } catch (IOException | XmlPullParserException e)
        {
            Log.e("QuestionActivity", "Failed to get question " + qKey + ". Error: " + e.getMessage());
        }

        LinearLayout tl_code = (LinearLayout) findViewById(R.id.ll_code);
        for (LinearLayout codeRow : question.getCodeRows())
        {
            tl_code.addView(codeRow);
        }

        LinearLayout ll_answers = (LinearLayout) findViewById(R.id.ll_answers);
        for (Answer answer : question.getCodeAnswers())
        {
            ll_answers.addView(answer);
        }
    }

    public void onMenuClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}