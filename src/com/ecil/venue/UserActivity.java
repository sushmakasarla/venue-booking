package com.ecil.venue;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import android.widget.Button;
import android.widget.TextView;




public class UserActivity extends Activity {
	TextView t;
	EditText et1,et2;
	Button a;
	Usersignup db;
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		setTitle("User Login");
		//getActionBar().setHomeButtonEnabled(true);

		db=new Usersignup(this);
		et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
		addListenerOnButton();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
		return true;
	}
	 public void addListenerOnButton()
	    {
		 
	    	t=(TextView)findViewById(R.id.textView1);
	    
	        t.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent d=new Intent(UserActivity.this,UsersignActivity.class);
					startActivity(d);
					
				}
				});
	    
	 
	        a=(Button)findViewById(R.id.button1);
			 a.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v1) {
					// TODO Auto-generated method stub
					//et2.setText("hello");
					
					Cursor res=db.check(et1.getText().toString(),et2.getText().toString());
	                if(res.getCount()==1)
	                {
	                    Intent i1 = new Intent(UserActivity.this,SearchActivity.class);
	                    i1.putExtra("uemail",et1.getText().toString());
	                    startActivity(i1);
	                }
	                else
	                {
	                    Toast.makeText(UserActivity.this,"Either Email/Password is incorrect",Toast.LENGTH_LONG).show();
	                }
	                //TextView t1=(TextView)findViewById(R.id.textView9);
	                //t1.setText((new Integer(res.getCount())).toString());

	            }
					
				
			 });
	        
	        
	        
	    }
	
	

}
