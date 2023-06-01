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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Venue1Activity extends Activity {
	String email;
    TextView t2;
    Usersignup v;
    ArrayList<String> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venue1);
		setTitle("venue Book");
		Bundle b=getIntent().getExtras();
        email=b.getString("email");
        v=new Usersignup(this);
        data = new ArrayList<String>();
        display();

	}
	 public void display()
	    {
		 ImageView imgView = (ImageView) findViewById(R.id.imageView1);
		 Cursor r=v.getimage(email);
		 if(r.getCount()==0)
         {
			 
			 return;
         }
             while(r.moveToNext())
             {
              	 byte[] data1 = r.getBlob(0);
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(data1);
                    Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                    imgView.setImageBitmap(theImage);
             }
 
	        Cursor res=v.details(email);
	        t2=(TextView)findViewById(R.id.t2);
	        if(res.getCount()==0)
	        {
	            t2.setText("Your venue details are not available");
	            return;
	            //Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
	        }

	        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
	        while (res.moveToNext()) {
	            data.add("Location of the Venue :"+ res.getString(12));
	            if((res.getString(0)!=null&&res.getString(1)!=null&&res.getString(2)!=null)&&res.getString(0).equals("yes")&&res.getString(1).equals("yes")&&res.getString(2).equals("yes"))
	                data.add("Type of Events : \n Family Event,Social Event,Business Event" );
	            else if((res.getString(0)!=null&&res.getString(1)!=null&&res.getString(2)!=null)&&(res.getString(0).equals("yes")||res.getString(1).equals("yes")||res.getString(2).equals("yes")))
	            {
	                if(res.getString(0).equals("yes")&&res.getString(1).equals("yes"))
	                    data.add("Type of Events : \n Family Event and Social Event" );
	                else if(res.getString(0).equals("yes")&&res.getString(2).equals("yes"))
	                    data.add("Type of Events : \n Family Event and Business Event" );
	                else if(res.getString(1).equals("yes")&&res.getString(2).equals("yes"))
	                    data.add("Type of Events : \nSocial Event and Business Event " );
	                else if(res.getString(0).equals("yes"))
	                    data.add("Type of Event : \n Family Event" );
	                else if(res.getString(1).equals("yes"))
	                    data.add("Type of Event : \n Social Event " );
	                else if(res.getString(2).equals("yes"))
	                    data.add("Type of Event : \n Business Event" );
	            }

	            data.add("Cost per Hour :"+ res.getString(3));
	            data.add("Capacity of the Hall:"+ res.getString(4));
	            data.add("Availabilty of AC:"+ res.getString(5));
	            data.add("Parking Capacity:"+ res.getString(6));
	            if((res.getString(7)!=null&&res.getString(8)!=null)&&res.getString(7).equals("yes")&&res.getString(8).equals("yes"))
	                data.add("Food Type: VEG and NON-VEG");
	            else if((res.getString(7)!=null)&&res.getString(7).equals("yes"))
	                data.add("Food Type: VEG");
	            else if((res.getString(7)!=null)&&res.getString(8).equals("yes"))
	                data.add("Food Type: NON-VEG");
	            data.add("Catering:"+ res.getString(9));
	            Float ot= new Float(res.getInt(10)/60.0);
	            data.add("Open Time:"+ot.toString());
	            Float ct= new Float(res.getInt(11)/60.0);
	            data.add("Close Time:"+ct.toString());

	        }
	        ListView listView = (ListView) findViewById(R.id.list1);
	        listView.setAdapter(adapter);
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
	                Intent intent=new Intent(this,VenueloginActivity.class);
	                startActivity(intent);
	                finish();
	        }
	        return true;
	

	 }

}
