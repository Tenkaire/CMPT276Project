package com.g8.group8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button easy_btn, medium_btn, hard_btn, help_btn,enter_btn;
	Spinner language_spinner, level_spinner;
	int LaCounter=0, LeCounter=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		onClickButtonListenerForEasy();
//		onClickButtonListenerForMedium();
//		onClickButtonListenerForHard();
		onClickButtonListenerForHelp();
		onSelectSpinnerListenerForLanguage();
	}
	
	public void onClickButtonListenerForHelp(){
        help_btn = (Button)findViewById(R.id.main_help);
        help_btn.setOnClickListener(
             new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                  // TODO Auto-generated method stub
                  Intent intent = new Intent(MainActivity.this,HelpActivity.class);
                  startActivity(intent);
             }
        });
   }
	
    public void onClickButtonListenerForEnter(){
        enter_btn = (Button)findViewById(R.id.main_enter);
        enter_btn.setOnClickListener(
             new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                  // TODO Auto-generated method stub
                  Intent intent = new Intent(MainActivity.this,QuizActivity.class);
                  startActivity(intent);
             }
        });
   }
    
//    public void onClickButtonListenerForMedium(){
//        medium_btn = (Button)findViewById(R.id.main_medium);
//        medium_btn.setOnClickListener(
//             new View.OnClickListener() {
//
//             @Override
//             public void onClick(View v) {
//                  // TODO Auto-generated method stub
//                  Intent intent = new Intent(MainActivity.this,QuizActivity.class);
//                  startActivity(intent);
//             }
//        });
//   }
    
//    public void onClickButtonListenerForHard(){
//        hard_btn = (Button)findViewById(R.id.main_hard);
//        hard_btn.setOnClickListener(
//             new View.OnClickListener() {
//
//             @Override
//             public void onClick(View v) {
//                  // TODO Auto-generated method stub
//                  Intent intent = new Intent(MainActivity.this,QuizActivity.class);
//                  startActivity(intent);
//             }
//        });
//   }
    
    public void onSelectSpinnerListenerForLanguage(){
        language_spinner = (Spinner) findViewById(R.id.language_spinner);
        language_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				LaCounter = position;
				switch(position){				
					case 0:
						onClickButtonListenerForEnter();
						break;
					case 1:
						onClickButtonListenerForEnter();
						break;
					case 2:
						onClickButtonListenerForEnter();
						break;
					case 3:
						onClickButtonListenerForEnter();
						break;
					case 4:
						onClickButtonListenerForEnter();
						break;
					default:
						break;
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "Please select one language.", Toast.LENGTH_LONG).show();			
			}
		});
   }


	
}
