package com.group8.dragcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.group8.dragcode.qclasses.CodeBox;
import com.group8.dragcode.qclasses.Module;
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

        LinearLayout ll_modules = (LinearLayout) findViewById(R.id.ll_modules);

        for (Module m : question.getCodeModules())
        {
            ll_modules.addView(m);
        }

        CodeBox codeBox = (CodeBox) findViewById(R.id.et_codeBox);
        codeBox.setCurrentQuestion(question);

    }
}