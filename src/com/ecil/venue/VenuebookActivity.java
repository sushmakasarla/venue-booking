package com.ecil.venue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class VenuebookActivity extends Activity {

	CalendarView c;
	final Context context = this;
	String d,vname,d1,uemail;
	String item1="",item2="",ca="";
	Usersignup db;
	Spinner spinner1,spinner2;
	List<String> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venuebook);
		setTitle("venueBook");
		db=new Usersignup(this);
		Bundle bundle = getIntent().getExtras();
		vname = bundle.getString("vname");
		uemail=bundle.getString("uemail");
		java.util.Date date=new java.util.Date(); 
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        d1=sd.format(date);
		c = (CalendarView) findViewById(R.id.calendarView1);
		addItemsOnSpinner1();
		addItemsOnSpinner2();
		c.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
		{
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
            	int k=i1+1;
            	if(i2/10==0&&k/10==0)
            		d="0"+i2+"/0"+k+"/"+i;
            	else if(i2/10==0||k/10==0)
            	{
            		if(i2/10==0)
            			d="0"+i2+"/"+k+"/"+i;
            		if(k/10==0)
            			d=i2+"/0"+k+"/"+i;
            	}
            	int y=d1.compareTo(d);
            	if(y>=0)
            	{
            		Toast.makeText(getApplicationContext(), "less than sys date" , Toast.LENGTH_LONG).show();
            		return;
            	}
            	Cursor res=db.checkdate(d,vname);
            	if(res.getCount()==0)
            	{
            		Toast.makeText(getApplicationContext(), "no such date in table", Toast.LENGTH_LONG).show();
            		return;
            	}
                	
            Toast.makeText(getApplicationContext(), d , Toast.LENGTH_LONG).show();
            ca=item1.substring(0,1)+item2.substring(0,1);
            Toast.makeText(getApplicationContext(),ca,Toast.LENGTH_SHORT).show();      
            Cursor res2=db.getavaldate(vname,ca,d);
            if(res2.getCount()!=0)
            {
            	if(res2.moveToNext())
         		{
            		if(res2.getString(0)== null)
            		{
            			Toast.makeText(getApplicationContext(), "available",Toast.LENGTH_SHORT).show();      
            			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            			alertDialogBuilder.setMessage("Click yes to Book!").setCancelable(false)
        				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog,int id) {
        						// if this button is clicked, close
        						// veneu is reserved
        						boolean t=db.updatetable(vname,d,ca);
                    			if(t==true)
                    				Toast.makeText(getApplicationContext(), "updated",Toast.LENGTH_SHORT).show();     
                    			else
                    				Toast.makeText(getApplicationContext(), "not updated",Toast.LENGTH_SHORT).show();       			
                    			Bundle b1=new Bundle();
                    			Intent i1=new Intent(VenuebookActivity.this,UserdetailsActivity.class);
                    			b1.putString("session",ca);
                    			b1.putString("date",d);
                    			b1.putString("uemail",uemail);
                    			i1.putExtras(b1);
                    			startActivity(i1);
        					}
        				  })
        				.setNegativeButton("No",new DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog,int id) {
        						// if this button is clicked, just close
        						// the dialog box and do nothing
        						dialog.cancel();
        					}
        				});
            			AlertDialog alertDialog = alertDialogBuilder.create();

        				// show it
        				alertDialog.show();
        			
            		
            			
            	} 
            		else
            			Toast.makeText(getApplicationContext(), "not available",Toast.LENGTH_SHORT).show();      
         		}
            }
      }
      });

		
	}
	 
	 public void addItemsOnSpinner1() {

		 spinner1 = (Spinner) findViewById(R.id.spinner1);
			list = new ArrayList<String>();
			list.add("Family Event");
			list.add("Business Event");
			list.add("Social Event");
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner1.setAdapter(dataAdapter);
			//spinner1.setOnItemSelectedListener(this);
			spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() 
			{
				public void onItemSelected(AdapterView<?> parent, View v,int pos, long id)
				{
					item1=(String) parent.getItemAtPosition(pos);
			         Toast.makeText(getApplicationContext(),item1,Toast.LENGTH_SHORT).show();
			         // Do your stuff here for spinner1
			    }
			    public void onNothingSelected(AdapterView<?> parent)
			    {

			    }

			});
	}
	public void addItemsOnSpinner2() 
	{

			spinner2 = (Spinner) findViewById(R.id.spinner2);
			list = new ArrayList<String>();
			list.add("Morning Session");
			list.add("Evening Session");
			
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner2.setAdapter(dataAdapter);
			spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() 
			{
				public void onItemSelected(AdapterView<?> parent, View view,int pos, long id)
				{
			         item2=(String) parent.getItemAtPosition(pos);
			         Toast.makeText(getApplicationContext(),item2,Toast.LENGTH_SHORT).show();
			         // Do your stuff here for spinner1

			    }
				public void onNothingSelected(AdapterView<?> parent)
				{

		        }
			        

			}); 
		}
	
	       
	       

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
		                Intent intent=new Intent(VenuebookActivity.this,UserActivity.class);
		                startActivity(intent);
		                finish();
		        }
		        return true;
		

		 }
	
}


