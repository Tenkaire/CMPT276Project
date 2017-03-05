package com.g8.group8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends Activity {
	
	Button Info_btn, Shop_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		onClickButtonListenerForInfo();
		onClickButtonListenerForShop();
	}
	
	 public void onClickButtonListenerForInfo(){
	        Info_btn = (Button)findViewById(R.id.help_info);
	        Info_btn.setOnClickListener(
	             new View.OnClickListener() {

	             @Override
	             public void onClick(View v) {
	                  // TODO Auto-generated method stub
	                  Intent intent = new Intent(HelpActivity.this,InformationActivity.class);
	                  startActivity(intent);
	             }
	        });
	   }
	 
	 public void onClickButtonListenerForShop(){
	        Shop_btn = (Button)findViewById(R.id.help_shop);
	        Shop_btn.setOnClickListener(
	             new View.OnClickListener() {

	             @Override
	             public void onClick(View v) {
	                  // TODO Auto-generated method stub
	                  Intent intent = new Intent(HelpActivity.this,ShopActivity.class);
	                  startActivity(intent);
	             }
	        });
	   }


}
