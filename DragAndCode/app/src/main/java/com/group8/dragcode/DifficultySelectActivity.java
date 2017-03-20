package com.group8.dragcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DifficultySelectActivity extends AppCompatActivity {

    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_select);

        Intent intent = this.getIntent();
        this.language = intent.getStringExtra("LANGUAGE");
    }

    public void onEasyClick(View v)
    {
        startQuestionScreen("easy");
    }

    public void onMediumClick(View v)
    {
        startQuestionScreen("medium");
    }

    public void onHardClick(View v)
    {
        startQuestionScreen("hard");
    }

    private void startQuestionScreen(String difficulty) {
        //TODO question select screen intent implemented here
    }
}
