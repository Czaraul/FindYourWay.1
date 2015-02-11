package com.cozaraul.findyourway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;



public class CarActivity extends ActionBarActivity {

	private List<Map<String, Object>> data;
	//private String textPlecare = getIntent().getStringExtra("textPlecare");
	//private String textSosire = getIntent().getStringExtra("textSosire");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car);
		
		TextView textP = (TextView)findViewById(R.id.textP);
		TextView textS = (TextView)findViewById(R.id.textS);
		
		textP.setText(getIntent().getStringExtra("textPlecare"));
		textS.setText(getIntent().getStringExtra("textSosire"));
		
		prepareData();
		SimpleAdapter adapter = new SimpleAdapter(this, data,R.layout.listview_car, new String[] { "1","2" },
			     new int[] { R.id.textItem1,R.id.textItem2});
		
		 ListView listview = (ListView)findViewById(R.id.listViewCar);
		 listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			  public void onItemClick(AdapterView parent, View v, int position, long id){
			        
			    } 
		 });
		 listview.setAdapter(adapter);
		 
		 
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.car, menu);
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
	
	private void prepareData(){
		
		data = new ArrayList<Map<String, Object>>();	

	    
	    ParseQuery<ParseObject> query = ParseQuery.getQuery("Curse");
    	query.whereEqualTo("plecare", getIntent().getStringExtra("textPlecare").toString());
    	query.whereEqualTo("sosire", getIntent().getStringExtra("textSosire").toString());
    	query.findInBackground(new FindCallback<ParseObject>() {
    	    public void done(List<ParseObject> curse, ParseException e)
    	    {
    	    	
    	    	Map<String, Object> item; 
    	    	
    	    	if(e==null){
    	    		for(int i = 0;i < curse.size() ; i++ ){
    	    			item = new HashMap <String, Object>();
    	    			item.put("1", curse.get(i).get("username").toString());
    	    			item.put("2", curse.get(i).get("date").toString());
    	    			data.add(item);  
    	    	
    	    		
    	    		}
    	    	}
    	    	else
    	    	{
    	    		CharSequence text1 = e.getMessage();
		     		int duration = Toast.LENGTH_SHORT;

		     		Toast toast = Toast.makeText(CarActivity.this, text1, duration);
		     		toast.show();
    	    	}
    	    }
    	});   
	}
}
