package com.g8.group8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;

public class LevelSelectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
        
        GridLayout grid = (GridLayout) this.findViewById(R.id.level_select_grid);
        
        //TODO Mock question count, real question list will be passed later
        int questionCount = 32;
        int columns = Math.min(4, questionCount);
        
        grid.setColumnCount(columns);
        grid.setRowCount(8);
        
        for (int i = 0; i < questionCount; i++) {
            int col = i % columns;
            int row = i / columns;
            
            Button button = new Button(this);
            button.setText(Integer.toString(i + 1));
            
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LevelSelectActivity.this, QuizActivity.class);
                    startActivity(intent);
                }
            });
            
            LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(row), GridLayout.spec(col));
            button.setLayoutParams(params);
            grid.addView(button);
        }
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

}
