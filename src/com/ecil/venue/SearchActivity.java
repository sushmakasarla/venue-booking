package com.ecil.venue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SearchActivity extends Activity {
	Usersignup db;
	String message,uemail;
	ListView l;
	EditText t1;
	 ArrayList<String> list1;
	 ArrayAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setTitle("Venue Book");
		 Bundle b=getIntent().getExtras();
	     uemail=b.getString("uemail");
	     
		t1=(EditText)findViewById(R.id.search);
		db=new Usersignup(this);
		l = (ListView) findViewById(R.id.listView1);
		final ArrayList<String> list = new ArrayList<String>();
		 list1 = new ArrayList<String>();
		Cursor res=db.getVenueNames();
		if(res.getCount()!=0)
		{
			while (res.moveToNext())
			{
	            list.add("venuename:   "+res.getString(0)+"\n"+"location:   "+res.getString(1));
	            list1.add(res.getString(1));
			}
		}
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
		l.setAdapter(adapter);
		l.setOnItemClickListener(new OnItemClickListener()
		{
			  public void onItemClick(AdapterView<?> parent, View view,
	                     int position, long id) {
                  
	                   // ListView Clicked item index
	                   int itemPosition     = position;
	                   
	                   // ListView Clicked item value
	                   String  itemValue    = (String) l.getItemAtPosition(position);
	                   String firstPart = itemValue.substring(13, itemValue.indexOf('\n'));
	                   //System.out.println(firstPart);
	                    String item=list1.get(itemPosition);
	                   // String item1=itemValue
	                    // Show Alert 
	                    
	                    
	                    Bundle b1=new Bundle();
	                    Intent d=new Intent(SearchActivity.this,SampleActivity.class);
	                    b1.putString("uemail",uemail);
	                    b1.putString("message",firstPart);
	                   d.putExtras(b1);
	    		    	startActivity(d);
	                 
	                  }
	    
	             });
		t1.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				 SearchActivity.this.adapter.getFilter().filter(s); 
				
			}
			
		});
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		 MenuInflater inflater = getMenuInflater();
 	    inflater.inflate(R.menu.login_button, menu);

 	    return super.onCreateOptionsMenu(menu);
		
	}
	 public boolean onOptionsItemSelected(MenuItem item) {
	        // Take appropriate action for each action item click
	        switch (item.getItemId()) {
	            case R.id.logout:
	                Intent intent=new Intent(SearchActivity.this,UserActivity.class);
	                startActivity(intent);
	                finish();
	        }
	        return true;
	

	 }

	

}
