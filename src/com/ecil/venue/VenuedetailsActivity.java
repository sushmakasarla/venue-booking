package com.ecil.venue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class VenuedetailsActivity extends Activity {
	
	 Button a;
	    CheckBox c1,c2,c3,c4,c5;
	    String ch1="no",ch2="no",ch3="no",ch4="no",ch5="no",email,vname,d1;
	    Usersignup v;
	    TextView t,t1;
	    RadioGroup r,r1;
	    EditText e1,e2,e3,e4;
	    RadioButton rb,rb1;
	    TimePicker timePicker1,timePicker2;
	    private static int RESULT_LOAD_IMG = 1;
		String imgDecodableString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venuedetails);
		setTitle("venue Book");
		 Bundle b=getIntent().getExtras();
	        email=b.getString("email");
	        vname=b.getString("vname");
	        v=new Usersignup(this);
	        java.util.Date date=new java.util.Date(); 
	        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
	        
	        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
	        timePicker1.setIs24HourView(true);
	        timePicker2 = (TimePicker) findViewById(R.id.timePicker2);
	        timePicker2.setIs24HourView(true);
	        addData();
	        for(int i=0;i<7;i++)
	        {
	        	Calendar c=Calendar.getInstance();
	        	c.setTime(date);
	        	c.add(Calendar.DATE, i);
	        	d1=d.format(c.getTime());
	        	boolean res=v.insertdate(d1,vname);
	        
	        if(res==true)
	        {
	        	/*Cursor res1=v.getVenuetable(vname);
	        	if(res1.moveToNext())
	        		Toast.makeText(VenuedetailsActivity.this,res1.getString(0),Toast.LENGTH_LONG).show();*/
	        		
	        	//Toast.makeText(VenuedetailsActivity.this,"inserted Successfully"+d1,Toast.LENGTH_LONG).show();
	        }
	        else
	        	Toast.makeText(VenuedetailsActivity.this,"inserted unSuccessfully",Toast.LENGTH_LONG).show();
	        }
	        	
	        	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.venuedetails, menu);
		return true;
	}
	public void loadImagefromGallery(View view) {
		// Create intent to Open Image applications like Gallery, Google Photos
		Intent galleryIntent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		// Start the Intent
		startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
	}
	public void addData()
    {
		a=(Button)findViewById(R.id.button1);
        a.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v1) {
                t=(TextView)findViewById(R.id.textView2);
                t1=(TextView)findViewById(R.id.textView6);

                c1=(CheckBox)findViewById(R.id.checkBox1);
                c2=(CheckBox)findViewById(R.id.checkBox2);
                c3=(CheckBox)findViewById(R.id.checkBox3);
                c4=(CheckBox)findViewById(R.id.checkBox4);
                c5=(CheckBox)findViewById(R.id.checkBox5);
                e1=(EditText)findViewById(R.id.editText1);
                e2=(EditText)findViewById(R.id.editText2);
                e3=(EditText)findViewById(R.id.editText3);
                e4=(EditText)findViewById(R.id.editText8);
                r=(RadioGroup)findViewById(R.id.r1);
                r1=(RadioGroup)findViewById(R.id.r2);
                //LOCATION

                //EVENT TYPE
                if(c1.isChecked()==true)
                    ch1="yes";
                else
                    ch1="no";

                if(c2.isChecked()==true)
                    ch2="yes";
                else
                    ch2="no";
                if(c3.isChecked()==true)
                    ch3="yes";
                else
                    ch3="no";
                if((ch1.equals("no"))&&(ch2.equals("no"))&&(ch3.equals("no")))
                {
                    //Toast.makeText(getApplicationContext(), "Select Atleast One Event Type", Toast.LENGTH_LONG).show();
                    t.setError("Select Atleast One Event Type");
                    return;
                }
                //COST PER HOUR
                if(e1.getText().toString().length()==0)
                {
                    e1.setError("Enter the Cost per Hour");
                    return;
                }
                //SEAT CAPACITY
                if(e2.getText().toString().length()==0)
                {
                    e2.setError("Enter the Seat Capacity");
                    return;
                }
                //AC FACILITY
                int selectedId=r.getCheckedRadioButtonId();
                rb=(RadioButton)findViewById(selectedId);
                //CAR PARKING CAPACITY
                if(e3.getText().toString().length()==0)
                {
                    e3.setError("Enter the Seat Capacity");
                    return;
                }
                //FOOD TYPE
                if(c4.isChecked()==true)
                    ch4="yes";
                else
                    ch4="no";
                if(c5.isChecked()==true)
                    ch5="yes";
                else
                    ch5="no";
                if((ch4.equals("no"))&&(ch5.equals("no")))
                {
                    //Toast.makeText(getApplicationContext(), "Select Atleast One Event Type", Toast.LENGTH_LONG).show();
                    t1.setError("Select Atleast One Event Type");
                    return;
                }
                //CATERING
                int selectedId1=r1.getCheckedRadioButtonId();
                rb1=(RadioButton)findViewById(selectedId);
                //OPEN TIME
                int hour1 = timePicker1.getCurrentHour();
                int min1 = timePicker1.getCurrentMinute();
                int ot=(hour1*60)+min1;
                //CLOSE TIME
                int hour2 = timePicker2.getCurrentHour();
                int min2 = timePicker2.getCurrentMinute();
                int ct=(hour2*60)+min2;
                boolean isInserted=v.insertDetails(ch1,ch2,ch3,e1.getText().toString(),e2.getText().toString(),rb.getText().toString(),e3.getText().toString(),ch4,ch5,rb1.getText().toString(),ot,ct,e4.getText().toString(),email);
                if(isInserted == true) {
                    Toast.makeText(VenuedetailsActivity.this,"Added Successfully",Toast.LENGTH_LONG).show();
                    Intent i1 = new Intent(VenuedetailsActivity.this,Venue1Activity.class);
                    i1.putExtra("email",email);
                    startActivity(i1);
                }
                else
                    Toast.makeText(VenuedetailsActivity.this,"Addition Unsuccessful", Toast.LENGTH_LONG).show();

            }
        });

		
       }
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		try {
			// When an Image is picked
			if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
					&& null != data) {
				// Get the Image from data

				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				// Get the cursor
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				// Move to first row
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				imgDecodableString = cursor.getString(columnIndex);
				cursor.close();
				ImageView imgView = (ImageView) findViewById(R.id.imageView1);
				// Set the Image in ImageView after decoding the String
				Bitmap yourSelectedImage=BitmapFactory.decodeFile(imgDecodableString);
				imgView.setImageBitmap(yourSelectedImage);
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 0, stream);
				byte[] byteArray = stream.toByteArray();
				boolean res=v.insertimage(byteArray,email);
				if(res==true)
					Toast.makeText(this, "image inserted",Toast.LENGTH_LONG).show();	
				else
					Toast.makeText(this, "cannot insert",Toast.LENGTH_LONG).show();

			}
			else 
			{
				Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
			}
		} 
		catch (Exception e)
		{
			Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
		}

	}

}



