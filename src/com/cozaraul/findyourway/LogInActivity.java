package com.cozaraul.findyourway;

import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LogInActivity extends ActionBarActivity {

	private EditText user, pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        
        Parse.enableLocalDatastore(this);     
        Parse.initialize(this, "wRAj7tfOwkfYgcarJmCQaaquk7KMwqH5Roq2233S", "5LMLZHDgVVW9FcbIjyui8ABJh2FeAVzzuciFvdhv");
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.log_in, menu);
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
    public void logIn(View view){
    	pw = (EditText)findViewById(R.id.textPassword);
    	user =  (EditText) findViewById(R.id.textUser);
    	
    	ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
    	query.whereExists("name");
    	query.findInBackground(new FindCallback<ParseObject>() {
    	    public void done(List<ParseObject> usernames, ParseException e) {
    	    	  if (e == null){ 
    	     for(int i=0;i<usernames.size();i++){
    	    	 
    	    	
    	    	 if(user.getText().toString().equals( usernames.get(i).get("name")) && pw.getText().toString().equals(usernames.get(i).get("password")))
    	     	{
    	     			Intent intent = new Intent(LogInActivity.this,MainActivity.class);
    	     			startActivity(intent);		
    	     	}
    	  
    	    	 	    	
    	     }
    	     }//aa
    	    	  else 
    	    	    {
    	    	    	CharSequence text1 = e.getMessage();
    		     		int duration = Toast.LENGTH_SHORT;

    		     		Toast toast = Toast.makeText(LogInActivity.this, text1, duration);
    		     		toast.show();
    	    	    }
    	    }
    	   
    	});
    }
}
