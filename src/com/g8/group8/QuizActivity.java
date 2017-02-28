package com.g8.group8;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
	
	
	TextView count, question, text1, text2, text3;
	private QuestionLibrary questionL = new QuestionLibrary();
	private int questionNum = 1;
	private String CorrectAnswer; 
	private Button previous, hint, skip, quit, save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		
		count = (TextView) findViewById(R.id.quiz_count);
		question = (TextView) findViewById(R.id.quiz_question);
		text1 = (TextView) findViewById(R.id.quiz_option);
		text2 = (TextView) findViewById(R.id.quiz_option2);
		text3 = (TextView) findViewById(R.id.quiz_option3);
		
		text1.setOnLongClickListener(LongClickListener);
		text2.setOnLongClickListener(LongClickListener);
		text3.setOnLongClickListener(LongClickListener);
		question.setOnDragListener(DragListener);
		
		onClickButtonListenerForHint();
		onClickButtonListenerForSkip();
		onClickButtonListenerForQuit();
		
	}
	
	View.OnLongClickListener LongClickListener = new View.OnLongClickListener(){

		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			ClipData data = ClipData.newPlainText("", "");
			View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
			v.startDrag(data, myShadowBuilder, v, 0);
			return true;
		}		
	};
	
	View.OnDragListener DragListener = new View.OnDragListener(){

		@Override
		public boolean onDrag(View v, DragEvent event) {
			// TODO Auto-generated method stub
			int dragEvent = event.getAction();
			switch(dragEvent){
			case DragEvent.ACTION_DRAG_ENTERED:
				final View view = (View) event.getLocalState();
				
				if(view.getId()== R.id.quiz_option){
					view.setVisibility(View.INVISIBLE);
					updateQuestions();
					Toast.makeText(QuizActivity.this, "Dragged", Toast.LENGTH_LONG).show();
				}
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				break;
			case DragEvent.ACTION_DROP:
				final View view1 = (View) event.getLocalState();
				if(view1.getId()== R.id.quiz_option){
					updateQuestions();
				}
				break;
			
			}
			return false;
		}
		
	};
	
	private void updateQuestions(){
		question.setText(questionL.getQuestion(questionNum));
		text1.setText(questionL.getOption1(questionNum));
		text2.setText(questionL.getOption2(questionNum));
		text3.setText(questionL.getOption3(questionNum));
		
		CorrectAnswer = questionL.getCorrect(questionNum);
		if(questionNum<4){
			questionNum++;
		}
		
	}
	
	public void onClickButtonListenerForQuit(){
        quit = (Button)findViewById(R.id.quiz_quit);
        quit.setOnClickListener(
             new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                  // TODO Auto-generated method stub
                  Intent intent = new Intent(QuizActivity.this,MainActivity.class);
                  startActivity(intent);
             }
        });
   }
	
	public void onClickButtonListenerForSkip(){
        skip = (Button)findViewById(R.id.quiz_skip);
        skip.setOnClickListener(
             new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                  // TODO Auto-generated method stub
            	 
            	 if(questionNum<4){
            		 updateQuestions();
            	 }else{
            		 Toast.makeText(QuizActivity.this, "No more question!", Toast.LENGTH_LONG).show();
            	 }
             }
        });
   }
	
	public void onClickButtonListenerForHint(){
        hint = (Button)findViewById(R.id.quiz_hint);
        hint.setOnClickListener(
             new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                  // TODO Auto-generated method stub
            	 Toast.makeText(QuizActivity.this, "Correct Option is "+ questionL.getCorrect(questionNum-1), Toast.LENGTH_LONG).show();
             }
        });
   }
	

}
