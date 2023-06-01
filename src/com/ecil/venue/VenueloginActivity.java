package com.ecil.venue;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VenueloginActivity extends Activity {
	TextView t;
	EditText et1,et2;
	Button a;
	Usersignup db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venuelogin);
		setTitle("venue Book");
		db=new Usersignup(this);
		et1=(EditText)findViewById(R.id.et1);
		et2=(EditText)findViewById(R.id.et2);
		addListenerOnButton();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.venuelogin, menu);
		return true;
	}
	public void addListenerOnButton()
    {
    	t=(TextView)findViewById(R.id.textView1);
    
        t.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent d=new Intent(VenueloginActivity.this,VenuesignActivity.class);
				startActivity(d);
				
			}
			
			
			});
    

	
	        a=(Button)findViewById(R.id.button1);
			 a.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v1) {
					// TODO Auto-generated method stub
					
					Cursor res=db.check1(et1.getText().toString(),et2.getText().toString());
	                if(res.getCount()==1)
	                {
	                    
	                	Intent i1 = new Intent(VenueloginActivity.this,Venue1Activity.class);
	                	
	                	i1.putExtra("email",et1.getText().toString());
	                	startActivity(i1);
	                }
	                else
	                {
	                    Toast.makeText(VenueloginActivity.this,"Either Email/Password is incorrect",Toast.LENGTH_LONG).show();
	                }
	                //TextView t1=(TextView)findViewById(R.id.textView9);
	                //t1.setText((new Integer(res.getCount())).toString());

	            }
					
				
			 });
}
}