package com.group8.dragcode;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.group8.dragcode.qclasses.Answer;
import com.group8.dragcode.qclasses.Blank;
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

        this.setTitle(question.getQuestionTitle());
    }

    public void onClickCheckAnswer(View view)
    {
        boolean answersCorrect = true;

        LinearLayout ll_code = (LinearLayout) findViewById(R.id.ll_code);

        for (int i = 0; i < ll_code.getChildCount(); i++)
        {
            View row = ll_code.getChildAt(i);

            if (row instanceof LinearLayout)
            {
                LinearLayout ll_row = (LinearLayout) row;
                for (int j = 0; j < ll_row.getChildCount(); j++)
                {
                    View rowChild = ll_row.getChildAt(j);

                    if (rowChild instanceof Blank)
                    {
                        Blank blank = (Blank) rowChild;

                        if (!blank.checkAnswer())
                        {
                            answersCorrect = false;
                        }
                    }
                }
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);
        String title;
        String text;

        if (answersCorrect)
        {
            title = getResources().getString(R.string.alert_correct);
            text = getResources().getString(R.string.alert_correct_text);
            builder.setTitle(title).setMessage(text).setNeutralButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                    // Set the question as finished in the database here

                    finish();
                }
            });
        }
        else
        {
            title = getResources().getString(R.string.alert_incorrect);
            text = getResources().getString(R.string.alert_incorrect_text);
            builder.setTitle(title).setMessage(text).setNeutralButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                }
            });
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}