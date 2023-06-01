package com.ecil.venue;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SampleActivity extends Activity {
EditText et1;
String vname,uemail,email;
TextView t1;
Usersignup v;
Button a;
ArrayList<String> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);
		setTitle("VenueBook");
		Bundle bundle = getIntent().getExtras();
		vname = bundle.getString("message");
		uemail= bundle.getString("uemail");
		t1=(TextView)findViewById(R.id.t1);
		
		v=new Usersignup(this);
        data = new ArrayList<String>();
        Cursor e=v.getemail(vname);
        if(e.getCount()!=0)
        	if(e.moveToNext())
        		email=e.getString(0);
        
        display();
        addListenerOnButton();
	}

	

	
	
	public void display()
    {
		ImageView imgView = (ImageView) findViewById(R.id.imageView1);
		 Cursor r=v.getimage(email);
		 if(r!=null)
        {
			 
            if(r.moveToFirst())
            {
           	 
           	 byte[] data1 = r.getBlob(0);
           	 
                   ByteArrayInputStream imageStream = new ByteArrayInputStream(data1);
                  
                   Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                  
                   imgView.setImageBitmap(theImage);
            }
        }
		Cursor res=v.detailsv(vname);
		if(res.getCount() !=0)
		{
		 ArrayAdapter adapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_list_item_1,data);
		 data.add("Venuename :"+ vname);
	        while (res.moveToNext()) {
	        	data.add("Venue Address :"+ res.getString(0));
	            data.add("E-mail :"+ res.getString(1));
	            data.add("Mobileno :"+ res.getString(2));
	            if((res.getString(3)!=null&&res.getString(4)!=null&&res.getString(5)!=null)&&res.getString(3).equals("yes")&&res.getString(4).equals("yes")&&res.getString(5).equals("yes"))
	                data.add("Type of Events : \n Family Event,Social Event,Business Event" );
	            else if((res.getString(3)!=null&&res.getString(4)!=null&&res.getString(5)!=null)&&(res.getString(3).equals("yes")||res.getString(4).equals("yes")||res.getString(5).equals("yes")))
	            {
	                if(res.getString(3).equals("yes")&&res.getString(4).equals("yes"))
	                    data.add("Type of Events : \n Family Event and Social Event" );
	                else if(res.getString(3).equals("yes")&&res.getString(5).equals("yes"))
	                    data.add("Type of Events : \n Family Event and Business Event" );
	                else if(res.getString(4).equals("yes")&&res.getString(5).equals("yes"))
	                    data.add("Type of Events : \nSocial Event and Business Event " );
	                else if(res.getString(3).equals("yes"))
	                    data.add("Type of Event : \n Family Event" );
	                else if(res.getString(4).equals("yes"))
	                    data.add("Type of Event : \n Social Event " );
	                else if(res.getString(5).equals("yes"))
	                    data.add("Type of Event : \n Business Event" );
	            }

	            data.add("Cost per Hour :"+ res.getString(6));
	            data.add("Capacity of the Hall:"+ res.getString(7));
	            data.add("Availabilty of AC:"+ res.getString(8));
	            data.add("Parking Capacity:"+ res.getString(9));
	            if((res.getString(10)!=null&&res.getString(11)!=null)&&res.getString(10).equals("yes")&&res.getString(11).equals("yes"))
	                data.add("Food Type: VEG and NON-VEG");
	            else if((res.getString(10)!=null)&&res.getString(10).equals("yes"))
	                data.add("Food Type: VEG");
	            else if((res.getString(11)!=null)&&res.getString(11).equals("yes"))
	                data.add("Food Type: NON-VEG");
	            data.add("Catering:"+ res.getString(12));
	           

	        }
	      
	        ListView listView = (ListView) findViewById(R.id.listView1);
	        listView.setAdapter(adapter);
		} 
		else
		{
			t1.setText("not found");
		}
    } 
	 public void addListenerOnButton()
	 {
		 a=(Button)findViewById(R.id.button1);
		 a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v1) {
				// TODO Auto-generated method stub
				//et2.setText("hello");
				Bundle b1=new Bundle();
				Intent i1 = new Intent(SampleActivity.this,VenuebookActivity.class);
				b1.putString("uemail",uemail);
				b1.putString("vname",vname);
				i1.putExtras(b1);
                startActivity(i1);
				
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
		                Intent intent=new Intent(SampleActivity.this,UserActivity.class);
		                startActivity(intent);
		                finish();
		        }
		        return true;
		

		 }
	 
}
