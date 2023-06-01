package com.ecil.venue;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class UserdetailsActivity extends Activity {
	TextView t1,t2,t3,t4,t5;
	Usersignup db;
	String uemail,session,date;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userdetails);
		setTitle("venue Book");
		t1=(TextView)findViewById(R.id.textView1);
		t2=(TextView)findViewById(R.id.textView2);
		t3=(TextView)findViewById(R.id.textView3);
		t4=(TextView)findViewById(R.id.textView5);
		db=new Usersignup(this);
		Bundle b=getIntent().getExtras();
        uemail=b.getString("uemail");
        session=b.getString("session");
        date=b.getString("date");
        t4.setText("session:"+session+"\n"+"Date:"+date);
		Cursor res=db.getAllData(uemail);
		if(res.getCount()==0)
		{
			Toast.makeText(this,"No details available for "+uemail,Toast.LENGTH_SHORT).show();
			return;
		}
		while(res.moveToNext())
		{
		t1.setText("username:"+res.getString(0));
		t2.setText("Email:"+uemail);
		t3.setText("Mobileno:"+res.getString(3));
		}	
		
		
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
		                Intent intent=new Intent(this,UserActivity.class);
		                startActivity(intent);
		                finish();
		        }
		        return true;
		

		 }

}
