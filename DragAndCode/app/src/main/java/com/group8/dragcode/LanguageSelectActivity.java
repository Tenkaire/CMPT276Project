package com.group8.dragcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LanguageSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);
    }

    public void onJavaClick(View v)
    {
        startDifficultyScreen("java");
    }

    public void onCppClick(View v)
    {
        startDifficultyScreen("c++");
    }

    public void onPythonClick(View v)
    {
        startDifficultyScreen("python");
    }

    private void startDifficultyScreen(String language) {
        Intent intent = new Intent(this, DifficultySelectActivity.class);
        intent.putExtra("LANGUAGE", language);
        this.startActivity(intent);
    }
}
