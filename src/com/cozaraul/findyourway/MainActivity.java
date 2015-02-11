package com.cozaraul.findyourway;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void toCarList(View view){
		
		EditText textPlecare = (EditText)findViewById(R.id.textPlecare);
		EditText textSosire = (EditText)findViewById(R.id.textSosire);
		EditText textData = (EditText)findViewById(R.id.textData);
		
		Intent intent = new Intent(this,CarActivity.class);
		intent.putExtra("textPlecare",textPlecare.getText().toString());
		intent.putExtra("textSosire",textSosire.getText().toString());
		intent.putExtra("textData",textData.getText().toString());
		startActivity(intent);
	}
	
	
}
