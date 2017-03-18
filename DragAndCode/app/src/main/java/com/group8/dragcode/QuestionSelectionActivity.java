package com.group8.dragcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

public class QuestionSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_selection_layout);

        GridView gridView = (GridView) findViewById(R.id.buttonGrid);
        gridView.setAdapter(new QuestionNumberAdapter(this));
    }

    public void returnToMainMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
