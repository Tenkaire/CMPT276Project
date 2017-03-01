package com.g8.group8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button easy_btn, medium_btn, hard_btn, help_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		onClickButtonListenerForEasy();
		onClickButtonListenerForMedium();
		onClickButtonListenerForHard();
		onClickButtonListenerForHelp();
	}
	
	public void onClickButtonListenerForHelp(){
        help_btn = (Button)findViewById(R.id.main_help);
        help_btn.setOnClickListener(
             new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this,HelpActivity.class);
                  startActivity(intent);
             }
        });
   }
	
    public void onClickButtonListenerForEasy(){
        easy_btn = (Button)findViewById(R.id.main_easy);
        easy_btn.setOnClickListener(
             new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this,LevelSelectActivity.class);
                  startActivity(intent);
             }
        });
   }
    
    public void onClickButtonListenerForMedium(){
        medium_btn = (Button)findViewById(R.id.main_medium);
        medium_btn.setOnClickListener(
             new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this,LevelSelectActivity.class);
                  startActivity(intent);
             }
        });
   }
    
    public void onClickButtonListenerForHard(){
        hard_btn = (Button)findViewById(R.id.main_hard);
        hard_btn.setOnClickListener(
             new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this,LevelSelectActivity.class);
                  startActivity(intent);
             }
        });
   }


	
}
