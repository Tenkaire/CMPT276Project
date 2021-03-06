package com.group8.dragcode;

import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.group8.dragcode.qclasses.Question;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void questionSelection(View view) {
        Intent intent = new Intent(this, QuestionSelectionActivity.class);
        startActivity(intent);
    }

    public void onPlayClick(View v) {
        Intent intent = new Intent(this, LanguageSelectActivity.class);
        this.startActivity(intent);
    }

    public void onStatsClick(View v) {
        //TODO start stats activity
        Intent intent = new Intent(this, StatsActivity.class);
        this.startActivity(intent);
    }

    public void onClickQuestion(View v)
    {
        // In reality, this will be the key to the question being clicked on
        String qkey = "TEST";

        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("QKEY", qkey);
        startActivity(intent);
    }
}
