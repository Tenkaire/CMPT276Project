package com.group8.dragcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_to_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClickButtonListenerForInfo();
    }

    public void nextScreen(View view){
        Intent intent = new Intent(this, QuestionSelectionActivity.class);
        startActivity(intent);
    }

    public void onClickButtonListenerForInfo(){
        btn_to_info = (Button)findViewById(R.id.BUTTON_to_ANI);
        btn_to_info.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(MainActivity.this,NaviagationActivity.class);
                        startActivity(intent);
                    }
                });
    }
}
