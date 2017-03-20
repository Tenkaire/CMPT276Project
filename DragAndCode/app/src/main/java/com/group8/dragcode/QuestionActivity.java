package com.group8.dragcode;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.group8.dragcode.db.DatabaseHelper;
import com.group8.dragcode.db.StatsModel;
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
    private StatsModel statsModel;
    private SQLiteDatabase db;
    private DatabaseHelper dbH;

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

        dbH = new DatabaseHelper(this);
        db = dbH.getWritableDatabase();
        statsModel = new StatsModel(db);
        statsModel.insertAttempt(question);
    }

    @Override
    protected void onDestroy()
    {
        db.close();
        dbH.close();

        super.onDestroy();
    }

    public void onClickHelp(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);

        SpannableStringBuilder ssBuilder = new SpannableStringBuilder();

        SpannableString title = new SpannableString(question.getQuestionTitle() + ":");
        title.setSpan(new StyleSpan(Typeface.BOLD), 0, question.getQuestionTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString expectedOutput = new SpannableString(getString(R.string.alert_output));
        expectedOutput.setSpan(new StyleSpan(Typeface.BOLD), 0, expectedOutput.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ssBuilder.append(title);
        ssBuilder.append("\n" + question.getQuestionDescription() + "\n\n");
        ssBuilder.append(expectedOutput);
        ssBuilder.append("\n");

        for (String outputLine : question.getCodeOutputs())
        {
            SpannableString output = new SpannableString(outputLine);
            output.setSpan(new TypefaceSpan("monospace"), 0, outputLine.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssBuilder.append(output);
            ssBuilder.append("\n");
        }

        builder.setTitle(getString(R.string.alert_about))
                .setMessage(ssBuilder)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void onClickHint(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);

        SpannableStringBuilder ssBuilder = new SpannableStringBuilder();

        SpannableString hintText = new SpannableString(question.getHintText());
        hintText.setSpan(new StyleSpan(Typeface.ITALIC), 0, question.getHintText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ssBuilder.append(getString(R.string.alert_hint_text) + "\n");
        ssBuilder.append(hintText);

        builder.setTitle(getString(R.string.alert_hint))
                .setMessage(ssBuilder)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
            builder.setTitle(title)
                    .setMessage(text)
                    .setNegativeButton(getString(R.string.alert_menu), new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                            finish();
                        }
                    })
                    .setPositiveButton(getString(R.string.alert_next), new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                            //Go to next question
                        }
                    })
                    .setOnDismissListener(new DialogInterface.OnDismissListener()
                    {
                        @Override
                        public void onDismiss(DialogInterface dialog)
                        {
                            statsModel.markCompletion(question);
                        }
                    });
        }
        else
        {
            title = getResources().getString(R.string.alert_incorrect);
            text = getResources().getString(R.string.alert_incorrect_text);
            builder.setTitle(title).setMessage(text).setPositiveButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener()
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
